package it.gov.mef.util;

import it.gov.mef.informamef.HomeActivity;
import it.gov.mef.informamef.R;
import it.gov.mef.informamef.RSSList;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class FormatActionBar {

	public static void setting(final Activity activity, final int layoutHome,
			int home, int back, int titolo, boolean isBack) {
	
		// Associo al pulsante home il ritorno alla home
		ImageButton imageHome = (ImageButton) activity.findViewById(home);
		imageHome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(v.getContext(), HomeActivity.class);
				activity.setContentView(layoutHome);
				activity.startActivity(intent);
			}

		});

		
		if ( isBack && back != R.id.imageRefresh ) {

			// Associo al pulsante back
			ImageButton imageBack = (ImageButton) activity.findViewById(back);
			imageBack.setVisibility(0);
			imageBack.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					activity.onBackPressed();
				}

			});
		} else if (back == R.id.imageRefresh){
			// Associo al pulsante back
						ImageButton imageBack = (ImageButton) activity.findViewById(R.id.imageBack);
						imageBack.setVisibility(0);
						imageBack.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								Intent intent = new Intent(v.getContext(), HomeActivity.class);
								activity.setContentView(layoutHome);
								activity.startActivity(intent);	}

						});

		}


		if (titolo > 0) {
			Typeface gothicB=Typeface.createFromAsset(activity.getAssets(), "fonts/gothicb.ttf"); 
			TextView title = (TextView) activity
					.findViewById(R.id.actionBarTitle);
			title.setTypeface(gothicB);
			title.setText(titolo);
		}
	}
	
	
	public static void setting(final Activity activity, final int layoutHome,
			int home,  int titolo) {
	
		// Associo al pulsante home il ritorno alla home
		ImageButton imageHome = (ImageButton) activity.findViewById(home);
		imageHome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(v.getContext(), HomeActivity.class);
				activity.setContentView(layoutHome);
				activity.startActivity(intent);
			}

		});

		
	

		// Associo al pulsante back
						ImageButton imageBack = (ImageButton) activity.findViewById(R.id.imageBack);
						imageBack.setVisibility(0);
						imageBack.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								Intent intent = new Intent(v.getContext(), HomeActivity.class);
								activity.setContentView(layoutHome);
								activity.startActivity(intent);	}

						});



		if (titolo > 0) {
			
			 Typeface gothicB=Typeface.createFromAsset(activity.getAssets(), "fonts/gothicb.ttf"); 
			TextView title = (TextView) activity
					.findViewById(R.id.actionBarTitle);
			title.setTypeface(gothicB);
			title.setText(titolo);
		}
	}
	

}
