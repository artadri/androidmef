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

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HomeActivity extends Activity {

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
		
		NavigationBean nav = (NavigationBean) getApplication();
		
		MefDaoFactory db = new MefDaoFactory(this);
		db.openDataBase(true);
		

		
		List<RSSHomeItem> itemHomeList= new ArrayList<RSSHomeItem>();

		
		int dipartimentoSelezionato = nav.getDipartimento();
		
		
		Date data_agg = null;
		String numElem = "";
		
		
		if ( MefConstants.MEF == dipartimentoSelezionato){
			

			FormatTitleBar.settingTitle(this, getString( R.string.title_home_mef), R.drawable.ic_logo_mef_9); 
			
			
//	MEF
		data_agg = db.getRSSLastUpdate(MefConstants.idRSS1);
		 numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS1) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS1)
				+ " ]";
		RSSHomeItem elemento = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico1)  , numElem, R.drawable.home_imagebutton1 + "", data_agg, MefConstants.idRSS1);
		
		data_agg = db.getRSSLastUpdate(MefConstants.idRSS2);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS2) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS2)
				+ " ]";
		RSSHomeItem elemento2 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico2) , numElem, R.drawable.home_imagebutton2 + "", data_agg, MefConstants.idRSS2);
		
		data_agg = db.getRSSLastUpdate(MefConstants.idRSS3);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS3) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS3)
				+ " ]";
		RSSHomeItem elemento3 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico3) , numElem, R.drawable.home_imagebutton3 + "", data_agg, MefConstants.idRSS3);
		
		data_agg = db.getRSSLastUpdate(MefConstants.idRSS4);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS4) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS4)
				+ " ]";
		RSSHomeItem elemento4 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico4) , numElem, R.drawable.home_imagebutton4 + "", data_agg, MefConstants.idRSS4);

		data_agg = db.getRSSLastUpdate(MefConstants.idRSS5);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS5) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS5)
				+ " ]";
		RSSHomeItem elemento5 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico5) , numElem, R.drawable.home_imagebutton4 + "", data_agg, MefConstants.idRSS5);
		
//		Aggiungo gli elementi alla lista
		itemHomeList.add(elemento);
		itemHomeList.add(elemento2);
		itemHomeList.add(elemento3);
		itemHomeList.add(elemento4);
		itemHomeList.add(elemento5);
		
		
		
		
		}
		
		if (MefConstants.DT == dipartimentoSelezionato){
			
		
// DT
			FormatTitleBar.settingTitle(this, getString( R.string.title_home_dt), R.drawable.ic_logo_dt_9); 
		
		data_agg = db.getRSSLastUpdate(MefConstants.idRSS6);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS6) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS6)
				+ " ]";
		RSSHomeItem elemento6 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico6) , numElem, R.drawable.home_imagebutton3 + "", data_agg, MefConstants.idRSS6);

		data_agg = db.getRSSLastUpdate(MefConstants.idRSS7);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS7) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS7)
				+ " ]";
		RSSHomeItem elemento7 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico7) , numElem, R.drawable.home_imagebutton3 + "", data_agg, MefConstants.idRSS7);

		data_agg = db.getRSSLastUpdate(MefConstants.idRSS8);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS8) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS8)
				+ " ]";
		RSSHomeItem elemento8 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico8) , numElem, R.drawable.home_imagebutton4 + "", data_agg, MefConstants.idRSS8);

		data_agg = db.getRSSLastUpdate(MefConstants.idRSS9);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS9) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS9)
				+ " ]";
		RSSHomeItem elemento9 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico9) , numElem, R.drawable.home_imagebutton4 + "", data_agg, MefConstants.idRSS9);

		data_agg = db.getRSSLastUpdate(MefConstants.idRSS10);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS10) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS10)
				+ " ]";
		RSSHomeItem elemento10 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico10) , numElem, R.drawable.home_imagebutton3 + "", data_agg, MefConstants.idRSS10);
		
		data_agg = db.getRSSLastUpdate(MefConstants.idRSS11);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS11) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS11)
				+ " ]";
		RSSHomeItem elemento11 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico11) , numElem, R.drawable.home_imagebutton4 + "", data_agg, MefConstants.idRSS11);

		data_agg = db.getRSSLastUpdate(MefConstants.idRSS12);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS12) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS12)
				+ " ]";
		RSSHomeItem elemento12 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico12) , numElem, R.drawable.home_imagebutton4 + "", data_agg, MefConstants.idRSS12);

		data_agg = db.getRSSLastUpdate(MefConstants.idRSS13);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS13) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS13)
				+ " ]";
		RSSHomeItem elemento13 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico13) , numElem, R.drawable.home_imagebutton4 + "", data_agg, MefConstants.idRSS13);
		
