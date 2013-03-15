package it.gov.mef.informamef;

import it.gov.mef.util.FormatActionBar;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class ContactActivity extends Activity {

	
	
	
	 Button btnOK;
	    EditText txtTo;
	    EditText txtSubject;
	    EditText txtMessage;

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_contact);
	        
			// Show the Up button in the action bar.
			setupActionBar();
			
			FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome, R.id.imageBack, R.string.actionBarTitle, false);
			
	        btnOK = (Button) findViewById(R.id.btnOK);
	        txtTo = (EditText) findViewById(R.id.etTo);
	        txtTo.requestFocus();
	        txtSubject = (EditText) findViewById(R.id.etSubject);
	        txtMessage = (EditText) findViewById(R.id.etMessage);
		btnOK.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                String to = txtTo.getText().toString();
	                String subject = txtSubject.getText().toString();
	                String message = txtMessage.getText().toString();
	                Intent mail = new Intent(Intent.ACTION_SEND);
	                mail.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
	                mail.putExtra(Intent.EXTRA_SUBJECT, subject);
	                mail.putExtra(Intent.EXTRA_TEXT, message);
	                mail.setType("message/rfc822");
	                startActivity(Intent.createChooser(mail, "Send email via:"));
	            }
	        });
		
		
		
		
		
		
		
		
	    }
	    
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
