package it.gov.mef.informamef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {

	// Definisco le img botton
	ImageButton imageButton1;
	ImageButton imageButton2;
	ImageButton imageButton3;
	ImageButton imageButton4;
	ImageButton imageButtonContact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		// Associo i listener ai bottoni

		imageButton1 = (ImageButton) findViewById(R.id.homeImageButton1);
		imageButton1.setOnClickListener(this);

		imageButton2 = (ImageButton) findViewById(R.id.homeImageButton2);
		imageButton2.setOnClickListener(this);

		imageButton3 = (ImageButton) findViewById(R.id.homeImageButton3);
		imageButton3.setOnClickListener(this);

		imageButton4 = (ImageButton) findViewById(R.id.homeImageButton4);
		imageButton4.setOnClickListener(this);

		imageButtonContact = (ImageButton) findViewById(R.id.homeImageButtonContact);
		imageButtonContact.setOnClickListener(this);

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("", "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("", "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d("", "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("", "onStop");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("", "onRestart");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("", "onDestroy");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	
	 /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
 
        switch (item.getItemId()) 
        {
        case R.id.item_prefs:
            // Single menu item is selected do something
            // Ex: launching new activity/screen or show alert message
            Toast.makeText(HomeActivity.this, "Bookmark is Selected item_prefs", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.action_settings:
          	startActivity(new Intent(this, PrefsActivity.class));//start the PrefsActivity.java
          	return true;
		default:
            return super.onOptionsItemSelected(item);
        }
    }    
    

	@Override
	public void onClick(View v) {

		// // visualizzazione messaggio
		// Toast.makeText(this, "ID pulsante selezionato" + v.getId() ,
		// Toast.LENGTH_SHORT)
		// .show();
		//
		Intent intent ;
		switch (v.getId()) {
		case R.id.homeImageButtonContact:

			intent = new Intent(this, ContactActivity.class);
			startActivity(intent);
			break;

		default:
			// Intent intent = new Intent(this, RSSListActivity.class);
			intent = new Intent(this, RSSList.class);
			intent.putExtra("idPulsante", v.getId());
			startActivity(intent);
			break;
		}

	}

}
