package it.gov.mef.informamef;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdateService extends Service {
	public UpdateService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		Log.d("MEFUpdateService", "onCreate");
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	 @Override
     public void onCreate() {
           super.onCreate();
               // codice da eseguire quando il service viene creato
           Log.d("MEFUpdateService", "onCreate");
     }

   
       @Override
     public void onDestroy() {
           super.onDestroy();
               // codice da eseguire quando il service viene distrutto
           Log.d("MEFUpdateService", "onCreate");
     }
       
       
       
       /* (non-Javadoc)
        * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
        */
       @Override
       public int onStartCommand(Intent intent, int flags, int startId) {
               // TODO Auto-generated method stub
    	   Log.d("MEFUpdateService", "onStartCommand");
               return super.onStartCommand(intent, flags, startId);
               
       }
       
       
}
