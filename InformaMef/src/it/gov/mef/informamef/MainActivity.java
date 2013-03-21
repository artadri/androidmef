package it.gov.mef.informamef;

import it.gov.mef.util.MefDaoFactory;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private LayoutParams layoutParams = new LayoutParams(
			LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	private LayoutParams textParams = new LayoutParams(
			LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	private MainActivity _this;
	private LinearLayout layout;
	private Handler mHandler;

	protected static final int FINISH_LOAD = 0;
	protected static final int START_LOAD = 1;
	protected static final int ABORT_LOAD = 2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_this = this;

		
		Intent intentdb = new Intent(this, TestDBActivity.class);
		startActivity(intentdb);
		
//		Creo il db
//		MefDaoFactory mdb = new MefDaoFactory(this); //.getWritableDatabase();
//		
//		mdb.insert(table, nullColumnHack, values)
//		mdb.openDataBase(true);

		
//		Cursor cursor = mdb.fetchRSS();
////		cursor.moveToFirst(); cursor.
//		
//		while(cursor.moveToNext())
//			{
//			Log.d("url", cursor.getString(cursor.getColumnIndex("url")));
//			
//			}
//		
//		cursor.close();
//		mdb.closeDataBase();
			  
		
		
		
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case FINISH_LOAD:
					setContentView(layout);
					SplashScreen.sendMessage(SplashScreen.CLOSE_SPLASH);
					setContentView(R.layout.activity_home);

					break;
				case START_LOAD:
					initializing();
					break;
				case ABORT_LOAD:
					finish();
				}

			}
		};
		startSplash();
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);

	}

	private void startSplash() {
		Intent intent = new Intent(this, SplashScreen.class);
		SplashScreen.setMainHandler(mHandler);
		startActivity(intent);
	}

	private void initializing() {
		new Thread() {
			@Override
			public void run() {

				layout = new LinearLayout(_this);
				layout.setLayoutParams(layoutParams);
				layout.setOrientation(LinearLayout.VERTICAL);

				for (int i = 0; i < 1000; i++) {

					/* Necessario per far avanzare la progress bar */
					Message msg = new Message();
					msg.what = SplashScreen.SET_PROGRESS;
					msg.arg1 = i;
					SplashScreen.sendMessage(msg);
				}

				mHandler.sendEmptyMessage(FINISH_LOAD);

			}

		}.start();
	}

}
