package it.gov.mef.informamef;

import it.gov.mef.util.MefDaoFactory;
import it.gov.mef.util.ParsingRSS;
import it.gov.mef.util.RSSItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TestDBActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		MefDaoFactory db = new MefDaoFactory(this);
		db.openDataBase(true);
		
		//inserisco gli RSS associo l'identificativo dell'icona associata come id della URL
//		db.insertRSS(R.id.homeImageButton1,"http://intranetdag-prod.tesoro.it/rss/rss.html?t=12002",	 "Intranet", "", "");
//		db.insertRSS(R.id.homeImageButton2,"http://www.mef.gov.it/rss/rss.asp?t=4", "MEF", "", "");
//		db.insertRSS(R.id.homeImageButton3,"http://www.mef.gov.it/rss/rss.asp?t=3", "MEF", "", "");
//		db.insertRSS(R.id.homeImageButton4,"http://www.mef.gov.it/rss/rss.asp?t=8&c=200", "MEF", "", "");
//		
//		
//		String feed = db.getRSSUrlById(R.id.homeImageButton1);
//		
//		ParsingRSS parseRSS = new ParsingRSS();
//		List<RSSItem> listRSS = parseRSS.parseUrlRSS(feed, R.id.homeImageButton1);
//		db.insertRSSItem(listRSS);
//		
//		feed = db.getRSSUrlById(R.id.homeImageButton2);
//		listRSS = parseRSS.parseUrlRSS(feed, R.id.homeImageButton2);
//		db.insertRSSItem(listRSS);
//		
//		feed = db.getRSSUrlById(R.id.homeImageButton3 );
//		listRSS = parseRSS.parseUrlRSS(feed, R.id.homeImageButton3);
//		db.insertRSSItem(listRSS);
//		
//		feed = db.getRSSUrlById(R.id.homeImageButton4);
//		listRSS = parseRSS.parseUrlRSS(feed, R.id.homeImageButton4);
//		db.insertRSSItem(listRSS);
		
		List<RSSItem> listRSS1 = db.getRSSList(R.id.homeImageButton1);
		List<RSSItem> listRSS2 = db.getRSSList(R.id.homeImageButton2);
		
		
		
		
		
		
		db.close();
		db.closeDataBase();
		
		
		
		
		
		
		
	}


}
