package it.gov.mef.informamef;

import it.gov.mef.util.DateUtil;
import it.gov.mef.util.FormatActionBar;
import it.gov.mef.util.FormatTitleBar;
import it.gov.mef.util.MefConstants;
import it.gov.mef.util.MefDaoFactory;
import it.gov.mef.util.NavigationBean;
import it.gov.mef.util.RSSHomeItem;
import it.gov.mef.util.RSSItem;
import it.gov.mef.util.UpdateRSS;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
	private int idRSS ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rsslist);

		

		ctx = this;
		List<RSSItem> listRSS = new ArrayList<RSSItem>();
		try {

			
			String feedUrl;
			int idPulsante = getIntent().getIntExtra("idPulsante", 0);
			idRSS = 1;
			URL url;
			HttpURLConnection conn = null;
			refreshList = idPulsante;
			
			NavigationBean nav = (NavigationBean) getApplication();
			List<RSSHomeItem> itemHomeList = nav.getItemHomeList();
			RSSHomeItem itemHome = itemHomeList.get(nav.getCurrentRSS());
			
			FormatTitleBar.settingTitle(this, itemHome.getNome() ); 
			
			idPulsante = itemHome.getIdRSS();
			idRSS =  itemHome.getIdRSS();
			
			MefDaoFactory db = new MefDaoFactory(this);
			db.openDataBase(false);
			
			
			// seleziono la URL da caricare
			switch (idPulsante) {
			case MefConstants.idRSS1:
				setTitle( getResources().getString(R.string.title_activity_rsslist_ico1)	);
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico1);
//				feedUrl = "http://intranetdag-prod.tesoro.it/rss/rss.html?t=12002";
				idRSS = 1;
				break;
			case MefConstants.idRSS2:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico2));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico2);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=4";
				idRSS = 2;
				break;
			case MefConstants.idRSS3:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico3));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico3);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=3";
				idRSS = 3;
				break;
			case MefConstants.idRSS4:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico4));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico4);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 4;
				break;
			case MefConstants.idRSS5:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico5));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico5);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 5;
				break;
			case MefConstants.idRSS6:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico6));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico6);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 6;
				break;
			case MefConstants.idRSS7:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico7));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico7);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 7;
				break;
			case MefConstants.idRSS8:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico8));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico8);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 8;
				break;
			case MefConstants.idRSS9:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico9));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico9);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 9;
				break;
			case MefConstants.idRSS10:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico10));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico10);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 10;
				break;
			case MefConstants.idRSS11:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico11));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico11);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 11;
				break;
			case MefConstants.idRSS12:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico12));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico12);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 12;
				break;
			case MefConstants.idRSS13:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico13));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico13);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 13;
				break;
			case MefConstants.idRSS14:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico14));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico14);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 14;
				break;
			case MefConstants.idRSS15:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico15));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico15);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 15;
				break;
			case MefConstants.idRSS16:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico16));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico16);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 16;
				break;
			case MefConstants.idRSS17:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico17));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico17);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 17;
				break;
			case MefConstants.idRSS18:
				setTitle(getResources().getString(R.string.title_activity_rsslist_ico18));
//				FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,R.string.title_activity_rssdetail_ico18);
//				feedUrl = "http://www.mef.gov.it/rss/rss.asp?t=8&c=200";
				idRSS = 18;
				break;
			default:
				this.setTitle("");
				feedUrl = "";
				break;
			}


			listRSS = db.getRSSList(itemHome.getIdRSS());
			nav.setListRSS(listRSS);
//			listRSS = db.getRSSList(idRSS);
		
			
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
				detailRSS.putExtra("idPulsante", refreshList);
				detailRSS.putExtra("idItemRSS", idItem);
				detailRSS.putExtra("idUrl", idUrl );
				detailRSS.putExtra("guid", guid);
				detailRSS.putExtra("category", category);
				
				RSSItem itemRSS = new RSSItem(titolo, descrizione, data, link, idItem, guid, category, idUrl);
				
				NavigationBean nav = (NavigationBean) getApplication();
				nav.setItemRSS(itemRSS);
				nav.setCurrentItemRSS(position);
				nav.setMaxItemRSS(listViewRSS.getCount());
		
				
				startActivity(detailRSS);

			}

		};

		// Setting the ItemClickEvent listener for the listview
		listViewRSS.setOnItemClickListener(itemClickListener);
		
		ImageButton imageBack= (ImageButton) findViewById(R.id.imageBackNav);
		imageBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent =  new Intent(ctx,  RSSList.class);
								
				NavigationBean nav = (NavigationBean)getApplication();
				int back = nav.getCurrentRSS()-1;
				nav.setCurrentRSS(back);
				startActivity(intent);
				
			}

		});


		ImageButton imageForward= (ImageButton) findViewById(R.id.imageForward);
		imageForward.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent =  new Intent(ctx, RSSList.class);
				
				NavigationBean nav = (NavigationBean)getApplication();
				int forward = nav.getCurrentRSS() + 1;
				nav.setCurrentRSS(forward);
				startActivity(intent);
			}

		});
		
		NavigationBean nav = (NavigationBean)getApplication();
		if (nav.getCurrentRSS() > 0 && nav.getCurrentRSS() <= nav.getMaxRSS() ) {
			imageBack.setVisibility(View.VISIBLE);
	    } else {
	    	imageBack.setVisibility(View.INVISIBLE);
	    }
		
		if (nav.getCurrentRSS() < nav.getMaxRSS() && nav.getCurrentRSS() >= 0) {
			imageForward.setVisibility(View.VISIBLE);
		} else {
			imageForward.setVisibility(View.INVISIBLE);
		}

		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	
	 /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
 
        switch (item.getItemId()) 
        {

		case R.id.action_settings:
			startActivity(new Intent(this, PrefsActivity.class));// start the
																	// PrefsActivity.java
			// startActivity(new Intent(this, TestDBActivity.class));//start the
			// PrefsActivity.java
			return true;
		case R.id.action_refresh_list:

//			TODO Deve aggiornare solo RSS corrente
			Toast.makeText(this,
					"Sincronizzazione avviata", Toast.LENGTH_SHORT)
					.show();
			NavigationBean nav = (NavigationBean)getApplication();
			List<RSSHomeItem> itemHomeList = nav.getItemHomeList();
			RSSHomeItem itemHome = itemHomeList.get(nav.getCurrentRSS());
			new UpdateRSS().execute(new Object [] {ctx, new Integer(-1), new Integer(itemHome.getIdRSS()) } );

			return true;
		case R.id.actionContact:

			 Intent intent = new Intent(this, ContactActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_home:
			Intent intentHome = new Intent(this, HomeDipActivity.class);
			startActivity(intentHome);
			return true;
		case R.id.action_indietro:
			onBackPressed();
			return true;
		case R.id.action_esci:
			Intent intent1 = new Intent(Intent.ACTION_MAIN);
			intent1.addCategory(Intent.CATEGORY_HOME);
			intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent1);
			return true; 

			
		default:
			return super.onOptionsItemSelected(item);
		}
    }    
    
    @Override
	public void onBackPressed() {
		NavigationBean nav = (NavigationBean) getApplication();
		nav.setCurrentRSS(0);
		nav.setItemRSS(null);
		nav.setCurrentItemRSS(0);
		nav.setMaxItemRSS(0);
		nav.setItemHomeList(null);
		
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);

	}

    

}
