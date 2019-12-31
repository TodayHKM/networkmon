/*
 *    Copyright 2012 Carlos Alberto Umanzor Arguedas
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * 
 */
package linkcrawler.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import linkcrawler.datatypes.LinkStatus;
import linkcrawler.datatypes.LinkTypes;
import linkcrawler.datatypes.UrlReport;

/**
 *
 * @author Carlos Alberto Umanzor Arguedas
 */
public class ReportController extends Thread {
    
    private String nameOfSite;
    private ArrayList<UrlReport> reports;
    private String reportLocation = ""; //$NON-NLS-1$
    private String status = "Generating Report....."; //$NON-NLS-1$
    private boolean success = true;
    private String exception = ""; //$NON-NLS-1$
    private ReportType rt = ReportType.HTML;
    
    /*
     * Constructor - HTML is set as default report
     */
    public ReportController(String nameOfSite, ArrayList<UrlReport> reports)
    {
        this.nameOfSite = nameOfSite;
        this.reports = reports;
    }
    
    /*
     * Constructor - HTML is set as default report
     */
    public ReportController(String nameOfSite, ArrayList<UrlReport> reports, ReportType rt)
    {
        this.nameOfSite = nameOfSite;
        this.reports = reports;
        this.rt = rt;
    }

    @Override
    public void run() {
        if(this.rt.equals(ReportType.EXCEL))
        {
            generateExcelReport();
        }
        else if(this.rt.equals(ReportType.JSON))
        {
            generateJSONReport();
        }
        else
        {
            try {
                generateHTMLReport();
            } catch (Exception ex) {
                Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void generateJSONReport()
    {
    	if(this.reports.isEmpty())
        {
            this.status = "There is no data yet"; //$NON-NLS-1$
            this.success = false;
            this.exception = Messages.getString("ReportController.4"); //$NON-NLS-1$
            return;
        }
        try {

            status = "Generating report for site: " + nameOfSite; //$NON-NLS-1$

            //Preparing directories

            String reportDir = System.getProperty("user.dir") + File.separatorChar + "Reports"; //$NON-NLS-1$ //$NON-NLS-2$
            
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss"); //$NON-NLS-1$
            
            String reportDirFile = reportDir + File.separatorChar + "Report_" + dateFormat.format(date); //$NON-NLS-1$
            reportLocation = reportDirFile;
            String reportSybDir = reportDirFile + File.separatorChar + "resources"; //$NON-NLS-1$
            File reportDirPointer = new File(reportDir);
            File reportDirFilePointer = new File(reportDirFile);
            File reportSubDirPointer = new File(reportSybDir);

            //Creating directories if not there
            if(!reportSubDirPointer.exists())
            {
                reportDirPointer.mkdir();
                reportDirFilePointer.mkdir();
                reportSubDirPointer.mkdir();
            }
            int counter = 1;
            for (UrlReport report : reports)
            {
                status = "Generating report for " +report.getPageUrl(); //$NON-NLS-1$
                String nameOfReport = "report_"+ counter +".json"; //$NON-NLS-1$ //$NON-NLS-2$
                File subReportFile =new File(reportSybDir + File.separatorChar + nameOfReport);     
                subReportFile.createNewFile();
                FileWriter fw; fw = new FileWriter(subReportFile);
                fw.write(report.toJSON());
                fw.close();                    
                counter++;
            }           
            
            reportLocation = reportDirFile;
            status = "Report generated"; //$NON-NLS-1$

        } catch (IOException ex) {
            status = "Unable to create report"; //$NON-NLS-1$
            this.exception = ex.getMessage();
            success = false;
        }
        
    }
    
    public void generateExcelReport()
    {
        if(this.reports.isEmpty())
            {
                this.status = "There is no data yet"; //$NON-NLS-1$
                this.success = false;
                this.exception = Messages.getString("ReportController.0"); //$NON-NLS-1$
                return;
            }
        try {
            
            
            
            String reportDir = System.getProperty("user.dir") + File.separatorChar + "Reports"; //$NON-NLS-1$ //$NON-NLS-2$
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss"); //$NON-NLS-1$
            String reportDirFile = reportDir + File.separatorChar + "Report_" + dateFormat.format(date); //$NON-NLS-1$
            reportLocation = reportDirFile;
            
            String reportExcelFile = reportDirFile + File.separatorChar + "ReportExcel.xls"; //$NON-NLS-1$
            
            File reportDirPointer = new File(reportDir);
            File reportDirFilePointer = new File(reportDirFile);
            
            
            
            //Creating directories if not there
            if(!reportDirFilePointer.exists())
            {
                reportDirPointer.mkdir();
                reportDirFilePointer.mkdir();
                
            }
            File reportExcelFilePointer = new File(reportExcelFile);
            reportExcelFilePointer.createNewFile();
            
            WritableWorkbook workbook = Workbook.createWorkbook(reportExcelFilePointer);
            
            int sheetNumber = 0;
            for(UrlReport ur : reports)
            {
                WritableSheet sheet = workbook.createSheet("Crawled Url " + (sheetNumber + 1), sheetNumber++); //$NON-NLS-1$
                
                WritableFont blackHeadersFont = new WritableFont(WritableFont.ARIAL, 8, WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
                WritableCellFormat blackHeadersFormatBackground = new WritableCellFormat();
                
                blackHeadersFormatBackground.setBackground(Colour.BLUE) ;
                blackHeadersFormatBackground.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.BLACK);
                blackHeadersFormatBackground.setFont(blackHeadersFont);
                blackHeadersFormatBackground.setAlignment(Alignment.LEFT);
                
                WritableFont whiteValueFont = new WritableFont(WritableFont.ARIAL, 8, WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
                WritableCellFormat whiteValueFormatBackground = new WritableCellFormat();
                whiteValueFormatBackground.setBackground(Colour.WHITE);
                whiteValueFormatBackground.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.BLACK);
                whiteValueFormatBackground.setFont(whiteValueFont);
                whiteValueFormatBackground.setAlignment(Alignment.LEFT);
                //加入列、行、内容、格式
                Label introLabel = new Label(0, 0, "Site:", blackHeadersFormatBackground); //$NON-NLS-1$
                Label introLabelValue = new Label(1, 0, ur.getPageUrl()+"         ", whiteValueFormatBackground); //$NON-NLS-1$
                Label TitleValue = new Label(2, 0, ur.getTitle()+"         ", whiteValueFormatBackground); //$NON-NLS-1$
                
                
                sheet.addCell(introLabel);
                sheet.addCell(introLabelValue);
                sheet.addCell(TitleValue);
                
                int staringRow = 0;
                
                staringRow = prepareLinksDataDetail(sheet, ur, staringRow, LinkTypes.INTERNAL, "Internal links   ", blackHeadersFormatBackground, whiteValueFormatBackground); //$NON-NLS-1$
                staringRow = prepareLinksDataDetail(sheet, ur, staringRow, LinkTypes.EXTERNAL, "External links   ", blackHeadersFormatBackground, whiteValueFormatBackground); //$NON-NLS-1$
                staringRow = prepareLinksDataDetail(sheet, ur, staringRow, LinkTypes.SPECIAL, "Special links   ", blackHeadersFormatBackground, whiteValueFormatBackground); //$NON-NLS-1$
                staringRow = prepareLinksDataDetail(sheet, ur, staringRow, LinkTypes.IMAGES, "Images   ", blackHeadersFormatBackground, whiteValueFormatBackground); //$NON-NLS-1$
                
                
                for(int x=0; x < sheet.getColumns(); x++)
                {
                    CellView cell=sheet.getColumnView(x);
//                    cell.setAutosize(true);
                    sheet.setColumnView(x, cell);
                }
                
            }
            
            
            
            workbook.write(); 
            workbook.close();
            
            this.status = "Excel report generated"; //$NON-NLS-1$
        } catch (Exception ex) {
            this.status = "Error when generating Excel File"; //$NON-NLS-1$
            this.success = false;
            this.exception = ex.getMessage();
        }
    }
    
    private int prepareLinksDataDetail(WritableSheet sheet, UrlReport ur, int startingRow, LinkTypes lt, String label, WritableCellFormat blackHeadersFormatBackground, WritableCellFormat whiteValueFormatBackground) throws Exception
    {
                startingRow += 2;
                sheet.addCell(new Label(0, startingRow++, label, whiteValueFormatBackground));
                
                //headers
                sheet.addCell(new Label(0, startingRow, "URL Link", blackHeadersFormatBackground)); //$NON-NLS-1$
                sheet.addCell(new Label(1, startingRow, "Status", blackHeadersFormatBackground)); //$NON-NLS-1$
                sheet.addCell(new Label(2, startingRow++, "Text", blackHeadersFormatBackground)); //$NON-NLS-1$
                        
                if(lt.equals(LinkTypes.INTERNAL))
                {
                    if(ur.haveInternalLinks())
                    {
                        for(LinkStatus ls: ur.getInternalLinks())
                        {                        
                            String[] statusOfLink = ls.getStatusInfoInArray();
                            sheet.addCell(new Label(0, startingRow, statusOfLink[0], whiteValueFormatBackground));
                            sheet.addCell(new Label(1, startingRow, statusOfLink[1], whiteValueFormatBackground));
                            sheet.addCell(new Label(2, startingRow++, ls.getLinkText(), whiteValueFormatBackground));
                              
                        }
                    }
                    else
                    {
                        sheet.addCell(new Label(0, startingRow++, "No data collected", whiteValueFormatBackground)); //$NON-NLS-1$
                    }
                }
                else if(lt.equals(LinkTypes.EXTERNAL))
                {
                    if(ur.haveExternalLinks())
                    {
                        for(LinkStatus ls: ur.getExternalLinks())
                        {                        
                            String[] statusOfLink = ls.getStatusInfoInArray();
                            sheet.addCell(new Label(0, startingRow, statusOfLink[0], whiteValueFormatBackground));
                            sheet.addCell(new Label(1, startingRow, statusOfLink[1], whiteValueFormatBackground));
                            sheet.addCell(new Label(2, startingRow++, ls.getLinkText(), whiteValueFormatBackground));

                        }
                    }
                    else
                    {
                        sheet.addCell(new Label(0, startingRow++, "No data collected", whiteValueFormatBackground)); //$NON-NLS-1$
                    }
                }
                else if(lt.equals(LinkTypes.SPECIAL))
                {
                    if(ur.haveSpecialLinks())
                    {
                        for(LinkStatus ls: ur.getSpecialLinks())
                        {                        
                            String[] statusOfLink = ls.getStatusInfoInArray();
                            sheet.addCell(new Label(0, startingRow, statusOfLink[0], whiteValueFormatBackground));
                            sheet.addCell(new Label(1, startingRow, statusOfLink[1], whiteValueFormatBackground));
                            sheet.addCell(new Label(2, startingRow++, ls.getLinkText(), whiteValueFormatBackground));

                        }
                    }
                    else
                    {
                        sheet.addCell(new Label(0, startingRow++, "No data collected", whiteValueFormatBackground)); //$NON-NLS-1$
                    }
                }
                else if(lt.equals(LinkTypes.IMAGES))
                {
                    if(ur.haveImages())
                    {
                        for(LinkStatus ls: ur.getImagesSrc())
                        {                        
                            String[] statusOfLink = ls.getStatusInfoInArray();
                            sheet.addCell(new Label(0, startingRow, statusOfLink[0], whiteValueFormatBackground));
                            sheet.addCell(new Label(1, startingRow, statusOfLink[1], whiteValueFormatBackground));
                            sheet.addCell(new Label(2, startingRow++, ls.getLinkText(), whiteValueFormatBackground));
                        }
                    }
                    else
                    {
                        sheet.addCell(new Label(0, startingRow++, "No data collected", whiteValueFormatBackground)); //$NON-NLS-1$
                    }
                }
                
                
                
                return startingRow;
    }
    
    private void generateHTMLReport() throws Exception
    {
        if(this.reports.isEmpty())
            {
                this.status = "There is no data yet"; //$NON-NLS-1$
                this.success = false;
                this.exception = "Please run linkcrawler at least once in order to be able to generate a report"; //$NON-NLS-1$
                return;
            }
            try {

                status = "Generating report for site: " + nameOfSite; //$NON-NLS-1$

                //Preparing directories

                String reportDir = System.getProperty("user.dir") + File.separatorChar + "Reports"; //$NON-NLS-1$ //$NON-NLS-2$
                
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss"); //$NON-NLS-1$
                
                String reportDirFile = reportDir + File.separatorChar + "Report_" + dateFormat.format(date); //$NON-NLS-1$
                reportLocation = reportDirFile;
                String reportSybDir = reportDirFile + File.separatorChar + "jsonData"; //$NON-NLS-1$
                File reportDirPointer = new File(reportDir);
                File reportDirFilePointer = new File(reportDirFile);
                File reportSubDirPointer = new File(reportSybDir);

                //Creating directories if not there
                if(!reportSubDirPointer.exists())
                {
                    reportDirPointer.mkdir();
                    reportDirFilePointer.mkdir();
                    reportSubDirPointer.mkdir();
                }
                
                //GET STATISTICS and JSON BACKBONE
                int goodlinks = 0;
                int badlinks = 0;
                HashMap<String, Integer> contentStatistics = new HashMap<String, Integer>();
                String jsonReportsSection = ""; //$NON-NLS-1$
                for (UrlReport report : reports)
                {
                	goodlinks = goodlinks + report.getGoodLinks();
                	badlinks = badlinks + report.getBadLinks();
                	for(Entry<String, Integer> entry : report.getContentTypeStatistics().entrySet()) {
                	    String key = entry.getKey();
                	    Integer value = entry.getValue();

                	    if(!contentStatistics.containsKey(key))
                	    {
                	    	contentStatistics.put(key, value);
                	    }
                	    else
                	    {
                	    	contentStatistics.put(key, contentStatistics.get(key) + value);
                	    }
                	}
                	
                	jsonReportsSection+=report.toJSON()+", "; //$NON-NLS-1$
                }
                jsonReportsSection = jsonReportsSection.substring(0, jsonReportsSection.length() - 2);
                
                String jsonFile = "var jsonDATA = { \"statistics\" : [" //$NON-NLS-1$
                		+ "{\"topic\" : \"Total Links\", \"value\": "+ (goodlinks + badlinks) +" , \"type\" : \"Global Statistics\" }," //$NON-NLS-1$ //$NON-NLS-2$
                		+ "{\"topic\" : \"Good Links\", \"value\": "+ goodlinks +" , \"type\" : \"Global Statistics\" }," + //$NON-NLS-1$ //$NON-NLS-2$
                				  "{\"topic\" : \"Bad Links\" , \"value\": "+ badlinks +" , \"type\" : \"Global Statistics\"} ,"; //$NON-NLS-1$ //$NON-NLS-2$
                
                if(contentStatistics.size() == 0)
                {
                	jsonFile += "{ \"topic\": \"No data for ContentType\", \"value\": \"No Data\", \"type\" : \"Content Type Statistics\"},"; //$NON-NLS-1$
                }
                else
                {
                	for(Entry<String, Integer> entry : contentStatistics.entrySet()) {
                	    String key = entry.getKey();
                	    Integer value = entry.getValue();
                	    
                	    jsonFile += "{\"topic\" : \""+ key +"\", \"value\": "+ value +", \"type\" : \"Content Type Statistics\"}, ";                	     //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    }
                	jsonFile = jsonFile.substring(0, jsonFile.length() - 2);
                }
                
                jsonFile += "],"; //$NON-NLS-1$
                
                jsonFile += "\"reports\" : ["; //$NON-NLS-1$
                jsonFile += jsonReportsSection;
                jsonFile += "]};"; //$NON-NLS-1$
                
                status = "Generating JSON DATA "; //$NON-NLS-1$
                File subReportFile =new File(reportSybDir + File.separatorChar + "jsonData.js");      //$NON-NLS-1$
                subReportFile.createNewFile();
                FileWriter fw; fw = new FileWriter(subReportFile);
                fw.write(jsonFile);
                fw.close();    
                
                ZipInputStream zis = new ZipInputStream(new FileInputStream(getHTMLReportZIPFile()));
                ZipEntry entry = null;
                OutputStream out = null;
                while((entry = zis.getNextEntry()) != null){
					String fileName = entry.getName();
					if(entry.isDirectory())
					{
						new File(reportDirFile, fileName).mkdirs();
					}
					else
					{
						out = new FileOutputStream(new File(reportDirFile,fileName));
				          // Transfer bytes from the ZIP file to the output file
				          byte[] buf = new byte[1024];
				          int len;
				          while ((len = zis.read(buf)) > 0) {
				            out.write(buf, 0, len);
				          }
				          out.close();
					}
                }
             
                zis.closeEntry();
                zis.close();
                
                
                reportLocation = reportDirFile;
                status = "Report generated"; //$NON-NLS-1$

            } catch (IOException ex) {
                status = "Unable to create report"; //$NON-NLS-1$
                this.exception = ex.getMessage();
                success = false;
            }
            
        
    }
    
    private File getHTMLReportZIPFile()
    {
    	
        InputStream inZip = ReportController.class.getResourceAsStream("htmlviewer.zip"); //$NON-NLS-1$
        
        //Creating temp file
        File tempZIP = new File("tempzip.zip"); //$NON-NLS-1$
        
        try
	    {
        	if(tempZIP.exists())
        	{
        		tempZIP.delete();
        	}
	        OutputStream os = new FileOutputStream(tempZIP);
	        byte[] buffer = new byte[1024];
	        int bytesRead;
	        //read from is to buffer
	        while((bytesRead = inZip.read(buffer)) !=-1){
	            os.write(buffer, 0, bytesRead);
	        }
	        inZip.close();
	        //flush OutputStream to write any buffered data to file
	        os.flush();
	        os.close();
	    }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        
        return tempZIP;
    }
   

    public String getReportLocation() {
        return reportLocation;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getException() {
        return exception;
    }

    public String getStatus() {
        return status;
    }
}
