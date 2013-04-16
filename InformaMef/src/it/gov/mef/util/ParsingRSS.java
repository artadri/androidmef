package it.gov.mef.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ParsingRSS {
	public ParsingRSS() {
		// TODO Auto-generated constructor stub
	}

	public List<RSSItem> parseUrlRSS(String feedUrl, int idUrl) {

		List<RSSItem> listRSS = new ArrayList<RSSItem>();
		try {
			URL url;
			HttpURLConnection conn = null;


			if (feedUrl != "") {
				url = new URL(feedUrl);
				conn = (HttpURLConnection) url.openConnection();
			}

			if (conn != null
					&& conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream is = conn.getInputStream();

				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();

				Document document = db.parse(is);
				Element element = document.getDocumentElement();

				NodeList nodeList = element.getElementsByTagName("item");

				if (nodeList.getLength() > 0) {
					for (int i = 0; i < nodeList.getLength(); i++) {

						Element entry = (Element) nodeList.item(i);
						Element _titleE = (Element) entry.getElementsByTagName(	"title").item(0);
						Element _descriptionE = (Element) entry.getElementsByTagName("description").item(0);
						Element _pubDateE = (Element) entry.getElementsByTagName("pubDate").item(0);
						Element _linkE = (Element) entry.getElementsByTagName("link").item(0);
						Element _categoryE = (Element) entry.getElementsByTagName("category").item(0);
						Element _guidE = (Element) entry.getElementsByTagName("guid").item(0);
						
						String _title = _titleE.getFirstChild().getNodeValue();
						String _description = ((_descriptionE != null && _descriptionE
								.getFirstChild() != null) ? _descriptionE
								.getFirstChild().getNodeValue() : "");

						Date _pubDate = null;

						try {
							_pubDate = DateUtil.parseDate(_pubDateE
									.getFirstChild().getNodeValue());
						} catch (Exception e) {
							_pubDate = new Date();

						}

						String _link = _linkE.getFirstChild().getNodeValue();
						String _category = _categoryE.getFirstChild().getNodeValue();
						String _guid = _guidE.getFirstChild().getNodeValue();
						
						
						 
						
						listRSS.add(new RSSItem(_title, _description, _pubDate,	_link, 0 , _guid, _category, idUrl, null, new Date()));

					}
				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return listRSS;
	}

}
