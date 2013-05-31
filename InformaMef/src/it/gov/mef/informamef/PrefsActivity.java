package it.gov.mef.informamef;

import java.util.Locale;

import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;

public class PrefsActivity extends PreferenceActivity {

	SharedPreferences prefs;
	OnSharedPreferenceChangeListener listener;
	Context ctx;
	Locale myLocale;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		ctx = this;
		

		prefs = PreferenceManager.getDefaultSharedPreferences(PrefsActivity.this);
		
		
		listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
			public void onSharedPreferenceChanged(SharedPreferences prefs1,
					String key) {
				int flag = 1;

				String localLanguage = prefs.getString("userLanguageValues", "it");
				setLocale(localLanguage);
				
//				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//				startActivities(intent);
				

			}

			private void startActivities(Intent intent) {
				// TODO Auto-generated method stub
				
			}
		};

		prefs.registerOnSharedPreferenceChangeListener(listener);
		super.onCreate(null);
		// super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);// instead of setContentView()

	}
	
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d("MEFPrefsActivity", "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("MEFPrefsActivity", "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		
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
		    
		Log.d("MEFPrefsActivity", "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("MEFPrefsActivity", "onStop");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("MEFPrefsActivity", "onRestart");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		 Intent refresh = new Intent(this, HomeDipActivity.class);
	    startActivity(refresh);
		Log.d(PrefsActivity.class.getName() + "onDestroy", "Richiamato metodo");
	}
	
	
	
	public void setLocale(String lang) {
		 
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
       
    }
	
	

}
