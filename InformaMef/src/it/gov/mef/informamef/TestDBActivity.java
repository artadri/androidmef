package it.gov.mef.informamef;

import it.gov.mef.util.MefConstants;
import it.gov.mef.util.MefDaoFactory;
import it.gov.mef.util.ParsingRSS;
import it.gov.mef.util.RSSItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
//		db.execSQL("DROP TABLE IF EXISTS item_rss" );
//		db.execSQL("DROP TABLE IF EXISTS rss" );
//		
		
		//inserisco gli RSS associo l'identificativo dell'icona associata come id della URL
//		db.insertRSS(MefConstants.idRSS1,"http://intranetdag-prod.tesoro.it/rss/rss.html?t=12002",	 "Intranet", "", "");
//		db.insertRSS(MefConstants.idRSS2,"http://www.mef.gov.it/rss/rss.asp?t=4", "MEF", "", "");
//		db.insertRSS(MefConstants.idRSS3,"http://www.mef.gov.it/rss/rss.asp?t=3", "MEF", "", "");
//		db.insertRSS(MefConstants.idRSS4,"http://www.mef.gov.it/rss/rss.asp?t=8&c=200", "MEF", "", "");
//		
//		
//		String feed = db.getRSSUrlById(MefConstants.idRSS1);
//		
//		ParsingRSS parseRSS = new ParsingRSS();
//		List<RSSItem> listRSS = parseRSS.parseUrlRSS(feed, MefConstants.idRSS1);
//		db.insertRSSItem(listRSS);
//		
//		feed = db.getRSSUrlById(MefConstants.idRSS2);
//		listRSS = parseRSS.parseUrlRSS(feed, MefConstants.idRSS2);
//		db.insertRSSItem(listRSS);
//		
//		feed = db.getRSSUrlById(MefConstants.idRSS3 );
//		listRSS = parseRSS.parseUrlRSS(feed, MefConstants.idRSS3);
//		db.insertRSSItem(listRSS);
//		
//		feed = db.getRSSUrlById(MefConstants.idRSS4);
//		listRSS = parseRSS.parseUrlRSS(feed, MefConstants.idRSS4);
//		db.insertRSSItem(listRSS);
//		
//		List<RSSItem> listRSS1 = db.getRSSList(MefConstants.idRSS1);
//		List<RSSItem> listRSS2 = db.getRSSList(MefConstants.idRSS2);
//		List<RSSItem> listRSS3 = db.getRSSList(MefConstants.idRSS3);
//		List<RSSItem> listRSS4 = db.getRSSList(MefConstants.idRSS4);
		
		db.execSQL("delete from item_rss" );
		
//		int count = db.updateRSSItem(MefConstants.idRSS1);
//		Log.d(this.toString(),count +"");
//		count += db.updateRSSItem(MefConstants.idRSS2);
//		Log.d(this.toString(),count +"");
//		count += db.updateRSSItem(MefConstants.idRSS3);
//		Log.d(this.toString(),count +"");
//		count += db.updateRSSItem(MefConstants.idRSS4);
		
		
		
		
		
		
		db.close();
		db.closeDataBase();
		
		
		
		
		
		
		
	}


}
