package it.gov.mef.informamef;

import it.gov.mef.util.MefConstants;
import it.gov.mef.util.MefDaoFactory;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationService extends Service {
	
private WakeLock mWakeLock;
    
    /**
     * Simply return null, since our Service will not be communicating with
     * any other components. It just does its work silently.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    /**
     * This is where we initialize. We call this when onStart/onStartCommand is
     * called by the system. We won't do anything with the intent here, and you
     * probably won't, either.
     */
    private void handleIntent(Intent intent) {
        // obtain the wake lock
        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TAGSERVICE");
        mWakeLock.acquire();
        
        // check the global background data setting
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (!cm.getBackgroundDataSetting()) {
            stopSelf();
            return;
        }
        
        // do the actual work, in a separate thread
        new PollTask().execute();
    }
    
    private class PollTask extends AsyncTask<Void, Void, Void> {
        /**
         * This is where YOU do YOUR work. There's nothing for me to write here
         * you have to fill this in. Make your HTTP request(s) or whatever it is
         * you have to do to get your updates in here, because this is run in a
         * separate thread
         */
        @Override
        protected Void doInBackground(Void... params) {
//            TODO Inserire il controllo per specializzare la notifica
        	Log.d("NOTIFICATION ACTIVITI", "Richiamata notitifca" + (new Date()).toString());
        	Context context = getApplicationContext();
        	MefDaoFactory db = new MefDaoFactory(context);
        	int count = 0;
        	StringBuffer strBuffer = new StringBuffer();
			int countMef = 0;
			int countDt = 0;
			int countRgs = 0;
			int countDag = 0;
			int countIntranet = 0;
			int countFinanze = 0;
        	
        	try {
				
				db.openDataBase(true);
				List<Integer> feed = db.getRSSListURL();
				Iterator<Integer> it = feed.iterator();
						
				
				while (it.hasNext()) {
					Integer elem = (Integer) it.next();
					
					count += db.updateRSSItem(elem.intValue());
					
					
					switch (elem.intValue()) {
							case MefConstants.MEF_ID_RSS1:
								countMef ++;
								break;
							case MefConstants.MEF_ID_RSS2:
								countMef ++;
								break;
							case MefConstants.MEF_ID_RSS3:
								countMef ++;
								break;
							case MefConstants.MEF_ID_RSS4:
								countMef ++;
								break;
							case MefConstants.MEF_ID_RSS5:
								countMef ++;
								break;
							case MefConstants.DT_ID_RSS1:
								countDt ++;
								break;
							case MefConstants.DT_ID_RSS2:
								countDt ++;
								break;
							case MefConstants.DT_ID_RSS3:
								countDt ++;
								break;
							case MefConstants.DT_ID_RSS4:
								countDt ++;
								break;
							case MefConstants.DT_ID_RSS5:
								countDt ++;
								break;
							case MefConstants.DT_ID_RSS6:
								countDt ++;
								break;
							case MefConstants.DT_ID_RSS7:
								countDt ++;
								break;
							case MefConstants.DT_ID_RSS8:
								countDt ++;
								break;
							case MefConstants.DAG_ID_RSS1:
								countDag ++;
								break;
							case MefConstants.RGS_ID_RSS1:
								countRgs ++;
								break;
							case MefConstants.INTRANET_DAG_ID_RSS1:
								countIntranet ++;
								break;
							case MefConstants.INTRANET_DAG_ID_RSS2:
								countIntranet ++;
								break;
							case MefConstants.INTRANET_DAG_ID_RSS3:
								countIntranet ++;
								break;
							case MefConstants.FINANZE_ID_RSS1:
								countFinanze ++;
								break;
							default:
		
								break;
					}
					
					
					
					
					Log.d(this.toString(),"Aggiornato id=" + elem.intValue());
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
        	
			if (count > 0) {
			
				strBuffer.append(getString(R.string.descrizione_notifica));
				
				if (countMef > 0)
					strBuffer.append(getString(R.string.title_home_dip_mef));
				if (countDag > 0)
					strBuffer.append(getString(R.string.title_home_dip_dag));
				if (countDt > 0)
					strBuffer.append(getString(R.string.title_home_dip_dt));
				if (countRgs > 0)
					strBuffer.append(getString(R.string.title_home_dip_rgs));
				if (countIntranet > 0)
					strBuffer.append(getString(R.string.title_home_dip_intranetdag));
				if (countFinanze > 0)
					strBuffer.append(getString(R.string.title_home_dip_finanze));
					
				
				
				NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

				NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
						context);

				// Titolo e testo della notifica
				notificationBuilder
						.setContentTitle(getString(R.string.title_notifica));
				notificationBuilder
						.setContentText(strBuffer.toString());

				// Testo che compare nella barra di stato non appena compare la
				// notifica
				notificationBuilder.setTicker(getString(R.string.tiker_notifica));

				// Data e ora della notifica
				notificationBuilder.setWhen(System.currentTimeMillis());

				// Icona della notifica
				notificationBuilder.setSmallIcon(R.drawable.ic_stat_name);

				// Creiamo il pending intent che verr� lanciato quando la
				// notifica
				// viene premuta
				Intent notificationIntent = new Intent(context,
						HomeDipActivity.class);
				PendingIntent contentIntent = PendingIntent.getActivity(
						context, 0, notificationIntent, 0);

				notificationBuilder.setContentIntent(contentIntent);

				// Impostiamo il suono, le luci e la vibrazione di default
				notificationBuilder.setDefaults(Notification.DEFAULT_SOUND
						| Notification.DEFAULT_LIGHTS
						| Notification.DEFAULT_VIBRATE
						| Notification.FLAG_AUTO_CANCEL);

//				Cancello quella precedente se c'�
				mNotificationManager.cancel(MefConstants.notifica);

				mNotificationManager.notify(MefConstants.notifica, notificationBuilder.build());

				Log.d(this.toString(), "doInBackground");
			}
        	
            return null;
        }
        
        /**
         * In here you should interpret whatever you fetched in doInBackground
         * and push any notifications you need to the status bar, using the
         * NotificationManager. I will not cover this here, go check the docs on
         * NotificationManager.
         *
         * What you HAVE to do is call stopSelf() after you've pushed your
         * notification(s). This will:
         * 1) Kill the service so it doesn't waste precious resources
         * 2) Call onDestroy() which will release the wake lock, so the device
         *    can go to sleep again and save precious battery.
         */
        @Override
        protected void onPostExecute(Void result) {
            // handle your data
            stopSelf();
//            onDestroy();
            
        }
    }
    
    /**
     * This is deprecated, but you have to implement it if you're planning on
     * supporting devices with an API level lower than 5 (Android 2.0).
     */
    @Override
    public void onStart(Intent intent, int startId) {
        handleIntent(intent);
    }
    
    /**
     * This is called on 2.0+ (API level 5 or higher). Returning
     * START_NOT_STICKY tells the system to not restart the service if it is
     * killed because of poor resource (memory/cpu) conditions.
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleIntent(intent);
        return START_NOT_STICKY;
    }
    
    /**
     * In onDestroy() we release our wake lock. This ensures that whenever the
     * Service stops (killed for resources, stopSelf() called, etc.), the wake
     * lock will be released.
     */
    public void onDestroy() {
        super.onDestroy();
        mWakeLock.release();
    }
    
}
