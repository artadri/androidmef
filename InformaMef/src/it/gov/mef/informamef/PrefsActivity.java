package it.gov.mef.informamef;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;

public class PrefsActivity extends PreferenceActivity {

	SharedPreferences prefs;

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);//instead of setContentView()
		
		
	}
	
	
	


}