//		Aggiungo gli elementi alla lista
		itemHomeList.add(elemento6);
		itemHomeList.add(elemento7);
		itemHomeList.add(elemento8);
		itemHomeList.add(elemento9);
		itemHomeList.add(elemento10);
		itemHomeList.add(elemento11);
		itemHomeList.add(elemento12);
		itemHomeList.add(elemento13);
		
		
		}
		
		if (MefConstants.DAG == dipartimentoSelezionato){
			
// DAG
			FormatTitleBar.settingTitle(this, getString( R.string.title_home_dag), R.drawable.ic_logo_dag_9); 
		data_agg = db.getRSSLastUpdate(MefConstants.idRSS14);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS14) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS14)
				+ " ]";
		RSSHomeItem elemento14 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico14) , numElem, R.drawable.home_imagebutton4 + "", data_agg, MefConstants.idRSS14);
		
//		Aggiungo gli elementi alla lista

		itemHomeList.add(elemento14);
		
		}
		
		if (MefConstants.RGS == dipartimentoSelezionato){
			
//	RGS
			FormatTitleBar.settingTitle(this, getString( R.string.title_home_rgs), R.drawable.ic_logo_rgs_9); 
		
		data_agg = db.getRSSLastUpdate(MefConstants.idRSS15);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS15) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS15)
				+ " ]";
		RSSHomeItem elemento15 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico15) , numElem, R.drawable.home_imagebutton4 + "", data_agg, MefConstants.idRSS15);
		
//		Aggiungo gli elementi alla lista
		itemHomeList.add(elemento15);
		
		}
		
		if (MefConstants.INTRANET_DAG == dipartimentoSelezionato){
			
//	INTRANET
			FormatTitleBar.settingTitle(this, getString( R.string.title_home_intranetdag), R.drawable.ic_logo_intranetdag_9); 
		data_agg = db.getRSSLastUpdate(MefConstants.idRSS16);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS16) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS16)
				+ " ]";
		RSSHomeItem elemento16 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico16) , numElem, R.drawable.home_imagebutton4 + "", data_agg, MefConstants.idRSS16);

		data_agg = db.getRSSLastUpdate(MefConstants.idRSS17);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS17) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS17)
				+ " ]";
		RSSHomeItem elemento17 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico17) , numElem, R.drawable.home_imagebutton4 + "", data_agg, MefConstants.idRSS17);

		data_agg = db.getRSSLastUpdate(MefConstants.idRSS18);
		numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.idRSS18) + " - " + db.getTotRSSItemByIdURL(MefConstants.idRSS18)
				+ " ]";
		RSSHomeItem elemento18 = new RSSHomeItem(getString(R.string.title_activity_rssdetail_ico18) , numElem, R.drawable.home_imagebutton4 + "", data_agg, MefConstants.idRSS18);
		
//		Aggiungo gli elementi alla lista
		itemHomeList.add(elemento16);
		itemHomeList.add(elemento17);
		itemHomeList.add(elemento18);
		
		}
		
		
		nav.setItemHomeList(itemHomeList);
		
//		TODO da finire modificando il layout
		listViewHome = (ListView) findViewById( R.id.home_list);
		listViewHome.setAdapter( new RSSHomeListAdapter(this, R.layout.rss_home_list_adapter, itemHomeList ) );
        
//		Typeface gothicB = Typeface.createFromAsset(getAssets(), "fonts/gothicb.ttf"); 
	
//         Typeface gothic= Typeface.createFromFile("fonts/xml/GOTHIC.ttf"); 
         
		
//		Setto il listener alla lista
		OnItemClickListener itemClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// AdapterView is the parent class of ListView
				ListView lv = (ListView) arg0;

				RSSHomeItem item = (RSSHomeItem) listViewHome.getItemAtPosition(position);
			
				Intent intent =  new Intent(ctx, RSSList.class);
			
				
				NavigationBean nav = (NavigationBean)getApplication();
				nav.setCurrentRSS(position);
			
				nav.setMaxRSS(listViewHome.getCount());
				
				
