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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RSSList extends Activity {

	private ListView listViewRSS;
	private Context ctx;
//	private LinearLayout layout;
//	private int refreshList;
	private int idRSS;
	private String str_Title_activity_rsslist_ico = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rsslist);

		ctx = this;
		List<RSSItem> listRSS = new ArrayList<RSSItem>();
		try {


			NavigationBean nav = (NavigationBean) getApplication();
			List<RSSHomeItem> itemHomeList = nav.getItemHomeList();
			RSSHomeItem itemHome = itemHomeList.get(nav.getCurrentRSS());


			idRSS = itemHome.getIdRSS();

			MefDaoFactory db = new MefDaoFactory(this);
			db.openDataBase(false);

			// seleziono la URL da caricare
			switch (idRSS) {
			case MefConstants.MEF_ID_RSS1:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico1);
				setTitle(str_Title_activity_rsslist_ico);

				break;
			case MefConstants.MEF_ID_RSS2:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico2);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.MEF_ID_RSS3:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico3);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.MEF_ID_RSS4:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico4);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.MEF_ID_RSS5:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico5);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.DT_ID_RSS1:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico6);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.DT_ID_RSS2:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico7);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.DT_ID_RSS3:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico8);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.DT_ID_RSS4:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico9);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.DT_ID_RSS5:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico10);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.DT_ID_RSS6:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico11);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.DT_ID_RSS7:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico12);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.DT_ID_RSS8:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico13);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.DAG_ID_RSS1:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico14);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.RGS_ID_RSS1:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico15);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.INTRANET_DAG_ID_RSS1:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico16);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.INTRANET_DAG_ID_RSS2:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico17);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.INTRANET_DAG_ID_RSS3:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico18);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			case MefConstants.FINANZE_ID_RSS1:
				str_Title_activity_rsslist_ico = getResources().getString(
						R.string.title_activity_rssdetail_ico19);
				setTitle(str_Title_activity_rsslist_ico);
				break;
			default:
				this.setTitle("");
				break;
			}

			listRSS = db.getRSSList(itemHome.getIdRSS());
			nav.setListRSS(listRSS);
			// listRSS = db.getRSSList(idRSS);

			int dipartimentoSelezionato = nav.getDipartimento();
			String bTitle = null;
			int logo = 0;
			switch (dipartimentoSelezionato) {
			case MefConstants.MEF:
				bTitle = getString(R.string.title_home_mef);
				logo =  R.drawable.ic_head_logo_mef_9;
				break;
			case MefConstants.DAG:
				bTitle = getString(R.string.title_home_dag);
				logo =  R.drawable.ic_head_logo_dag_9;
				break;
			case MefConstants.RGS:
				bTitle = getString(R.string.title_home_rgs);
				logo =  R.drawable.ic_head_logo_rgs_9;
				break;
			case MefConstants.DT:
				bTitle = getString(R.string.title_home_dt);
				logo =  R.drawable.ic_head_logo_dt_9;
				break;
			case MefConstants.INTRANET_DAG:
				bTitle = getString(R.string.title_home_intranetdag);
				logo =  R.drawable.ic_head_logo_intranetdag_9;
				break;
			case MefConstants.FINANZE:
				bTitle = getString(R.string.title_home_finanze);
				logo =  R.drawable.ic_head_logo_finanze_9;
				break;
			default:
				bTitle = "";
				logo =  R.drawable.ic_head_logo_mef_9;
				break;

			}

			if (str_Title_activity_rsslist_ico != null) {
				FormatTitleBar.settingTitle(this, bTitle,
						logo,
						str_Title_activity_rsslist_ico);
			} else {
				FormatTitleBar.settingTitle(this, bTitle,
						R.drawable.ic_logo_intranetdag_9);
			}

			db.closeDataBase();
		} catch (Exception e) {

			e.printStackTrace();

		}

		listViewRSS = (ListView) findViewById(R.id.rss_list);
		listViewRSS.setAdapter(new RSSListAdapter(ctx,
				R.layout.rss_list_adapter, listRSS));

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
				int idUrl = item.getIdUrl();
				String guid = item.getGuid();
				String category = item.getCategory();



				Intent detailRSS = new Intent(ctx, RSSDetailActivity.class);


				RSSItem itemRSS = new RSSItem(titolo, descrizione, data, link,
						idItem, guid, category, idUrl);

				NavigationBean nav = (NavigationBean) getApplication();
				nav.setItemRSS(itemRSS);
				nav.setCurrentItemRSS(position);
				nav.setMaxItemRSS(listViewRSS.getCount());

				startActivity(detailRSS);

			}

		};

		// Setting the ItemClickEvent listener for the listview
		listViewRSS.setOnItemClickListener(itemClickListener);

		ImageButton imageBack = (ImageButton) findViewById(R.id.imageBackNav);
		imageBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(ctx, RSSList.class);

				NavigationBean nav = (NavigationBean) getApplication();
				int back = nav.getCurrentRSS() - 1;
				nav.setCurrentRSS(back);
				startActivity(intent);

			}

		});

		ImageButton imageForward = (ImageButton) findViewById(R.id.imageForward);
		imageForward.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ctx, RSSList.class);

				NavigationBean nav = (NavigationBean) getApplication();
				int forward = nav.getCurrentRSS() + 1;
				nav.setCurrentRSS(forward);
				startActivity(intent);
			}

		});

		boolean notBack = false;
		boolean notFw = false;
		NavigationBean nav = (NavigationBean) getApplication();
		if (nav.getCurrentRSS() > 0 && nav.getCurrentRSS() <= nav.getMaxRSS()) {
			imageBack.setVisibility(View.VISIBLE);
		} else {
			imageBack.setVisibility(View.INVISIBLE);
			notBack= true;
			
		}

		if (nav.getCurrentRSS() < nav.getMaxRSS()-1 && nav.getCurrentRSS() >= 0) {
			imageForward.setVisibility(View.VISIBLE);
		} else {
			imageForward.setVisibility(View.INVISIBLE);
			notFw = true;
		}
		
		if(notBack && notFw){
			RelativeLayout actionShare = (RelativeLayout)findViewById(R.id.actionShare);
			actionShare.setVisibility(View.INVISIBLE);
		}
		
		

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
			startActivity(new Intent(this, PrefsActivity.class));// start the
			
			return true;
		case R.id.action_refresh_list:

			// TODO Deve aggiornare solo RSS corrente
			Toast.makeText(this, "Sincronizzazione avviata", Toast.LENGTH_SHORT)
					.show();
			NavigationBean nav = (NavigationBean) getApplication();
			List<RSSHomeItem> itemHomeList = nav.getItemHomeList();
			RSSHomeItem itemHome = itemHomeList.get(nav.getCurrentRSS());

			  UpdateRSS updateRSS = new UpdateRSS(this);
            updateRSS.execute(new Object[] { ctx, new Integer(-1),
					new Integer(itemHome.getIdRSS()) });

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
