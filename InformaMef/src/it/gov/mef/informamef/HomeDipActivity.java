package it.gov.mef.informamef;

import java.util.ArrayList;
import java.util.List;

import it.gov.mef.informamef.R.menu;
import it.gov.mef.util.FormatTitleBar;
import it.gov.mef.util.MefConstants;
import it.gov.mef.util.NavigationBean;
import it.gov.mef.util.RSSHomeDipItem;
import it.gov.mef.util.RSSHomeItem;
import it.gov.mef.util.RSSItem;
import it.gov.mef.util.UpdateRSS;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HomeDipActivity extends Activity {

	private Context ctx = null;
	private ListView listViewDip;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_dip);
		ctx = this;
		FormatTitleBar.settingTitle(this, "Notizie dal MEF" ); 
		
		List<RSSHomeDipItem> listDip = new ArrayList<RSSHomeDipItem>();
		
		RSSHomeDipItem elem = new RSSHomeDipItem(getString(R.string.title_home_dip_mef), getString(R.string.descrizione_home_dip_mef), R.drawable.ic_logo_mef_9, MefConstants.MEF );
		RSSHomeDipItem elem1 = new RSSHomeDipItem(getString(R.string.title_home_dip_dag), getString(R.string.descrizione_home_dip_dag), R.drawable.ic_logo_dag_9, MefConstants.DAG );
		RSSHomeDipItem elem2 = new RSSHomeDipItem(getString(R.string.title_home_dip_dt), getString(R.string.descrizione_home_dip_dt), R.drawable.ic_logo_dt_9, MefConstants.DT );
		RSSHomeDipItem elem3 = new RSSHomeDipItem(getString(R.string.title_home_dip_rgs), getString(R.string.descrizione_home_dip_rgs), R.drawable.ic_logo_rgs_9, MefConstants.RGS );
		RSSHomeDipItem elem4 = new RSSHomeDipItem(getString(R.string.title_home_dip_intranetdag), getString(R.string.descrizione_home_dip_intranetdag), R.drawable.ic_logo_intranetdag_9, MefConstants.INTRANET_DAG );
		
		listDip.add(elem);
		listDip.add(elem1);
		listDip.add(elem2);
		listDip.add(elem3);
		listDip.add(elem4);
		
		listViewDip = (ListView) findViewById(R.id.rss_dip_list);
		listViewDip.setAdapter(new RSSHomeDipListAdapter(ctx, R.layout.rss_list_dip_adapter, listDip));
		
//		Setto il listener alla lista
		OnItemClickListener itemClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// AdapterView is the parent class of ListView
				ListView lv = (ListView) arg0;
				RSSHomeDipItem item = (RSSHomeDipItem) lv.getItemAtPosition(position);
				
				int dip = item.getIdDip();
				NavigationBean nav = (NavigationBean)getApplication();
				
				switch (dip) {
				case MefConstants.MEF:
					nav.setDipartimento(MefConstants.MEF);
					break;
				case MefConstants.DAG:
					nav.setDipartimento(MefConstants.DAG);
					break;
				case MefConstants.DT:
					nav.setDipartimento(MefConstants.DT);
					break;
				case MefConstants.RGS:
					nav.setDipartimento(MefConstants.RGS);
					break;
				case MefConstants.INTRANET_DAG:
					nav.setDipartimento(MefConstants.INTRANET_DAG);
					break;
				default:
					nav.setDipartimento(MefConstants.MEF);
					break;
				}
			
				
				Intent intent = new Intent(ctx, HomeActivity.class);		
				startActivity(intent);
				
				
			}
		};

		listViewDip.setOnItemClickListener(itemClickListener);
		
		
//		Gestione della selezione del dipartimento
        
        
//        ImageButton homeImageButton1= (ImageButton) findViewById(R.id.homeImageButton1);
//        homeImageButton1.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				
//				NavigationBean nav = (NavigationBean)getApplication();
//				nav.setDipartimento(MefConstants.MEF);
//				Intent intent = new Intent(ctx, HomeActivity.class);		
//				startActivity(intent);
//			}
//
//		});
//        

//        ImageButton homeImageButton2= (ImageButton) findViewById(R.id.homeImageButton2);
//        homeImageButton2.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				
//				NavigationBean nav = (NavigationBean)getApplication();
//				nav.setDipartimento(MefConstants.DAG);
//				Intent intent = new Intent(ctx, HomeActivity.class);		
//				startActivity(intent);
//			}
//
//		});
//
//        ImageButton homeImageButton3= (ImageButton) findViewById(R.id.homeImageButton3);
//        homeImageButton3.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				
//				NavigationBean nav = (NavigationBean)getApplication();
//				nav.setDipartimento(MefConstants.DT);
//				Intent intent = new Intent(ctx, HomeActivity.class);		
//				startActivity(intent);
//			}
//
//		});
        

//        ImageButton homeImageButton4= (ImageButton) findViewById(R.id.homeImageButton4);
//        homeImageButton4.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				
//				NavigationBean nav = (NavigationBean)getApplication();
//				nav.setDipartimento(MefConstants.RGS);
//				Intent intent = new Intent(ctx, HomeActivity.class);		
//				startActivity(intent);
//			}
//
//		});
//
//
//        ImageButton homeImageButton5= (ImageButton) findViewById(R.id.homeImageButton5);
//        homeImageButton5.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				
//				NavigationBean nav = (NavigationBean)getApplication();
//				nav.setDipartimento(MefConstants.INTRANET_DAG);
//				Intent intent = new Intent(ctx, HomeActivity.class);		
//				startActivity(intent);
//			}
//
//		});
        
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		
		case R.id.action_settings:
			startActivity(new Intent(this, PrefsActivity.class));// start the
																	// PrefsActivity.java
			// startActivity(new Intent(this, TestDBActivity.class));//start the
			// PrefsActivity.java
			return true;
		case R.id.action_refresh_list:
			Toast.makeText(this,
					"Sincronizzazione avviata", Toast.LENGTH_SHORT)
					.show();
			new UpdateRSS().execute(new Object [] {ctx, new Integer(-1), new Integer(-1) } );
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
	
}
