package it.gov.mef.informamef;

import it.gov.mef.util.DateUtil;
import it.gov.mef.util.MefConstants;
import it.gov.mef.util.MefDaoFactory;
import it.gov.mef.util.RSSHomeItem;
import it.gov.mef.util.RSSItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HomeActivity extends Activity implements OnClickListener {

	// Definisco le img botton
	ImageButton imageButton1;
	ImageButton imageButton2;
	ImageButton imageButton3;
	ImageButton imageButton4;
	ImageButton imageButtonContact;
	ImageButton imageButtonPodcast;
	private Context ctx;
	
	private ListView listViewHome;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ctx = this;
		MefDaoFactory db = new MefDaoFactory(this);
		db.openDataBase(false);
		db.getTotRSSItemNotRead(MefConstants.idRSS1);
		
		
		
//		TextView txt1 = (TextView) findViewById(R.id.homeTextNumElem1);
//		txt1.setText("[ " + db.getTotRSSItemNotRead(MefConstants.idRSS1)
//				+ " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS1)
//				+ " ]");
//
//		imageButton1 = (ImageButton) findViewById(R.id.homeImageButton1);
//		imageButton1.setOnClickListener(this);
//
//		TextView txt2 = (TextView) findViewById(R.id.homeTextNumElem2);
//		txt2.setText("[ " + db.getTotRSSItemNotRead(MefConstants.idRSS2)
//				+ " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS2)
//				+ " ]");
//
//		imageButton2 = (ImageButton) findViewById(R.id.homeImageButton2);
//		imageButton2.setOnClickListener(this);
//
//		TextView txt3 = (TextView) findViewById(R.id.homeTextNumElem3);
//		txt3.setText("[ " + db.getTotRSSItemNotRead(MefConstants.idRSS3)
//				+ " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS3)
//				+ " ]");
//
//		imageButton3 = (ImageButton) findViewById(R.id.homeImageButton3);
//		imageButton3.setOnClickListener(this);
//
//		TextView txt4 = (TextView) findViewById(R.id.homeTextNumElem4);
//		txt4.setText("[ " + db.getTotRSSItemNotRead(MefConstants.idRSS4)
//				+ " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS4)
//				+ " ]");
//
//		imageButton4 = (ImageButton) findViewById(R.id.homeImageButton4);
//		imageButton4.setOnClickListener(this);
//
//		imageButtonContact = (ImageButton) findViewById(R.id.homeImageButtonContact);
//		imageButtonContact.setOnClickListener(this);
//
//		imageButtonPodcast = (ImageButton) findViewById(R.id.homeImageButtonPodcast);
//		imageButtonPodcast.setOnClickListener(this);

		

		
		
		List<RSSHomeItem> itemHomeList= new ArrayList<RSSHomeItem>();
		Date data_agg = db.getRSSLastUpdate(MefConstants.idRSS1);
		String numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS1) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS1)
				+ " ]";
		RSSHomeItem elemento = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico1)  , numElem, R.drawable.home_imagebutton1 + "", data_agg);
		data_agg = db.getRSSLastUpdate(MefConstants.idRSS2);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS2) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS2)
				+ " ]";
		RSSHomeItem elemento2 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico2) , numElem, R.drawable.home_imagebutton2 + "", data_agg);
		data_agg = db.getRSSLastUpdate(MefConstants.idRSS3);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS3) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS3)
				+ " ]";
		RSSHomeItem elemento3 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico3) , numElem, R.drawable.home_imagebutton3 + "", data_agg);
		data_agg = db.getRSSLastUpdate(MefConstants.idRSS4);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS4) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS4)
				+ " ]";
		RSSHomeItem elemento4 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico4) , numElem, R.drawable.home_imagebutton4 + "", data_agg);

		itemHomeList.add(elemento);
		itemHomeList.add(elemento2);
		itemHomeList.add(elemento3);
		itemHomeList.add(elemento4);

