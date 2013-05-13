package it.gov.mef.informamef;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.gov.mef.util.FormatActionBar;
import it.gov.mef.util.MefDaoFactory;
import it.gov.mef.util.UpdateRSS;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RSSDetailActivity extends Activity implements OnTouchListener {

//	private int idPulsante;
	private String titolo;
	private String descrizione;
	private String link;
	private String data;
	private int idItem ;
	private int idUrl ;
	private String guid  ;
	private String category ;
	private int idPulsante;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rssdetail);

		titolo = getIntent().getStringExtra("titolo");
		descrizione = getIntent().getStringExtra("descrizione");
		link = getIntent().getStringExtra("link");
		data = getIntent().getStringExtra("data");
		idPulsante = getIntent().getIntExtra("idPulsante", 0);
		
		
		idItem = getIntent().getIntExtra("idItemRSS", 0);
		idUrl = getIntent().getIntExtra("idUrl",0); ;
		guid  = getIntent().getStringExtra("guid");
		category = getIntent().getStringExtra("data");
		

		FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,
				R.id.imageBack, R.string.detailRSSTitle, true);
		
        Typeface gothicB=Typeface.createFromAsset(getAssets(), "fonts/gothicb.ttf"); 
        Typeface gothic=Typeface.createFromAsset(getAssets(), "fonts/gothic.ttf"); 
		
		TextView txtTitolo = (TextView) findViewById(R.id.rssDetailTitolo);
		txtTitolo.setTypeface(gothicB);
		txtTitolo.setText(titolo);
		TextView txtData = (TextView) findViewById(R.id.rssDetailData);
		txtData.setTypeface(gothic);
		txtData.setText(data);

		TextView txtDescrizione = (TextView) findViewById(R.id.rssDetailDescrizione);
		txtDescrizione.setTypeface(gothic);
		txtDescrizione.setText(Html.fromHtml(descrizione));
		
		MefDaoFactory db = new MefDaoFactory(this);
		db.openDataBase(false);
		db.updateDateItemRss(idItem, new Date());
		db.closeDataBase();
		db.close();
		
		
		
		
		
		
