package it.gov.mef.informamef;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class NotificationActivity extends Activity {

	private static final int SIMPLE_NOTIFICATION_ID = 1;

	NotificationManager mNotificationManager;
	BackgroundThread backgroundThread;
	Handler backgroundHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);

		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		View btnSendSimpleNotification = findViewById(R.id.btnSendSimpleNotification);
		btnSendSimpleNotification.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sendSimpleNotification();
			}
		});

		View btnCancelNotify = findViewById(R.id.btnCancelNotification);
		btnCancelNotify.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				cancelSimpleNotification();
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		backgroundThread = new BackgroundThread();
		backgroundThread.setRunning(true);
		backgroundThread.start();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		boolean retry = true;
		backgroundThread.setRunning(false);

		while (retry) {
			try {
				backgroundThread.join();
				retry = false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void cancelSimpleNotification() {
		mNotificationManager.cancel(SIMPLE_NOTIFICATION_ID);
	}

	private void sendSimpleNotification() {

		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
				NotificationActivity.this);

		// Titolo e testo della notifica
		notificationBuilder.setContentTitle("Titolo della mia notifica");
		notificationBuilder.setContentText("Testo della mia notifica");

		// Testo che compare nella barra di stato non appena compare la notifica
		notificationBuilder.setTicker("Questo è il tickerText");

		// Data e ora della notifica
		notificationBuilder.setWhen(System.currentTimeMillis());

		// Icona della notifica
		notificationBuilder.setSmallIcon(R.drawable.ic_stat_name);

		// Creiamo il pending intent che verrà lanciato quando la notifica
		// viene premuta
		Intent notificationIntent = new Intent(this, NotificationActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);

		notificationBuilder.setContentIntent(contentIntent);

		// Impostiamo il suono, le luci e la vibrazione di default
		notificationBuilder.setDefaults(Notification.DEFAULT_SOUND
				| Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE
				| Notification.FLAG_AUTO_CANCEL);

		mNotificationManager.notify(SIMPLE_NOTIFICATION_ID,
				notificationBuilder.build());

	}

	public class BackgroundThread extends Thread {

		boolean running = false;
		final static String ACTION = "NotifyServiceAction";
		NotificationManager notificationManager;
		Notification myNotification;

		void setRunning(boolean b) {
			running = b;
		}

		@Override
		public synchronized void start() {
			// TODO Auto-generated method stub
			super.start();
			notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (running) {
				try {
					sleep(10000); // send notification in every 10sec.

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				sendSimpleNotification();
				
			}
		}
	}

}