//				TODO da sistemare 
				switch (position) {
				case MefConstants.idRSS1:
					intent.putExtra("idPulsante", MefConstants.idRSS1);
					break;
				case MefConstants.idRSS2:
					intent.putExtra("idPulsante", MefConstants.idRSS2);				
					break;
				case MefConstants.idRSS3:
					intent.putExtra("idPulsante", MefConstants.idRSS3);
					break;
				case MefConstants.idRSS4:
					intent.putExtra("idPulsante", MefConstants.idRSS4);
					break;
				case MefConstants.idRSS5:
					intent.putExtra("idPulsante", MefConstants.idRSS5);
					break;
				case MefConstants.idRSS6:
					intent.putExtra("idPulsante", MefConstants.idRSS6);
					break;
				case MefConstants.idRSS7:
					intent.putExtra("idPulsante", MefConstants.idRSS7);
					break;
				case MefConstants.idRSS8:
					intent.putExtra("idPulsante", MefConstants.idRSS8);
					break;
				case MefConstants.idRSS9:
					intent.putExtra("idPulsante", MefConstants.idRSS9);
					break;
				case MefConstants.idRSS10:
					intent.putExtra("idPulsante", MefConstants.idRSS10);
					break;
				case MefConstants.idRSS11:
					intent.putExtra("idPulsante", MefConstants.idRSS11);
					break;
				case MefConstants.idRSS12:
					intent.putExtra("idPulsante", MefConstants.idRSS12);
					break;
				case MefConstants.idRSS13:
					intent.putExtra("idPulsante", MefConstants.idRSS13);
					break;
				case MefConstants.idRSS14:
					intent.putExtra("idPulsante", MefConstants.idRSS14);
					break;
				case MefConstants.idRSS15:
					intent.putExtra("idPulsante", MefConstants.idRSS15);
					break;
				case MefConstants.idRSS16:
					intent.putExtra("idPulsante", MefConstants.idRSS16);
					break;
				case MefConstants.idRSS17:
					intent.putExtra("idPulsante", MefConstants.idRSS17);
					break;
				case MefConstants.idRSS18:
					intent.putExtra("idPulsante", MefConstants.idRSS18);
					break;
				default:
					break;
				}
				
			
				startActivity(intent);

			}

		};

		
		
		
		
		listViewHome.setOnItemClickListener(itemClickListener);
		
		db.closeDataBase();
		
		
		
		
		ImageButton imageBack= (ImageButton) findViewById(R.id.imageBackNav);
		imageBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent =  new Intent(ctx,  HomeActivity.class);
								
				NavigationBean nav = (NavigationBean)getApplication();
				int back = nav.getDipartimento() -1 ;
				
				
					nav.setDipartimento(back);
				
				startActivity(intent);
				
			}

		});


		ImageButton imageForward= (ImageButton) findViewById(R.id.imageForward);
		imageForward.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent =  new Intent(ctx, HomeActivity.class);
				
				NavigationBean nav = (NavigationBean)getApplication();
				int forward = nav.getDipartimento();
				nav.setDipartimento(forward+1);
				startActivity(intent);
			}

		});
		
	
		if (dipartimentoSelezionato > 1 && dipartimentoSelezionato <= 5 ) {
			imageBack.setVisibility(View.VISIBLE);
	    } else {
	    	imageBack.setVisibility(View.INVISIBLE);
	    }
		
		if (dipartimentoSelezionato < 5 && dipartimentoSelezionato >= 1) {
			imageForward.setVisibility(View.VISIBLE);
		} else {
			imageForward.setVisibility(View.INVISIBLE);
		}
	
		
		
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
			Toast.makeText(HomeActivity.this,
					"Sincronizzazione avviata", Toast.LENGTH_SHORT)
					.show();
			NavigationBean nav = (NavigationBean)getApplication();
			
			
			new UpdateRSS().execute(new Object [] {ctx, new Integer(nav.getDipartimento()) , new Integer(-1)} );
			startActivity(new Intent(this, HomeActivity.class));

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

//	@Override
//	public void onClick(View v) {
//
//		Intent intent;
//		switch (v.getId()) {
//		case R.id.homeImageButtonContact:
//
//			intent = new Intent(this, ContactActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.homeImageButtonPodcast:
//
//			intent = new Intent(this, PodcastActivity.class);
//			startActivity(intent);
//			break;

//		default:
//			NavigationBean nav = (NavigationBean) getApplication();
//			nav.setCurrentRSS(v.getId());
//			intent = new Intent(this, RSSList.class);
//			intent.putExtra("idPulsante", v.getId());
//			startActivity(intent);
//			break;
//		}
//
//	}
	
	@Override
	public void onBackPressed() {
		NavigationBean nav = (NavigationBean) getApplication();
		nav.setCurrentRSS(0);
		Intent intent = new Intent(this, HomeDipActivity.class);
		startActivity(intent);

	}


}
