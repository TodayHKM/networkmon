/*
 *  ʵ������Ŀ��url����
 */

package linkcrawler.connectors;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * HTTPConnector is a centralized connector for HTTP resources
 * So far it contains the logic to mimic a classic browser GET call, this will prevent Error Code 500 on certain environments
 * 
 */
public class HTTPConnector {

	private String urlToConnect = ""; 
	private String exceptionCode = "";
	private long linkTime = 0 ;
	private boolean btimeOut=false;
	private int timeOut = 5000;  //���õĳ�ʱʱ��ms
	
	public HTTPConnector(String urlToConnect) {
		this.urlToConnect = urlToConnect.trim();
	}
	public HTTPConnector(String urlToConnect,int timeOut) {
		this.urlToConnect = urlToConnect.trim();
		this.timeOut = timeOut;
		
	}
	//��ȡ���ӣ�����쳣������null
	@SuppressWarnings("unused")
	public HttpURLConnection getConnection() 
	{
		try {
			// ����ȫ��Ĭ�ϵ�cookie����Ӧ���ض�������
			CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
			
			URL resourceURL = new URL(urlToConnect);     
			
			if( resourceURL.getProtocol().trim().toLowerCase().equals("https"))  //��ȫhttps����
			{
				SSLContext sc = null;
				TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}
					public void checkClientTrusted(X509Certificate[] certs, String authType) {
					}
					public void checkServerTrusted(X509Certificate[] certs, String authType) {
					}
					}
				};		
				try {
					sc = SSLContext.getInstance("SSL");
					sc.init(null, trustAllCerts, new java.security.SecureRandom());
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}catch (KeyManagementException e) {
					e.printStackTrace();
				}
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

				HostnameVerifier allHostsValid = new HostnameVerifier() {
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				};			
				
				HttpsURLConnection connectionToResource = (HttpsURLConnection) resourceURL.openConnection();
				connectionToResource.setRequestMethod("GET");
				connectionToResource.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
				connectionToResource.setRequestProperty("Host", resourceURL.getHost());
				connectionToResource.setRequestProperty("Path", resourceURL.getPath());
				connectionToResource.setRequestProperty("Connection", "keep-alive");
				connectionToResource.setRequestProperty("Accept-Language", "en-US,en;q=0.8,es;q=0.6");
				connectionToResource.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
				connectionToResource.setRequestProperty("Accept", "*/*");

			    connectionToResource.setReadTimeout(this.timeOut);//��ȡ���ݳ�ʱ
			    connectionToResource.setConnectTimeout(this.timeOut) ;// socket���ӳ�ʱ
				
