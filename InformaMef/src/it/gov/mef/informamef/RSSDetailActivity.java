package it.gov.mef.informamef;

import it.gov.mef.util.FormatActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RSSDetailActivity extends Activity {

	private int idPulsante;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rssdetail);

		String titolo = getIntent().getStringExtra("titolo");
		String descrizione = getIntent().getStringExtra("descrizione");
		String link = getIntent().getStringExtra("link");
		String data = getIntent().getStringExtra("data");
		idPulsante = getIntent().getIntExtra("idPulsante", 0);

		FormatActionBar.setting(this, R.layout.activity_home, R.id.imageHome, R.id.imageBack, R.string.detailRSSTitle, true);
		
		
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

}
