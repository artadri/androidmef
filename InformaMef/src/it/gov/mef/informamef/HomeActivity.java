package it.gov.mef.informamef;

import it.gov.mef.util.MefDaoFactory;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {

	// Definisco le img botton
	ImageButton imageButton1;
	ImageButton imageButton2;
	ImageButton imageButton3;
	ImageButton imageButton4;
	ImageButton imageButtonContact;
	ImageButton imageButtonPodcast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		// Associo i listener ai bottoni

		// // visualizzazione messaggio
//				 Toast.makeText(this, "" + this.getResources().getConfiguration().locale.getDisplayName() ,
//				 Toast.LENGTH_SHORT)
//				 .show();
//		startActivity(new Intent(this, NotificationActivity.class));
		

		MefDaoFactory db = new MefDaoFactory(this);
		db.openDataBase(false);
		
		
		TextView txt1 = (TextView)findViewById(R.id.homeTextNumElem1);
		String txtReplace = txt1.getText().toString();
		txtReplace = txtReplace.replace("$read", "0");
		txtReplace = txtReplace.replace("$tot", Integer.toString(db.getTotRSS(R.id.homeImageButton1)));
		txt1.setText(txtReplace);
		
		imageButton1 = (ImageButton) findViewById(R.id.homeImageButton1);
		imageButton1.setOnClickListener(this);
		
		
		TextView txt2 = (TextView)findViewById(R.id.homeTextNumElem2);
		String txtReplace2 = txt2.getText().toString();
		txtReplace2 = txtReplace2.replace("$read", "0");
		txtReplace2 = txtReplace2.replace("$tot", Integer.toString(db.getTotRSS(R.id.homeImageButton2)));
		txt2.setText(txtReplace2);


		imageButton2 = (ImageButton) findViewById(R.id.homeImageButton2);
		imageButton2.setOnClickListener(this);
		
		
		TextView txt3 = (TextView)findViewById(R.id.homeTextNumElem3);
		String txtReplace3 = txt3.getText().toString();
		txtReplace3 = txtReplace3.replace("$read", "0");
		txtReplace3 = txtReplace3.replace("$tot", Integer.toString(db.getTotRSS(R.id.homeImageButton3)));
		txt3.setText(txtReplace3);


		imageButton3 = (ImageButton) findViewById(R.id.homeImageButton3);
		imageButton3.setOnClickListener(this);
		
		TextView txt4 = (TextView)findViewById(R.id.homeTextNumElem4);
		String txtReplace4 = txt4.getText().toString();
		txtReplace4 = txtReplace4.replace("$read", "0");
		txtReplace4 = txtReplace4.replace("$tot", Integer.toString(db.getTotRSS(R.id.homeImageButton4)));
		txt4.setText(txtReplace4);


		imageButton4 = (ImageButton) findViewById(R.id.homeImageButton4);
		imageButton4.setOnClickListener(this);

		imageButtonContact = (ImageButton) findViewById(R.id.homeImageButtonContact);
		imageButtonContact.setOnClickListener(this);
		
		imageButtonPodcast = (ImageButton) findViewById(R.id.homeImageButtonPodcast);
		imageButtonPodcast.setOnClickListener(this);
		
		db.closeDataBase();
	
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("MEFHomeActivity", "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("MEFHomeActivity", "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d("MEFHomeActivity", "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("MEFHomeActivity", "onStop");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("MEFHomeActivity", "onRestart");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("MEFHomeActivity", "onDestroy");
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

		
		Intent intent ;
		switch (v.getId()) {
		case R.id.homeImageButtonContact:

			intent = new Intent(this, ContactActivity.class);
			startActivity(intent);
			break;
		case R.id.homeImageButtonPodcast:

			intent = new Intent(this, PodcastActivity.class);
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
