package it.gov.mef.informamef;

import it.gov.mef.util.MefDaoFactory;
import it.gov.mef.util.NavigationBean;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private LayoutParams layoutParams = new LayoutParams(
			LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	private LayoutParams textParams = new LayoutParams(
			LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	private MainActivity _this;
	private LinearLayout layout;
	private Handler mHandler;

	protected static final int FINISH_LOAD = 0;
	protected static final int START_LOAD = 1;
	protected static final int ABORT_LOAD = 2;
	


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_this = this;

	
		
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case FINISH_LOAD:
					setContentView(layout);
					SplashScreen.sendMessage(SplashScreen.CLOSE_SPLASH);
					setContentView(R.layout.activity_home_dip);

					break;
				case START_LOAD:
					initializing();
					break;
				case ABORT_LOAD:
					finish();
				}

			}
		};
		startSplash();
		Intent intent = new Intent(this, HomeDipActivity.class);
		startActivity(intent);

	}

	private void startSplash() {
		Intent intent = new Intent(this, SplashScreen.class);
		SplashScreen.setMainHandler(mHandler);
		startActivity(intent);
	}

	private void initializing() {
		new Thread() {
			@Override
			public void run() {

				layout = new LinearLayout(_this);
				layout.setLayoutParams(layoutParams);
				layout.setOrientation(LinearLayout.VERTICAL);

				
				
//				TODO aggiungere aggiornamento dati al primo caricamento
//				MefDaoFactory db = new MefDaoFactory(_this);
//				db.openDataBase(true);
//				
//				int countItem = db.getCountRSSItem();
//				
//
//				if (countItem == 0){
//					Log.d("Main Activity", "nessun item caricato");
//				} else{
//					Log.d("Main Activity", "item caricati");
//				}
				
				
				
				for (int i = 0; i < 1000; i++) {

					/* Necessario per far avanzare la progress bar */
					Message msg = new Message();
					msg.what = SplashScreen.SET_PROGRESS;
					msg.arg1 = i;
					SplashScreen.sendMessage(msg);
				}

				
				mHandler.sendEmptyMessage(FINISH_LOAD);

			}

		}.start();
	}
	
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(MainActivity.class.getName(), "onStart");
	}

	@Override
	protected void onResume() {
		
			super.onResume();
		    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		    int minutes = Integer.parseInt(prefs.getString("prefSyncFrequency", "0"));
		    
		    
		    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		    Intent i = new Intent(this, NotificationService.class);
		    PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
		    am.cancel(pi);
		    // by my own convention, minutes <= 0 means notifications are disabled
		    if (minutes > 0) {
		        am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
		            SystemClock.elapsedRealtime() + minutes*60*1000,
		            minutes*60*1000, pi);
		    }
		
		
		Log.d(MainActivity.class.getName(), "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(MainActivity.class.getName(), "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(MainActivity.class.getName(), "onStop");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(MainActivity.class.getName(), "onRestart");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(MainActivity.class.getName(), "onDestroy");
	}

	

}
