package it.gov.mef.informamef;

import java.util.Locale;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.app.Activity;
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
		

		prefs = PreferenceManager
				.getDefaultSharedPreferences(PrefsActivity.this);
		;
		listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
			public void onSharedPreferenceChanged(SharedPreferences prefs1,
					String key) {
				int flag = 1;

				String localLanguage = prefs.getString("userLanguageValues", "it");
				setLocale(localLanguage);
				

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
		 Intent refresh = new Intent(this, HomeActivity.class);
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
