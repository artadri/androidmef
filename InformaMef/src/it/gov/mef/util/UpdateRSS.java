package it.gov.mef.util;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

public class UpdateRSS extends AsyncTask<Context,String,String> {

	@Override
	protected String doInBackground(Context... arg0) {
		
		
		Context ctx = arg0[0];
		
		// Prendiamo dal context il ConnectivityManager
				ConnectivityManager connManager = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
				// Prendiamo le informazioni della connessione mobile
				NetworkInfo netInfo= connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				// Prendiamo le informazioni della connessione WiFi
				NetworkInfo wifiInfo= connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				
				if ( netInfo.getState() != NetworkInfo.State.CONNECTED && wifiInfo.getState() != NetworkInfo.State.CONNECTED ) 
				{
				    Log.d("HomeActivity", "Il telefono non è connesso ad internet");
				}
				else if(wifiInfo.getState() != NetworkInfo.State.CONNECTED)
				{
				    Log.d("HomeActivity", "I dati da scaricare sono molti, vi consigliamo di connettersi tramite WiFi");
				}
				
				
		MefDaoFactory db = new MefDaoFactory(ctx);
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
		
		return null;
	}


}