//		PULSANTI SHARE
		
		
		List<Intent> targetedShareIntents = new ArrayList<Intent>();
		Intent shareIntent1 = new Intent(android.content.Intent.ACTION_SEND);
		shareIntent1.setType("text/plain");
		List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(
				shareIntent1, 0);
		
		
		ImageButton imageFacebook= (ImageButton) findViewById(R.id.detailRSSImageShareFacebook);
		imageFacebook.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shareIt(titolo, descrizione, R.id.detailRSSImageShareFacebook);
			}

		});
		
		
		ImageButton imageTwitter= (ImageButton) findViewById(R.id.detailRSSImageShareTwitter);
		imageTwitter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shareIt(titolo, descrizione, R.id.detailRSSImageShareTwitter);
			}

		});
		
		ImageButton imageGooleplus= (ImageButton) findViewById(R.id.detailRSSImageShareGplus);
		imageGooleplus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shareIt(titolo, descrizione, R.id.detailRSSImageShareGplus);
			}

		});
		
		
		ImageButton imageShare = (ImageButton) findViewById(R.id.detailRSSImageShare);
		imageShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shareIt(titolo, descrizione, R.id.detailRSSImageShare);
			}

		});

		ImageButton imageInternet = (ImageButton) findViewById(R.id.detailRSSImageInternet);
		imageInternet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
				startActivity(intent);

			}
		});

		
		
		if (!resInfo.isEmpty()) {
			
			for (ResolveInfo resolveInfo : resInfo) {
				String packageName = resolveInfo.activityInfo.packageName;
				if (packageName.equals("com.facebook.katana") ) {
				
					imageFacebook.setVisibility(0);
				} else if (packageName.equals("com.twitter.android") ) {
					
					imageTwitter.setVisibility(0);
				}  else if (packageName.equals("com.google.android.apps.plus") ) {
					
					imageGooleplus.setVisibility(0);
				}
				
				
			}
		}
		
		
		

	}

	@Override
	public void onBackPressed() {

		Intent intent = new Intent(this, RSSList.class);
		intent.putExtra("idPulsante", idPulsante);
		startActivity(intent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rssdetail, menu);
		return true;
	}
	
	/**
	 * Event Handling for Individual menu item selected Identify single menu
	 * item by it's id
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_settings:
			startActivity(new Intent(this, PrefsActivity.class));// start the
																	// PrefsActivity.java
			// startActivity(new Intent(this, TestDBActivity.class));//start the
			// PrefsActivity.java
			return true;
			case R.id.actionContact:

			 Intent intent = new Intent(this, ContactActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	

	private void shareIt(String titolo, String descrizione, int share) {


		try {
			List<Intent> targetedShareIntents = new ArrayList<Intent>();
			Intent shareIntent1 = new Intent(android.content.Intent.ACTION_SEND);
			shareIntent1.setType("text/plain");
			List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(
					shareIntent1, 0);
			
			Intent chooserIntent = null;
			
			if (!resInfo.isEmpty()) {
				

				
				for (ResolveInfo resolveInfo : resInfo) {
					Intent targetedShareIntent = new Intent(android.content.Intent.ACTION_SEND);
					targetedShareIntent.setType("text/plain");
					targetedShareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, titolo);
					String packageName = resolveInfo.activityInfo.packageName;
					Log.i("package", packageName);
					
					
					
					if (packageName.equals("com.facebook.katana") && R.id.detailRSSImageShareFacebook == share) {
						targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, link);
						targetedShareIntent.setPackage(packageName);
						targetedShareIntents.add(targetedShareIntent);

					} else if (packageName.equals("com.twitter.android")  && R.id.detailRSSImageShareTwitter == share) {
						targetedShareIntent.putExtra(
								android.content.Intent.EXTRA_TEXT, link);
						targetedShareIntent.setPackage(packageName);
						targetedShareIntents.add(targetedShareIntent);
					}  else if (packageName.equals("com.google.android.apps.plus")  && R.id.detailRSSImageShareGplus == share) {
						targetedShareIntent.putExtra(
								android.content.Intent.EXTRA_TEXT, link);
						targetedShareIntent.setPackage(packageName);
						targetedShareIntents.add(targetedShareIntent);
					} else if (R.id.detailRSSImageShare == share && !(packageName.equals("com.facebook.katana") || packageName.equals("com.twitter.android") || packageName.equals("com.google.android.apps.plus"))  ) {
						targetedShareIntent.putExtra(
								android.content.Intent.EXTRA_TEXT, descrizione
										+ link);
						targetedShareIntent.setPackage(packageName);
						targetedShareIntents.add(targetedShareIntent);
					}

				}
				
				
		
				if (targetedShareIntents.size() > 0) {
					chooserIntent = Intent.createChooser(targetedShareIntents.remove(0),
							getResources().getString(R.string.condividi_con));

					chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
							targetedShareIntents.toArray(new Parcelable[] {}));
					startActivity(chooserIntent);
				}
			}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		 switch(event.getAction())
         {
         case MotionEvent.ACTION_DOWN:
             Log.d("RSSDETAIL_onTouch", "" + MotionEvent.ACTION_DOWN);
             return true;
         case MotionEvent.ACTION_CANCEL:
        	 Log.d("RSSDETAIL_onTouch", "" + MotionEvent.ACTION_CANCEL);
        	 return true;
         case MotionEvent.ACTION_OUTSIDE:
        	 Log.d("RSSDETAIL_onTouch", "" + MotionEvent.ACTION_OUTSIDE);
        	 return true;
         case MotionEvent.ACTION_UP:
        	 Log.d("RSSDETAIL_onTouch", "" + MotionEvent.ACTION_UP);
        	 return true;
         }
         return false;
     }
		
		
	

}