				long st = System.currentTimeMillis();
				try {
					connectionToResource.connect();
					connectionToResource.getResponseCode();					
					linkTime = System.currentTimeMillis()-st;
					return connectionToResource;
				}catch(ProtocolException e) {
					exceptionCode = "ProtocolException";
					return null;
				}catch (java.net.UnknownHostException e) {
					exceptionCode = "UnknownHostException";
					return null;
				}catch (SocketException e) {
					exceptionCode = "SocketException";					
					return null;
				}catch(java.net.SocketTimeoutException e) {
					exceptionCode = "SocketTimeoutException";
					btimeOut = true;
					return null;
				}catch (IOException e) {
					exceptionCode = "HttpsIOException";
					e.printStackTrace();
					return null;
				}catch (Exception e) {
					exceptionCode = "Exception";
					e.printStackTrace();
					return null;
				}					        
			}
			else
			{
				HttpURLConnection connectionToResource = (HttpURLConnection) resourceURL.openConnection();
				connectionToResource.setRequestMethod("GET");
				connectionToResource.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
			    connectionToResource.setRequestProperty("Host", resourceURL.getHost());
			    connectionToResource.setRequestProperty("Path", resourceURL.getPath());
			    connectionToResource.setRequestProperty("Connection", "keep-alive");
			    connectionToResource.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
			    connectionToResource.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
			    connectionToResource.setRequestProperty("Accept", "*/*");
			    //��ʱ���ã�by fh 8.21
			    connectionToResource.setReadTimeout(this.timeOut/2);//��ȡ���ݳ�ʱ
			    connectionToResource.setConnectTimeout(this.timeOut/2) ;// socket���ӳ�ʱ
			 		      
			    long st = System.currentTimeMillis();
			    connectionToResource.connect();	
			    connectionToResource.getResponseCode();
			    linkTime = System.currentTimeMillis()-st;
			    
			    return connectionToResource;
			}
		} catch (MalformedURLException e) {
			exceptionCode = "MalformedURLException";
			return null;
		} catch (ProtocolException e) {
			exceptionCode = "ProtocolException";
			return null;
		}catch (java.net.UnknownHostException e) {
			exceptionCode = "UnknownHostException";
			return null;
		}catch (SocketException e) {
			exceptionCode = "SocketException";
			return null;
		}catch(java.net.SocketTimeoutException e) {
			exceptionCode = "SocketTimeoutException";
			btimeOut = true;
			return null;
		}catch (IOException e) {
			exceptionCode = "IOException";
			return null;
		}catch (Exception e) {
			exceptionCode = "Exception";
			e.printStackTrace();
			return null;
		}
	}

	//��ȡ���ӣ�ԭ�еķ�ʽ
		@SuppressWarnings("unused")
		public HttpURLConnection getConnection0() throws IOException {
			
				// ����ȫ��Ĭ�ϵ�cookie����Ӧ���ض�������
				CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
				
				URL resourceURL = new URL(urlToConnect);     
				
				if( resourceURL.getProtocol().trim().toLowerCase().equals("https"))  //��ȫhttps����
				{
					SSLContext sc = null;
					TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
						public X509Certificate[] getAcceptedIssuers() {
							return null;
						}
						public void checkClientTrusted(X509Certificate[] certs, String authType) {
						}
						public void checkServerTrusted(X509Certificate[] certs, String authType) {
						}
						}
					};		
					try {
						sc = SSLContext.getInstance("SSL");
						sc.init(null, trustAllCerts, new java.security.SecureRandom());
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}catch (KeyManagementException e) {
						e.printStackTrace();
					}
					HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

					HostnameVerifier allHostsValid = new HostnameVerifier() {
						public boolean verify(String hostname, SSLSession session) {
							return true;
						}
					};			
					
					HttpsURLConnection connectionToResource = (HttpsURLConnection) resourceURL.openConnection();
					connectionToResource.setRequestMethod("GET");
					connectionToResource.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
					connectionToResource.setRequestProperty("Host", resourceURL.getHost());
					connectionToResource.setRequestProperty("Path", resourceURL.getPath());
					connectionToResource.setRequestProperty("Connection", "keep-alive");
					connectionToResource.setRequestProperty("Accept-Language", "en-US,en;q=0.8,es;q=0.6");
					connectionToResource.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
					connectionToResource.setRequestProperty("Accept", "*/*");

				    connectionToResource.setReadTimeout(this.timeOut);//��ȡ���ݳ�ʱ
				    connectionToResource.setConnectTimeout(this.timeOut) ;// socket���ӳ�ʱ
					
					long st = System.currentTimeMillis();
					
						connectionToResource.connect();
						connectionToResource.getResponseCode();					
						linkTime = System.currentTimeMillis()-st;
						return connectionToResource;
					        
				}
				else
				{
					HttpURLConnection connectionToResource = (HttpURLConnection) resourceURL.openConnection();
					connectionToResource.setRequestMethod("GET");
					connectionToResource.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
				    connectionToResource.setRequestProperty("Host", resourceURL.getHost());
				    connectionToResource.setRequestProperty("Path", resourceURL.getPath());
				    connectionToResource.setRequestProperty("Connection", "keep-alive");
				    connectionToResource.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
				    connectionToResource.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
				    connectionToResource.setRequestProperty("Accept", "*/*");
				    //��ʱ���ã�by fh 8.21
				    connectionToResource.setReadTimeout(this.timeOut);//��ȡ���ݳ�ʱ
				    connectionToResource.setConnectTimeout(this.timeOut) ;// socket���ӳ�ʱ
				 		      
				    long st = System.currentTimeMillis();
				    connectionToResource.connect();	
				    connectionToResource.getResponseCode();
				    linkTime = System.currentTimeMillis()-st;
				    
				    return connectionToResource;
				}			
		}


	public String getExceptionCode() {
		return exceptionCode;
	}

	public long getLinkTime() {
		return linkTime;
	}
	
	public boolean isTimeOut() {
		return btimeOut;
	}

	}
