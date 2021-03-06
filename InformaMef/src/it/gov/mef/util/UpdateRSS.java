package it.gov.mef.util;

import it.gov.mef.informamef.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class UpdateRSS extends AsyncTask<Object,String,String> {

	private Context ctx = null;
	Activity activity = null;
	ProgressBar prgBar = null;
	
	
	
	
	


	public UpdateRSS() {
		super();
		
	}
	
	public UpdateRSS(Activity activity) {
		super();
		this.activity = activity;
		
	}
	


	@Override
	protected String doInBackground(Object... arg0) {
		
		
		ctx = (Context)arg0[0];
		int dip = (Integer) arg0[1];
		int rss = (Integer) arg0[2];
		
		
		
		// Prendiamo dal context il ConnectivityManager
				ConnectivityManager connManager = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
				// Prendiamo le informazioni della connessione mobile
				NetworkInfo netInfo= connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				// Prendiamo le informazioni della connessione WiFi
				NetworkInfo wifiInfo= connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				
				if ( netInfo.getState() != NetworkInfo.State.CONNECTED && wifiInfo.getState() != NetworkInfo.State.CONNECTED ) 
				{
				    Log.d("HomeActivity", "Il telefono non � connesso ad internet");
				}
				else if(wifiInfo.getState() != NetworkInfo.State.CONNECTED)
				{
				    Log.d("HomeActivity", "I dati da scaricare sono molti, vi consigliamo di connettersi tramite WiFi");
				}
				
				
		MefDaoFactory db = new MefDaoFactory(ctx);
		try {

			db.openDataBase(true);
			
			List<Integer> feed = new ArrayList<Integer>();
			
			
			if (dip == -1 && rss == -1){
//				aggiorno tutti i dipartimenti
				feed = db.getRSSListURL();
			} else if (dip >= 0 &&  rss == -1){
//				aggiorno solo il singolo dipartimento
				
				switch (dip) {
				case MefConstants.MEF:
					feed = db.getRSSListURLByDesc(MefConstants.DESC_MEF);
					break;
				case MefConstants.DAG:
					feed = db.getRSSListURLByDesc(MefConstants.DESC_DAG);
					break;
				case MefConstants.DT:
					feed = db.getRSSListURLByDesc(MefConstants.DESC_DT);
					break;
				case MefConstants.RGS:
					feed = db.getRSSListURLByDesc(MefConstants.DESC_RGS);
					break;
				case MefConstants.INTRANET_DAG:
					feed = db.getRSSListURLByDesc(MefConstants.DESC_INTRANET_DAG);
					break;
				default:
					feed = db.getRSSListURLByDesc(MefConstants.DESC_MEF);
					break;
				}
				
			} else if (dip == -1 && rss >= 0){
//				aggiorno solo il singolo RSS
				Integer rssFeed = new Integer(db.getRSSUrlId(rss));
				feed.add(rssFeed);
			}
			
			
			
			
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
		
		return null;
	}


	@Override
	protected void onCancelled() {
		super.onCancelled();
		Log.d("UpdateRSS", "onCancelled");
		prgBar.setVisibility(View.GONE);
	}

//	@Override
//	protected void onCancelled(String result) {
//		super.onCancelled(result);
//		Log.d("UpdateRSS", "onCancelled(String result)");
//	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Log.d("UpdateRSS", "onPostExecute");
		prgBar.setVisibility(View.GONE);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.d("UpdateRSS", "onPreExecute");
		    
		prgBar =	(ProgressBar)activity.findViewById(R.id.download_bar);
		prgBar.setVisibility(View.VISIBLE);
			
	}

	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
		Log.d("UpdateRSS", "onProgressUpdate");
	}


}
