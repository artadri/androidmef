package it.gov.mef.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.text.Html;


public class RSSItem {

	 	private String title;
	    private String description;
	    private Date date;
	    private String link;
	    private int id_item;
	    private String guid;
	    private String category; 
	    private int idUrl;
	    private Date date_update;
	    private Date date_read;
        
	    
	    
	    public RSSItem(String title, String description, Date date,
				String link, int id_item, String guid, String category, int idUrl, Date date_read, Date date_updateDate ) {
			
			this.title = title;
			this.description = description;
			this.date = date;
			this.link = link;
			this.id_item = id_item;
			this.guid = guid;
			this.category = category;
			this.idUrl = idUrl;
			this.date_read = date_read;
			this.date_update = date_updateDate;
		}
	    
	    
	    public RSSItem(String title, String description, Date date,
				String link, int id_item, String guid, String category, int idUrl) {
			
			this.title = title;
			this.description = description;
			this.date = date;
			this.link = link;
			this.id_item = id_item;
			this.guid = guid;
			this.category = category;
			this.idUrl = idUrl;
		}
	    
	    

		public RSSItem(String title, String description, Date date, String link) {
			
			
			this.title = title;
			this.description = description;
			this.date = date;
			this.link = link;
			this.id_item = 0;
			this.guid = "";
			this.category = "";
			this.idUrl = 0;
		
	    }
	     
		
		
		
		
	    public Date getDate() {
			return date;
		}



		public void setDate(Date date) {
			this.date = date;
		}



		public Date getDate_update() {
			return date_update;
		}



		public void setDate_update(Date date_update) {
			this.date_update = date_update;
		}



		public Date getDate_read() {
			return date_read;
		}



		public void setDate_read(Date date_read) {
			this.date_read = date_read;
		}



		public String getTitle() {
	        return title;
	    }
	     
	    public void setTitle(String title) {
	        this.title = title;
	    }
	     
	    public String getDescription() {
	        return description;
	    }
	     
	    public void setDescription(String description) {
	        this.description = description;
	    }
	     
	    public Date getPubDate() {
	        return date;
	    }
	     
	    public void setPubDate(Date pubDate) {
	        this.date = pubDate;
	    }
	     
	    public String getLink() {
	        return link;
	    }
	     
	    public void setLink(String link) {
	        this.link = link;
	    }
	     
	    @Override
	    public String toString() {
	         
	        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm - MM/dd/yy");
	         
	        String result = getTitle() + getDescription() + "   ( " + sdf.format(this.getPubDate()) + " )";
	        
	        
	        return result;
	    }
	     
	    public static ArrayList<RSSItem> getRSSItems(String feedUrl) {
	         
	        ArrayList<RSSItem> rssItems = new ArrayList<RSSItem>();
	         
	        try {
//	        	System.setProperty("http.proxyHost", "alpha01.tesoro.it");
	        	
//	        	System.setProperty("http.proxyPort", "8080");
	             
	            URL url = new URL(feedUrl);
	           
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	          
	             
	            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
	                InputStream is = conn.getInputStream();
	                 
	                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	                DocumentBuilder db = dbf.newDocumentBuilder();
	                 
	                 
	                Document document = db.parse(is);
	                Element element = document.getDocumentElement();
	                 
	                 
	                NodeList nodeList = element.getElementsByTagName("item");
	                 
	                if (nodeList.getLength() > 0) {
	                    for (int i = 0; i < nodeList.getLength(); i++) {
	                    	 
	                        Element entry = (Element) nodeList.item(i);
	                        Element _titleE       = (Element)entry.getElementsByTagName("title").item(0);
	                        Element _descriptionE = (Element)entry.getElementsByTagName("description").item(0);
	                        Element _pubDateE       = (Element) entry.getElementsByTagName("pubDate").item(0);
	                        Element _linkE           = (Element) entry.getElementsByTagName("link").item(0);
	                        String _title           = _titleE.getFirstChild().getNodeValue();
	                        String _description   = _descriptionE.getFirstChild().getNodeValue();
	                        Date _pubDate           = new Date(_pubDateE.getFirstChild().getNodeValue());
	                        String _link           = _linkE.getFirstChild().getNodeValue();
	                        RSSItem rssItem = new RSSItem(_title, _description, _pubDate, _link);
	                         
	                        rssItems.add(rssItem);
	                         
	                    }
	                }
	                 
	            }
	             
	            } catch (Exception e) {
	             
	            e.printStackTrace();
	             
	        }
	         
	        return rssItems;
	    }

		public int getId_item() {
			return id_item;
		}

		public void setId_item(int id_item) {
			this.id_item = id_item;
		}

		public String getGuid() {
			return guid;
		}

		public void setGuid(String guid) {
			this.guid = guid;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}



		public int getIdUrl() {
			return idUrl;
		}



		public void setIdUrl(int idUrl) {
			this.idUrl = idUrl;
		}
}
