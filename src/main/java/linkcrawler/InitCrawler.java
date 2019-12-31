package linkcrawler;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import linkcrawler.manager.TaskManager;

/**
 * Servlet implementation class InitCrawler
 */
@WebServlet(urlPatterns={"/InitCrawler"},loadOnStartup=0)
public class InitCrawler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitCrawler() {
        super();      
        
    }
    
    public void init(ServletConfig config) throws ServletException {
    	 System.out.println("start crawl task...");
         try {
			TaskManager  tm = new TaskManager();
			System.out.println("crawl task start success ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("crawl task start  failed ");
		}
        
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	 System.out.println("start crawling...");
    //     TaskManager  tm = new TaskManager();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
