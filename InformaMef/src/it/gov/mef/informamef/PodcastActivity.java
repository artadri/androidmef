package it.gov.mef.informamef;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;


public class PodcastActivity extends Activity {
	/** TO DO
	 * NON FUNZIONA
	*/
	
	
	public static String url = "http://www.mef.gov.it/system/modules/it.mef/elements/podcast/box.video.jsp?lob_id=4952";
	
    private VideoView videoView = null;
    private ProgressBar prog = null;
    private Context ctx = null;
    private MediaController mediaController = null;

   @Override
   public void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              requestWindowFeature(Window.FEATURE_NO_TITLE);
              getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
              WindowManager.LayoutParams.FLAG_FULLSCREEN);
              getWindow().setFormat(PixelFormat.TRANSLUCENT);
              setContentView(R.layout.activity_podcast);
              ctx = this;
              prog = (ProgressBar) findViewById(R.id.prog);
              videoView = (VideoView) findViewById(R.id.video);
              Uri video = Uri.parse(url);
              mediaController = new MediaController(this);
              mediaController.setAnchorView(videoView);
              videoView.setMediaController(mediaController);
              videoView.setVideoURI(video);
              

              videoView.setOnErrorListener(new OnErrorListener() {

                             @Override
                             public boolean onError(MediaPlayer mp, int what, int extra) {
                                      // TODO Auto-generated method stub
                                      Toast.makeText(ctx, "Error occured", 500).show();
                                      return false;
                             }
              });

              videoView.setOnPreparedListener(new OnPreparedListener() {

                             public void onPrepared(MediaPlayer arg0) {
                                        prog.setVisibility(View.GONE);
                                        videoView.start();
                             }
             });
              
              
             videoView.setOnCompletionListener(new OnCompletionListener () {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					Log.i(this.toString(), "video completo");
					
				}
			}) ;
   }

   @Override
   protected void onDestroy() {
         try {
                 videoView.stopPlayback();
         } catch (Exception e) {
                 //
         }
         super.onDestroy();
   }}
