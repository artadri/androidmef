package it.gov.mef.informamef;

import it.gov.mef.util.DateUtil;
import it.gov.mef.util.FormatActionBar;
import it.gov.mef.util.MefDaoFactory;
import it.gov.mef.util.RSSItem;

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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RSSList extends Activity {

	private ListView listViewRSS;
	private Context ctx;
	private LinearLayout layout;
	private int refreshList ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rsslist);
		
//		
//		
////		Associo al pulsante home il ritorno alla home
//		ImageButton imageHome = (ImageButton) findViewById(R.id.imageHome);
//		imageHome.setOnClickListener(new OnClickListener() {
//			 
//			@Override
//			public void onClick(View v) {
// 
//				Intent intent = new Intent( v.getContext(), HomeActivity.class );
//				setContentView(R.layout.activity_home);
//				startActivity(intent);
//			}
// 
//		});
//		
//		Associo al pulsante home il ritorno alla home
		ImageButton imageRefesh = (ImageButton) findViewById(R.id.imageRefresh);
		imageRefesh.setVisibility(0);
		imageRefesh.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
 
				Intent intent = new Intent( v.getContext(), RSSList.class );
				intent.putExtra("idPulsante", refreshList);
				startActivity(intent);
			}
 
		});
		
		
		

		ctx = this;
		List<RSSItem> listRSS = new ArrayList<RSSItem>();
		try {
//			TODO da eliminare per il device reale
			System.setProperty("http.proxyHost", "alpha01.tesoro.it");
			System.setProperty("http.proxyPort", "8080");

			String feedUrl;
			int idPulsante = getIntent().getIntExtra("idPulsante", 0);
			URL url;
			HttpURLConnection conn = null;
			refreshList = idPulsante;
			
			
			
			MefDaoFactory db = new MefDaoFactory(this);
			db.openDataBase(false);
			
			
			// seleziono la URL da caricare
			switch (idPulsante) {
			case R.id.homeImageButton1:
				setTitle( getResources().getString(R.string.title_activity_rsslist_ico1)	);
				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome, R.id.imageRefresh, R.string.title_activity_rssdetail_ico1, false );
				feedUrl = "http://intranetdag-prod.tesoro.it/rss/rss.html?t=12002";
				
				break;
			case R.id.homeImageButton2:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico2));
				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome, R.id.imageRefresh, R.string.title_activity_rssdetail_ico2, false );
				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=4";
				break;
			case R.id.homeImageButton3:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico3));
				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome, R.id.imageRefresh, R.string.title_activity_rssdetail_ico3, false );
				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=3";
				break;
			case R.id.homeImageButton4:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico4));
				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome, R.id.imageRefresh, R.string.title_activity_rssdetail_ico4, false );
				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				break;
			default:
				this.setTitle("");
				feedUrl = "";
				break;
			}

			if (feedUrl != "") {
				url = new URL(feedUrl);
				conn = (HttpURLConnection) url.openConnection();
			}

//			Spostato su ParsingRSS
//			if (conn != null
//					&& conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//				InputStream is = conn.getInputStream();
//
//				DocumentBuilderFactory dbf = DocumentBuilderFactory
//						.newInstance();
//				DocumentBuilder db = dbf.newDocumentBuilder();
//
//				Document document = db.parse(is);
//				Element element = document.getDocumentElement();
//
//				NodeList nodeList = element.getElementsByTagName("item");
//
//				if (nodeList.getLength() > 0) {
//					for (int i = 0; i < nodeList.getLength(); i++) {
//
//						Element entry = (Element) nodeList.item(i);
//						Element _titleE = (Element) entry.getElementsByTagName(
//								"title").item(0);
//						Element _descriptionE = (Element) entry
//								.getElementsByTagName("description").item(0);
//						Element _pubDateE = (Element) entry
//								.getElementsByTagName("pubDate").item(0);
//						Element _linkE = (Element) entry.getElementsByTagName(
//								"link").item(0);
//						String _title = _titleE.getFirstChild().getNodeValue();
//						String _description = ((_descriptionE!=null && _descriptionE.getFirstChild() != null) ? _descriptionE.getFirstChild()
//								.getNodeValue() : "");
//
//						Date _pubDate = null;
//
//						try {
//							_pubDate = DateUtil.parseDate(_pubDateE
//									.getFirstChild().getNodeValue());
//						} catch (Exception e) {
//							_pubDate = new Date();
//
//						}
//
//						String _link = _linkE.getFirstChild().getNodeValue();
//						listRSS.add(new RSSItem(_title, _description, _pubDate,
//								_link));
//
//					}
//				}
//
//			}

			
			listRSS = db.getRSSList(idPulsante);
			db.updateRSS(idPulsante, (new Date()).toString());
			
			db.closeDataBase();
		} catch (Exception e) {

			e.printStackTrace();

		}

		

		
		
		
		listViewRSS = (ListView) findViewById(R.id.rss_list);
		listViewRSS.setAdapter(new RSSListAdapter(ctx, R.layout.rss_list_adapter, listRSS));


		OnItemClickListener itemClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// AdapterView is the parent class of ListView
				ListView lv = (ListView) arg0;

				RSSItem item = (RSSItem) listViewRSS
						.getItemAtPosition(position);
				String titolo = item.getTitle();
				String descrizione = item.getDescription();
				String link = item.getLink();
				Date data = item.getPubDate();
				int idItem = item.getId_item();
				int idUrl = item.getIdUrl() ;
				String guid = item.getGuid() ;
				String category = item.getCategory();
				

				// v.getResources()

				Intent detailRSS = new Intent(ctx, RSSDetailActivity.class);
				detailRSS.putExtra("titolo", titolo);
				detailRSS.putExtra("descrizione", descrizione);
				detailRSS.putExtra("link", link);
				detailRSS.putExtra("data", DateUtil.formatHTTPDate(data));
//				detailRSS.putExtra("idPulsante", refreshList);
				detailRSS.putExtra("idItem", idItem);
				detailRSS.putExtra("idUrl", idUrl );
				detailRSS.putExtra("guid", guid);
				detailRSS.putExtra("category", category);
				

				startActivity(detailRSS);

			}

		};

		// Setting the ItemClickEvent listener for the listview
		listViewRSS.setOnItemClickListener(itemClickListener);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rsslist, menu);
		return true;
	}

}
