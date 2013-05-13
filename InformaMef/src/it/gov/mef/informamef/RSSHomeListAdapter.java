package it.gov.mef.informamef;

import it.gov.mef.util.DateUtil;
import it.gov.mef.util.RSSHomeItem;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RSSHomeListAdapter extends ArrayAdapter<RSSHomeItem> {
	private int resource;
    private LayoutInflater inflater;
    private Context context;

    public RSSHomeListAdapter ( Context ctx, int resourceId, List<RSSHomeItem> objects) {

          super( ctx, resourceId, objects );
          resource = resourceId;
          inflater = LayoutInflater.from( ctx );
          context=ctx;
    }

    @Override
    public View getView ( int position, View convertView, ViewGroup parent ) {

          /* create a new view of my layout and inflate it in the row */
          convertView = ( RelativeLayout ) inflater.inflate( resource, null );

          /* Extract the city's object to show */
          RSSHomeItem item = getItem( position );
          

          Typeface gothicB=Typeface.createFromAsset(context.getAssets(), "fonts/gothicb.ttf"); 
          Typeface gothic=Typeface.createFromAsset(context.getAssets(), "fonts/gothic.ttf"); 
//           Typeface.createFromAsset(getAssets(), "fonts/gothicb.ttf"); 
          

          /* Take the TextView from layout and set the city's name */
          TextView txtName = (TextView) convertView.findViewById(R.id.itemName);
          txtName.setText(item.getNome());
          txtName.setTypeface(gothicB);
          
          TextView dataAgg = (TextView) convertView.findViewById(R.id.ultimAgg);
          dataAgg.setTypeface(gothic);
          dataAgg.setText("Ultimo Agg. " + ((item.getUltimoAgg()!= null)? DateUtil.formatHTTPDate(item.getUltimoAgg()):"") );
          
          /* Take the TextView from layout and set the city's wiki link */
          TextView txtTotali = (TextView) convertView.findViewById(R.id.itemTotali);
          txtTotali.setTypeface(gothicB);
          txtTotali.setText(item.getTotali());

          /* Take the ImageView from layout and set the city's image */
          ImageView imageRSS = (ImageView) convertView.findViewById(R.id.ImageHomeAdapter);
                    
//          String uri = "drawable/" + item.getImage();
//          int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

          Drawable image = context.getResources().getDrawable(Integer.parseInt(item.getImage()));
          
          imageRSS.setImageDrawable(image);
          return convertView;
    }
    

}
