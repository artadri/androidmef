package it.gov.mef.informamef;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
    	Log.d("MEFBootCompletedReceiver", "onReceive");
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
