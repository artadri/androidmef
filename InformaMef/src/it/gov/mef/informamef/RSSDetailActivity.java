package it.gov.mef.informamef;

import java.util.ArrayList;
import java.util.List;

import it.gov.mef.util.FormatActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RSSDetailActivity extends Activity {

	private int idPulsante;
	private String titolo;
	private String descrizione;
	private String link;
	private String data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rssdetail);

		titolo = getIntent().getStringExtra("titolo");
		descrizione = getIntent().getStringExtra("descrizione");
		link = getIntent().getStringExtra("link");
		data = getIntent().getStringExtra("data");
		idPulsante = getIntent().getIntExtra("idPulsante", 0);

		FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome,
				R.id.imageBack, R.string.detailRSSTitle, true);

		ImageButton imageShare = (ImageButton) findViewById(R.id.detailRSSImageShare);
		imageShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shareIt(titolo, descrizione);
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

		TextView txtTitolo = (TextView) findViewById(R.id.rssDetailTitolo);
		txtTitolo.setText(titolo);
		TextView txtData = (TextView) findViewById(R.id.rssDetailData);
		txtData.setText(data);

		TextView txtDescrizione = (TextView) findViewById(R.id.rssDetailDescrizione);
		txtDescrizione.setText(Html.fromHtml(descrizione));

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

	private void shareIt(String titolo, String descrizione) {

		/**
		 * TODO per far apparire solo alcune delle icone nella scelta è
		 * necessario filtrare per package ed eventualmente adattare i parametri
		 * da passare
		 */
		List<Intent> targetedShareIntents = new ArrayList<Intent>();
		Intent shareIntent1 = new Intent(android.content.Intent.ACTION_SEND);
		shareIntent1.setType("text/plain");
		List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(
				shareIntent1, 0);
		if (!resInfo.isEmpty()) {
			for (ResolveInfo resolveInfo : resInfo) {
				String packageName = resolveInfo.activityInfo.packageName;
				Log.i("package", packageName);
				Intent targetedShareIntent = new Intent(
						android.content.Intent.ACTION_SEND);
				targetedShareIntent.setType("text/plain");
				targetedShareIntent.putExtra(
						android.content.Intent.EXTRA_SUBJECT, titolo);
				if (packageName.equals("com.facebook.katana")) {
					targetedShareIntent.putExtra(
							android.content.Intent.EXTRA_TEXT, link);
					targetedShareIntent.setPackage(packageName);
					targetedShareIntents.add(targetedShareIntent);

				} else if (packageName.equals("com.twitter.android")) {
					targetedShareIntent.putExtra(
							android.content.Intent.EXTRA_TEXT, link);
					targetedShareIntent.setPackage(packageName);
					targetedShareIntents.add(targetedShareIntent);
				}  else if (packageName.equals("com.google.android.apps.plus")) {
					targetedShareIntent.putExtra(
							android.content.Intent.EXTRA_TEXT, link);
					targetedShareIntent.setPackage(packageName);
					targetedShareIntents.add(targetedShareIntent);
				} else {
					targetedShareIntent.putExtra(
							android.content.Intent.EXTRA_TEXT, descrizione
									+ link);
				}

			}

			Intent chooserIntent = Intent.createChooser(
					targetedShareIntents.remove(0),
					getResources().getString(R.string.condividi_con));
			chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
					targetedShareIntents.toArray(new Parcelable[] {}));

			startActivity(chooserIntent);
		}

	}

}