//		TODO da finire modificando il layout
		listViewHome = (ListView) findViewById( R.id.home_list);
		listViewHome.setAdapter( new RSSHomeListAdapter(this, R.layout.rss_home_list_adapter, itemHomeList ) );
        
		
		
		
//		Setto il listener alla lista
		OnItemClickListener itemClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// AdapterView is the parent class of ListView
				ListView lv = (ListView) arg0;

				RSSHomeItem item = (RSSHomeItem) listViewHome.getItemAtPosition(position);
				
				Intent intent =  new Intent(ctx, RSSList.class);
				
//				TODO da sistemare 
				switch (position) {
				case 0:
					intent.putExtra("idPulsante", R.id.homeImageButton1);
					break;
				case 1:
					intent.putExtra("idPulsante", R.id.homeImageButton2);				
					break;
				case 2:
					intent.putExtra("idPulsante", R.id.homeImageButton3);
					break;
				case 3:
					intent.putExtra("idPulsante", R.id.homeImageButton4);
					break;

				default:
					break;
				}
				
			
				startActivity(intent);

			}

		};

		
		
		
		
		listViewHome.setOnItemClickListener(itemClickListener);
		
		db.closeDataBase();
		
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("MEFHomeActivity", "onStart");
	}

	@Override
	protected void onResume() {

		super.onResume();
		// SharedPreferences prefs =
		// PreferenceManager.getDefaultSharedPreferences(this);
		// int minutes = prefs.getInt("syncFrequencyValues",1);
		// AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		// Intent i = new Intent(this, NotificationService.class);
		// PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
		// am.cancel(pi);
		// // by my own convention, minutes <= 0 means notifications are
		// disabled
		// if (minutes > 0) {
		// am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
		// SystemClock.elapsedRealtime() + minutes*60*1000,
		// minutes*60*1000, pi);
		// }
		//

		Log.d("MEFHomeActivity", "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d("MEFHomeActivity", "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("MEFHomeActivity", "onStop");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("MEFHomeActivity", "onRestart");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("MEFHomeActivity", "onDestroy");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	/**
	 * Event Handling for Individual menu item selected Identify single menu
	 * item by it's id
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.item_prefs:
			// Single menu item is selected do something
			// Ex: launching new activity/screen or show alert message
			Toast.makeText(HomeActivity.this,
					"Bookmark is Selected item_prefs", Toast.LENGTH_SHORT)
					.show();
			return true;
		case R.id.action_settings:
			startActivity(new Intent(this, PrefsActivity.class));// start the
																	// PrefsActivity.java
			// startActivity(new Intent(this, TestDBActivity.class));//start the
			// PrefsActivity.java
			return true;
		case R.id.action_refresh_list:

			MefDaoFactory db = new MefDaoFactory(this);
			try {

				db.openDataBase(true);
				List<Integer> feed = db.getRSSListURL();
				Iterator<Integer> it = feed.iterator();
				while (it.hasNext()) {
					Integer elem = (Integer) it.next();

					db.updateRSSItem(elem.intValue());
					Log.d(this.toString(), "Aggiornato id=" + elem.intValue());
				}

			} catch (Exception e) {
				Log.e(this.toString(), e.toString());
				if (db != null) {
					db.close();
					db.closeDataBase();
				}
			} finally {
				if (db != null) {
					db.close();
					db.closeDataBase();
				}
			}

			startActivity(new Intent(this, HomeActivity.class));

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onClick(View v) {

		Intent intent;
		switch (v.getId()) {
		case R.id.homeImageButtonContact:

			intent = new Intent(this, ContactActivity.class);
			startActivity(intent);
			break;
		case R.id.homeImageButtonPodcast:

			intent = new Intent(this, PodcastActivity.class);
			startActivity(intent);
			break;

		default:
			// Intent intent = new Intent(this, RSSListActivity.class);
			intent = new Intent(this, RSSList.class);
			intent.putExtra("idPulsante", v.getId());
			startActivity(intent);
			break;
		}

	}

}
