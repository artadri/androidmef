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

		List<RSSHomeItem> itemHomeList = new ArrayList<RSSHomeItem>();

		int dipartimentoSelezionato = nav.getDipartimento();

		Date data_agg = null;
		String numElem = "";

		if (MefConstants.MEF == dipartimentoSelezionato) {

			FormatTitleBar.settingTitle(this,
					getString(R.string.title_home_mef),
					R.drawable.ic_head_logo_mef_9);

			// MEF
			data_agg = db.getRSSLastUpdate(MefConstants.MEF_ID_RSS1);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.MEF_ID_RSS1)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.MEF_ID_RSS1)
					+ " ]";
			RSSHomeItem elemento = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico1), numElem,
					R.drawable.ic_news_9 + "", data_agg, MefConstants.MEF_ID_RSS1);

			data_agg = db.getRSSLastUpdate(MefConstants.MEF_ID_RSS2);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.MEF_ID_RSS2)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.MEF_ID_RSS2)
					+ " ]";
			RSSHomeItem elemento2 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico2), numElem,
					R.drawable.ic_docum_9 + "", data_agg, MefConstants.MEF_ID_RSS2);

			data_agg = db.getRSSLastUpdate(MefConstants.MEF_ID_RSS3);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.MEF_ID_RSS3)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.MEF_ID_RSS3)
					+ " ]";
			RSSHomeItem elemento3 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico3), numElem,
					R.drawable.ic_comunicati_9 + "", data_agg,
					MefConstants.MEF_ID_RSS3);

			data_agg = db.getRSSLastUpdate(MefConstants.MEF_ID_RSS4);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.MEF_ID_RSS4)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.MEF_ID_RSS4)
					+ " ]";
			RSSHomeItem elemento4 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico4), numElem,
					R.drawable.ic_bandi_9 + "", data_agg, MefConstants.MEF_ID_RSS4);

			data_agg = db.getRSSLastUpdate(MefConstants.MEF_ID_RSS5);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.MEF_ID_RSS5)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.MEF_ID_RSS5)
					+ " ]";
			RSSHomeItem elemento5 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico5), numElem,
					R.drawable.ic_concorsi_9 + "", data_agg, MefConstants.MEF_ID_RSS5);

			// Aggiungo gli elementi alla lista
			itemHomeList.add(elemento);
			itemHomeList.add(elemento2);
			itemHomeList.add(elemento3);
			itemHomeList.add(elemento4);
			itemHomeList.add(elemento5);

		}

		if (MefConstants.DT == dipartimentoSelezionato) {

			// DT
			FormatTitleBar.settingTitle(this,
					getString(R.string.title_home_dt), R.drawable.ic_head_logo_dt_9);

			data_agg = db.getRSSLastUpdate(MefConstants.DT_ID_RSS1);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.DT_ID_RSS1)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.DT_ID_RSS1)
					+ " ]";
			RSSHomeItem elemento6 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico6), numElem,
					R.drawable.ic_news_9 + "", data_agg,
					MefConstants.DT_ID_RSS1);

			data_agg = db.getRSSLastUpdate(MefConstants.DT_ID_RSS2);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.DT_ID_RSS2)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.DT_ID_RSS2)
					+ " ]";
			RSSHomeItem elemento7 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico7), numElem,
					R.drawable.ic_calendario_9 + "", data_agg,
					MefConstants.DT_ID_RSS2);

			data_agg = db.getRSSLastUpdate(MefConstants.DT_ID_RSS3);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.DT_ID_RSS3)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.DT_ID_RSS3)
					+ " ]";
			RSSHomeItem elemento8 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico8), numElem,
					R.drawable.ic_calendario_9 + "", data_agg, MefConstants.DT_ID_RSS3);

			data_agg = db.getRSSLastUpdate(MefConstants.DT_ID_RSS4);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.DT_ID_RSS4)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.DT_ID_RSS4)
					+ " ]";
			RSSHomeItem elemento9 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico9), numElem,
					R.drawable.ic_btpctz_9 + "", data_agg, MefConstants.DT_ID_RSS4);

			data_agg = db.getRSSLastUpdate(MefConstants.DT_ID_RSS5);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.DT_ID_RSS5)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.DT_ID_RSS5)
					+ " ]";
			RSSHomeItem elemento10 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico10),
					numElem, R.drawable.ic_btpctz_9 + "", data_agg,
					MefConstants.DT_ID_RSS5);

			data_agg = db.getRSSLastUpdate(MefConstants.DT_ID_RSS6);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.DT_ID_RSS6)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.DT_ID_RSS6)
					+ " ]";
			RSSHomeItem elemento11 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico11),
					numElem, R.drawable.ic_btpctz_9 + "", data_agg,
					MefConstants.DT_ID_RSS6);

			data_agg = db.getRSSLastUpdate(MefConstants.DT_ID_RSS7);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.DT_ID_RSS7)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.DT_ID_RSS7)
					+ " ]";
			RSSHomeItem elemento12 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico12),
					numElem, R.drawable.ic_btpctz_9 + "", data_agg,
					MefConstants.DT_ID_RSS7);

			data_agg = db.getRSSLastUpdate(MefConstants.DT_ID_RSS8);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.DT_ID_RSS8)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.DT_ID_RSS8)
					+ " ]";
			RSSHomeItem elemento13 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico13),
					numElem, R.drawable.ic_btpctz_9 + "", data_agg,
					MefConstants.DT_ID_RSS8);

			// Aggiungo gli elementi alla lista
			itemHomeList.add(elemento6);
			itemHomeList.add(elemento7);
			itemHomeList.add(elemento8);
			itemHomeList.add(elemento9);
			itemHomeList.add(elemento10);
			itemHomeList.add(elemento11);
			itemHomeList.add(elemento12);
			itemHomeList.add(elemento13);

		}

		if (MefConstants.DAG == dipartimentoSelezionato) {

			// DAG
			FormatTitleBar.settingTitle(this,
					getString(R.string.title_home_dag),
					R.drawable.ic_head_logo_dag_9);
			data_agg = db.getRSSLastUpdate(MefConstants.DAG_ID_RSS1);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.DAG_ID_RSS1)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.DAG_ID_RSS1)
					+ " ]";
			RSSHomeItem elemento14 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico14),
					numElem, R.drawable.ic_news_9 + "", data_agg,
					MefConstants.DAG_ID_RSS1);

			// Aggiungo gli elementi alla lista

			itemHomeList.add(elemento14);

		}

		if (MefConstants.RGS == dipartimentoSelezionato) {

			// RGS
			FormatTitleBar.settingTitle(this,
					getString(R.string.title_home_rgs),
					R.drawable.ic_head_logo_rgs_9);

			data_agg = db.getRSSLastUpdate(MefConstants.RGS_ID_RSS1);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.RGS_ID_RSS1)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.RGS_ID_RSS1)
					+ " ]";
			RSSHomeItem elemento15 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico15),
					numElem, R.drawable.ic_news_9 + "", data_agg,
					MefConstants.RGS_ID_RSS1);

			// Aggiungo gli elementi alla lista
			itemHomeList.add(elemento15);

		}
		
		if (MefConstants.FINANZE == dipartimentoSelezionato) {

			// DAG
			FormatTitleBar.settingTitle(this,
					getString(R.string.title_home_finanze),
					R.drawable.ic_head_logo_finanze_9);
			data_agg = db.getRSSLastUpdate(MefConstants.FINANZE_ID_RSS1);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.FINANZE_ID_RSS1)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.FINANZE_ID_RSS1)
					+ " ]";
			RSSHomeItem elemento19 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico19),
					numElem, R.drawable.ic_news_9 + "", data_agg,
					MefConstants.FINANZE_ID_RSS1);

			// Aggiungo gli elementi alla lista

			itemHomeList.add(elemento19);

		}


		if (MefConstants.INTRANET_DAG == dipartimentoSelezionato) {

			// INTRANET
			FormatTitleBar.settingTitle(this,
					getString(R.string.title_home_intranetdag),
					R.drawable.ic_head_logo_intranetdag_9);
			data_agg = db.getRSSLastUpdate(MefConstants.INTRANET_DAG_ID_RSS1);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.INTRANET_DAG_ID_RSS1)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.INTRANET_DAG_ID_RSS1)
					+ " ]";
			RSSHomeItem elemento16 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico16),
					numElem, R.drawable.ic_news_9 + "", data_agg,
					MefConstants.INTRANET_DAG_ID_RSS1);

			data_agg = db.getRSSLastUpdate(MefConstants.INTRANET_DAG_ID_RSS2);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.INTRANET_DAG_ID_RSS2)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.INTRANET_DAG_ID_RSS2)
					+ " ]";
			RSSHomeItem elemento17 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico17),
					numElem, R.drawable.ic_lexnews_9 + "", data_agg,
					MefConstants.INTRANET_DAG_ID_RSS2);

			data_agg = db.getRSSLastUpdate(MefConstants.INTRANET_DAG_ID_RSS3);
			numElem = "[ " + db.getTotRSSItemNotRead(MefConstants.INTRANET_DAG_ID_RSS3)
					+ " - " + db.getTotRSSItemByIdURL(MefConstants.INTRANET_DAG_ID_RSS3)
					+ " ]";
			RSSHomeItem elemento18 = new RSSHomeItem(
					getString(R.string.title_activity_rssdetail_ico18),
					numElem, R.drawable.ic_lexnews_9 + "", data_agg,
					MefConstants.INTRANET_DAG_ID_RSS3);

			// Aggiungo gli elementi alla lista
			itemHomeList.add(elemento16);
			itemHomeList.add(elemento17);
			itemHomeList.add(elemento18);

		}
		
		
		nav.setItemHomeList(itemHomeList);

		// TODO da finire modificando il layout
		listViewHome = (ListView) findViewById(R.id.home_list);
		listViewHome.setAdapter(new RSSHomeListAdapter(this,
				R.layout.rss_home_list_adapter, itemHomeList));


		// Setto il listener alla lista
		OnItemClickListener itemClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// AdapterView is the parent class of ListView
				ListView lv = (ListView) arg0;

				RSSHomeItem item = (RSSHomeItem) listViewHome
						.getItemAtPosition(position);

				Intent intent = new Intent(ctx, RSSList.class);

				NavigationBean nav = (NavigationBean) getApplication();
				nav.setCurrentRSS(position);

				nav.setMaxRSS(listViewHome.getCount());



				startActivity(intent);

			}

		};

		listViewHome.setOnItemClickListener(itemClickListener);

		db.closeDataBase();

		ImageButton imageBack = (ImageButton) findViewById(R.id.imageBackNav);
		imageBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(ctx, HomeActivity.class);

				NavigationBean nav = (NavigationBean) getApplication();
				int back = nav.getDipartimento() - 1;

				nav.setDipartimento(back);

				startActivity(intent);

			}

		});

		ImageButton imageForward = (ImageButton) findViewById(R.id.imageForward);
		imageForward.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ctx, HomeActivity.class);

				NavigationBean nav = (NavigationBean) getApplication();
				int forward = nav.getDipartimento();
				nav.setDipartimento(forward + 1);
				startActivity(intent);
			}

		});

		if (dipartimentoSelezionato > 1 && dipartimentoSelezionato <= MefConstants.NUMERO_DIPARTIMENTI) {
			imageBack.setVisibility(View.VISIBLE);
		} else {
			imageBack.setVisibility(View.INVISIBLE);
		}

		if (dipartimentoSelezionato < MefConstants.NUMERO_DIPARTIMENTI && dipartimentoSelezionato >= 1) {
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
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.action_settings:
			startActivity(new Intent(this, PrefsActivity.class));
			
			return true;
		case R.id.action_refresh_list:
			Toast.makeText(HomeActivity.this, "Sincronizzazione avviata",
					Toast.LENGTH_SHORT).show();
			NavigationBean nav = (NavigationBean) getApplication();

			new UpdateRSS().execute(new Object[] { ctx,
					new Integer(nav.getDipartimento()), new Integer(-1) });
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

	@Override
	public void onBackPressed() {
		NavigationBean nav = (NavigationBean) getApplication();
		nav.setCurrentRSS(0);
		Intent intent = new Intent(this, HomeDipActivity.class);
		startActivity(intent);

	}

}
