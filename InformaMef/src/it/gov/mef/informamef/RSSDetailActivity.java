package it.gov.mef.informamef;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.gov.mef.util.DateUtil;
import it.gov.mef.util.FormatActionBar;
import it.gov.mef.util.FormatTitleBar;
import it.gov.mef.util.MefConstants;
import it.gov.mef.util.MefDaoFactory;
import it.gov.mef.util.NavigationBean;
import it.gov.mef.util.RSSHomeItem;
import it.gov.mef.util.RSSItem;
import it.gov.mef.util.UpdateRSS;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class RSSDetailActivity extends Activity  {

    //	private int idPulsante;
    private String titolo;
    private String descrizione;
    private String link;
    private String data;
    private int idItem;
    private int idUrl;
    private String guid;
    private String category;
    private int idPulsante;
    private Context ctx;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 500;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssdetail);
        ctx = this;

        MefDaoFactory db = new MefDaoFactory(this);
        db.openDataBase(false);
        List<RSSItem> listRSS = new ArrayList<RSSItem>();

//        titolo = getIntent().getStringExtra("titolo");
//        descrizione = getIntent().getStringExtra("descrizione");
//        link = getIntent().getStringExtra("link");
//        data = getIntent().getStringExtra("data");
//        idPulsante = getIntent().getIntExtra("idPulsante", 0);
//
//
//        idItem = getIntent().getIntExtra("idItemRSS", 0);
//        idUrl = getIntent().getIntExtra("idUrl", 0);
//        guid = getIntent().getStringExtra("guid");
//        category = getIntent().getStringExtra("data");


        NavigationBean nav = (NavigationBean) getApplication();
        listRSS = nav.getListRSS();
        RSSItem itemRSS = listRSS.get(nav.getCurrentItemRSS());
        nav.setItemRSS(itemRSS);

        titolo = itemRSS.getTitle(); // getIntent().getStringExtra("titolo");
        descrizione = itemRSS.getDescription(); //getIntent().getStringExtra("descrizione");
        link = itemRSS.getLink(); //getIntent().getStringExtra("link");
        data = DateUtil.formatHTTPDate(itemRSS.getDate());// getIntent().getStringExtra("data");
        idPulsante = nav.getCurrentRSS();//getIntent().getIntExtra("idPulsante", 0);


        idItem = itemRSS.getId_item(); //getIntent().getIntExtra("idItemRSS", 0);
        idUrl = itemRSS.getIdUrl(); // getIntent().getIntExtra("idUrl",0); ;
        guid = itemRSS.getGuid(); //getIntent().getStringExtra("guid");
        category = itemRSS.getCategory(); //getIntent().getStringExtra("data");


        int dipartimentoSelezionato = nav.getDipartimento();
		String bTitle = null;
		int logo = 0;
		switch (dipartimentoSelezionato) {
		case MefConstants.MEF:
			bTitle = getString(R.string.title_home_mef);
			logo =  R.drawable.ic_logo_mef_9;
			break;
		case MefConstants.DAG:
			bTitle = getString(R.string.title_home_dag);
			logo =  R.drawable.ic_logo_dag_9;
			break;
		case MefConstants.RGS:
			bTitle = getString(R.string.title_home_rgs);
			logo =  R.drawable.ic_logo_rgs_9;
			break;
		case MefConstants.DT:
			bTitle = getString(R.string.title_home_dt);
			logo =  R.drawable.ic_logo_dt_9;
			break;
		case MefConstants.INTRANET_DAG:
			bTitle = getString(R.string.title_home_intranetdag);
			logo =  R.drawable.ic_logo_intranetdag_9;
			break;
		case MefConstants.FINANZE:
			bTitle = getString(R.string.title_home_finanze);
			logo =  R.drawable.ic_logo_finanze_9;
			break;
		default:
			bTitle = "Dettaglio Elemento";
			logo =  R.drawable.ic_logo_mef_9;
			break;

		}


        FormatTitleBar.settingTitle(this, bTitle, logo, category);





        Typeface gothicB = Typeface.createFromAsset(getAssets(), "fonts/gothicb.ttf");
        Typeface gothic = Typeface.createFromAsset(getAssets(), "fonts/gothic.ttf");

        TextView txtTitolo = (TextView) findViewById(R.id.rssDetailTitolo);
        txtTitolo.setTypeface(gothicB);
        txtTitolo.setText(titolo);
        TextView txtData = (TextView) findViewById(R.id.rssDetailData);
        txtData.setTypeface(gothic);
        txtData.setText(data);

        TextView txtDescrizione = (TextView) findViewById(R.id.rssDetailDescrizione);
        txtDescrizione.setTypeface(gothic);
        txtDescrizione.setText(Html.fromHtml(descrizione));


        db.updateDateItemRss(idItem, new Date());
        db.closeDataBase();
        db.close();


