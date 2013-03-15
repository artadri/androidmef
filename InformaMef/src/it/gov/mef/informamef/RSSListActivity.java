package it.gov.mef.informamef;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RSSListActivity extends Activity {
//	String feedUrl = "";
//	ListView rssListView = null;
//	ArrayList<RSSItem> RSSItems = new ArrayList<RSSItem>();
//	ArrayAdapter<RSSItem> array_adapter = null;
//
//	/** Called when the activity is first created. */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_rss_list);
//
//		// feedUrl = "http://intranetdag-prod.tesoro.it/test_rss";
//		feedUrl = "http://intranetdag-prod.tesoro.it/rss/rss.html?t=12002";
//
//		refreshRSSList();
//
//		rssListView = (ListView) findViewById(R.id.rssListView);
//
//		array_adapter = new ArrayAdapter<RSSItem>(this, R.layout.list_item,
//				RSSItems);
//		rssListView.setAdapter(array_adapter);
//		
//		
//		
//		OnItemClickListener itemClickListener = new OnItemClickListener () {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
//                // AdapterView is the parent class of ListView
//                ListView lv = (ListView) arg0;
//                if(lv.isItemChecked(position)){
//                    Toast.makeText(getBaseContext(), "You checked " + array_adapter.getItem(position).getTitle(), Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getBaseContext(), "You unchecked " + "errore", Toast.LENGTH_SHORT).show();
//                }
//           }
//
//
//        };
// 
//        // Setting the ItemClickEvent listener for the listview
//        rssListView.setOnItemClickListener(itemClickListener);
//    	
//		
//		
//
//		refreshRSSList();
//
//	}
//
//	private void refreshRSSList() {
//
//		ArrayList<RSSItem> newItems = RSSItem.getRSSItems(feedUrl);
//		RSSItems.clear();
//		RSSItems.addAll(newItems);
//
//	}
}
