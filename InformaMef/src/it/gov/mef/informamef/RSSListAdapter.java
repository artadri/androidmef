package it.gov.mef.informamef;

import it.gov.mef.util.DateUtil;
import it.gov.mef.util.RSSItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RSSListAdapter extends ArrayAdapter<RSSItem> {

	private int resource;
	private LayoutInflater inflater;
	String titolo;
	String descrizione;
	String link;
	Date data;
	Context ctx = null;

	public RSSListAdapter(Context ctx, int resourceId, List<RSSItem> objects) {
		super(ctx, resourceId, objects);
		resource = resourceId;
		inflater = LayoutInflater.from(ctx);
		this.ctx = ctx;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		/* create a new view of my layout and inflate it in the row */
		convertView = (LinearLayout) inflater.inflate(resource, null);

		RSSItem item = (RSSItem) getItem(position);
		RSSItem itemPrec = null;
		Typeface gothicB=Typeface.createFromAsset(ctx.getAssets(), "fonts/gothicb.ttf"); 
  

		/* Take the TextView from layout and set title */
		TextView txtDate = (TextView) convertView
				.findViewById(R.id.rssListAdapterData);
		txtDate.setTypeface(gothicB);
		
		TextView txtTitle = (TextView) convertView.findViewById(R.id.rssListAdapterTitolo);
		//txtDate.setBackgroundColor(Color.parseColor("#FFE2E5"));
		txtTitle.setTypeface(gothicB);
		
		if (position == 0) {
			txtDate.setText(DateUtil.formatHTTPDate(item.getPubDate()));
		    txtDate.setBackgroundColor(Color.parseColor("#30E4E4E4"));
		    txtDate.setTextColor(Color.parseColor("#5090C0"));
			txtTitle.setText(item.getTitle());
		} else {
			itemPrec = (RSSItem) getItem(position - 1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			Log.d("RSSLIADAPTER", sdf.format(item.getPubDate()) + " " +sdf.format(itemPrec.getPubDate()));
			
			if (!sdf.format(item.getPubDate()).equals(
					sdf.format(itemPrec.getPubDate()))) {
				
//				Log.d("RSSLIADAPTER", sdf.format(item.getPubDate()) + " " +sdf.format(itemPrec.getPubDate()));
				
				
				    txtDate.setText(DateUtil.formatHTTPDate(item.getPubDate()));
				    txtDate.setBackgroundColor(Color.parseColor("#30E4E4E4"));
				    txtDate.setTextColor(Color.parseColor("#5090C0"));
				    
			} else {
				txtDate.setVisibility(View.GONE);
			}

//			if (position % 2 == 0) {
//				txtTitle.setBackgroundColor(Color.parseColor("#E4E4E4"));
//				txtTitle.setTextColor(Color.parseColor("#FFFF44"));
//			} else {
//				txtTitle.setBackgroundColor(Color.parseColor("#FFFFFF"));
//			}

			txtTitle.setText(  item.getTitle());

		}

		// if (position % 2 == 0){
		// convertView.setBackgroundColor(Color.parseColor("#E4E4E4"));
		// txtTitle.setTextColor(Color.parseColor("#FFFFFF"));
		// } else{
		// convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
		// }
		//

		/* Take the TextView from layout and set the description */
		// TextView txtDescription = (TextView)
		// convertView.findViewById(R.id.rssDescrizione);
		// txtDescription.setText(Html.fromHtml(item.getDescription()));

		return convertView;
	}

}
