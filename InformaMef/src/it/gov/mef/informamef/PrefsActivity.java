package it.gov.mef.informamef;

import java.util.Locale;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Configuration;
import android.view.Menu;

public class PrefsActivity extends PreferenceActivity {

	SharedPreferences prefs;
	OnSharedPreferenceChangeListener listener;
	Context ctx;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		ctx = this;

		prefs = PreferenceManager
				.getDefaultSharedPreferences(PrefsActivity.this);
		;
		listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
			public void onSharedPreferenceChanged(SharedPreferences prefs,
					String key) {
				int flag = 1;

				String language = prefs.getString("userLanguageValues", "it");
				Locale locale = new Locale(language);
				Locale.setDefault(locale);
				Configuration conf = new Configuration();
				conf.locale = locale;
//				ctx.getResources().updateConfiguration(conf, null);
				getBaseContext().getResources().updateConfiguration(conf, null);
				ctx.getResources().updateConfiguration(conf, null);

			}
		};

		prefs.registerOnSharedPreferenceChangeListener(listener);
		super.onCreate(null);
		// super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);// instead of setContentView()

	}

}