//		PULSANTI SHARE


        List<Intent> targetedShareIntents = new ArrayList<Intent>();
        Intent shareIntent1 = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent1.setType("text/plain");
        List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(
                shareIntent1, 0);


        ImageButton imageFacebook = (ImageButton) findViewById(R.id.detailRSSImageShareFacebook);
        imageFacebook.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                shareIt(titolo, descrizione, R.id.detailRSSImageShareFacebook);
            }

        });


        ImageButton imageTwitter = (ImageButton) findViewById(R.id.detailRSSImageShareTwitter);
        imageTwitter.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                shareIt(titolo, descrizione, R.id.detailRSSImageShareTwitter);
            }

        });

        ImageButton imageGooleplus = (ImageButton) findViewById(R.id.detailRSSImageShareGplus);
        imageGooleplus.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                shareIt(titolo, descrizione, R.id.detailRSSImageShareGplus);
            }

        });


        ImageButton imageShare = (ImageButton) findViewById(R.id.detailRSSImageShare);
        imageShare.setVisibility(View.VISIBLE);
        imageShare.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                shareIt(titolo, descrizione, R.id.detailRSSImageShare);
            }

        });

        ImageButton imageInternet = (ImageButton) findViewById(R.id.detailRSSImageInternet);
        imageInternet.setVisibility(View.VISIBLE);
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
                if (packageName.equals("com.facebook.katana")) {

                    imageFacebook.setVisibility(View.VISIBLE);
                } else if (packageName.equals("com.twitter.android")) {

                    imageTwitter.setVisibility(View.VISIBLE);
                } else if (packageName.equals("com.google.android.apps.plus")) {

                    imageGooleplus.setVisibility(View.VISIBLE);
                }


            }
        }


        ImageButton imageBack = (ImageButton) findViewById(R.id.imageBackNav);
        imageBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ctx, RSSDetailActivity.class);

                NavigationBean nav = (NavigationBean) getApplication();
                int back = nav.getCurrentItemRSS() - 1;
                nav.setCurrentItemRSS(back);
                startActivity(intent);

            }

        });


        ImageButton imageForward = (ImageButton) findViewById(R.id.imageForward);
        imageForward.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, RSSDetailActivity.class);

                NavigationBean nav = (NavigationBean) getApplication();
                int forward = nav.getCurrentItemRSS() + 1;
                nav.setCurrentItemRSS(forward);
                startActivity(intent);
            }

        });


        if (nav.getCurrentItemRSS() > 0 && nav.getCurrentItemRSS() <= nav.getMaxItemRSS()) {
            imageBack.setVisibility(View.VISIBLE);
        } else {
            imageBack.setVisibility(View.INVISIBLE);
        }

        if (nav.getCurrentItemRSS() < nav.getMaxItemRSS() && nav.getCurrentItemRSS() >= 0) {
            imageForward.setVisibility(View.VISIBLE);
        } else {
            imageForward.setVisibility(View.INVISIBLE);
        }
        
        
        ScrollView scrview = (ScrollView) findViewById(R.id.rssScrollViewDetail);
		gestureDetector = new GestureDetector(new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (gestureDetector.onTouchEvent(event)) {
					return true;
				}
				return false;
			}
		};
		scrview.setOnTouchListener(gestureListener);

        


    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, RSSList.class);
        NavigationBean nav = (NavigationBean) getApplication();
        nav.setItemRSS(null);
        nav.setCurrentItemRSS(0);
        nav.setMaxItemRSS(0);
        nav.setListRSS(null);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    /**
     * Event Handling for Individual menu item selected Identify single menu
     * item by it's id
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_settings:
                startActivity(new Intent(this, PrefsActivity.class));// start the
                // PrefsActivity.java
                // startActivity(new Intent(this, TestDBActivity.class));//start the
                // PrefsActivity.java
                return true;
            case R.id.action_refresh_list:

//			TODO Deve aggiornare solo RSS corrente
                Toast.makeText(this,
                        "Sincronizzazione avviata", Toast.LENGTH_SHORT)
                        .show();
                NavigationBean nav = (NavigationBean) getApplication();
                List<RSSHomeItem> itemHomeList = nav.getItemHomeList();
                RSSHomeItem itemHome = itemHomeList.get(nav.getCurrentRSS());
                new UpdateRSS().execute(new Object[]{ctx, new Integer(-1), new Integer(itemHome.getIdRSS())});

//			startActivity(new Intent(this, HomeDipActivity.class));

                return true;
            case R.id.actionContact:

                Intent intent = new Intent(this, ContactActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_home:
                Intent intentHome = new Intent(this, HomeDipActivity.class);
                startActivity(intentHome);
                return true;
            case R.id.action_indietro:
                onBackPressed();
                return true;
            case R.id.action_esci:
                Intent intent1 = new Intent(Intent.ACTION_MAIN);
                intent1.addCategory(Intent.CATEGORY_HOME);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
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

                    } else if (packageName.equals("com.twitter.android") && R.id.detailRSSImageShareTwitter == share) {
                        targetedShareIntent.putExtra(
                                android.content.Intent.EXTRA_TEXT, link);
                        targetedShareIntent.setPackage(packageName);
                        targetedShareIntents.add(targetedShareIntent);
                    } else if (packageName.equals("com.google.android.apps.plus") && R.id.detailRSSImageShareGplus == share) {
                        targetedShareIntent.putExtra(
                                android.content.Intent.EXTRA_TEXT, link);
                        targetedShareIntent.setPackage(packageName);
                        targetedShareIntents.add(targetedShareIntent);
                    } else if (R.id.detailRSSImageShare == share && !(packageName.equals("com.facebook.katana") || packageName.equals("com.twitter.android") || packageName.equals("com.google.android.apps.plus"))) {
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
                            targetedShareIntents.toArray(new Parcelable[]{}));
                    startActivity(chooserIntent);
                }
            }
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }




	class MyGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			try {
				 NavigationBean nav = (NavigationBean) getApplication();
				if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
					return false;
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					Log.d("SWIPE", "destra-sinistra");
					
					 
					 
					 if (nav.getCurrentItemRSS() < nav.getMaxItemRSS()-1 && nav.getCurrentItemRSS() >= 0) {
						 Intent intent = new Intent(ctx, RSSDetailActivity.class);
			                int forward = nav.getCurrentItemRSS() + 1;
			                nav.setCurrentItemRSS(forward);
			                startActivity(intent);
				        } 
					 

				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					Log.d("SWIPE", "sinistra-destra");
					 	
					if (nav.getCurrentItemRSS() > 0 && nav.getCurrentItemRSS() <= nav.getMaxItemRSS()) {
						 Intent intent = new Intent(ctx, RSSDetailActivity.class);

			                
			                int back = nav.getCurrentItemRSS() - 1;
			                nav.setCurrentItemRSS(back);
			                startActivity(intent);
				        }
					 
					
				}
			} catch (Exception e) {
				// nothing
			}
			return false;
		}
	}




}
