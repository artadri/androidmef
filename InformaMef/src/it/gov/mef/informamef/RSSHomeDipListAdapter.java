package it.gov.mef.informamef;

import it.gov.mef.util.DateUtil;
import it.gov.mef.util.MefConstants;
import it.gov.mef.util.RSSHomeDipItem;
import it.gov.mef.util.RSSHomeItem;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class RSSHomeDipListAdapter extends ArrayAdapter<RSSHomeDipItem> {
	
	private int resource;
    private LayoutInflater inflater;
    private Context context;
    
	public RSSHomeDipListAdapter(Context context, int resource,
			 List<RSSHomeDipItem> objects) {
		super(context, resource, objects);
		 this.resource = resource;
         this.inflater = LayoutInflater.from( context );
         this.context=context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

        /* create a new view of my layout and inflate it in the row */
        convertView = ( RelativeLayout ) inflater.inflate( resource, null );

        /* Extract the city's object to show */
        RSSHomeDipItem item = getItem( position );
        

        Typeface gothicB=Typeface.createFromAsset(context.getAssets(), "fonts/gothicb.ttf"); 
        Typeface gothic=Typeface.createFromAsset(context.getAssets(), "fonts/gothic.ttf"); 
//         Typeface.createFromAsset(getAssets(), "fonts/gothicb.ttf"); 
        

        /* Take the TextView from layout and set the city's name */
        TextView txtDipartimento = (TextView) convertView.findViewById(R.id.rssListDipAdapterTitolo);
        txtDipartimento.setText(item.getDipartimento());
        txtDipartimento.setTypeface(gothicB);
        
        TextView txtDescrizione = (TextView) convertView.findViewById(R.id.rssListDipAdapterDesc);
        txtDescrizione.setTypeface(gothic);
        txtDescrizione.setText(item.getDescrizione());
       
        
        
        Drawable image = context.getResources().getDrawable(item.getImage());
        /* Take the ImageView from layout and set the city's image */
        ImageView imageRSS = (ImageView) convertView.findViewById(R.id.rssListDipAdapterLogo);
        
        imageRSS.setImageDrawable(image);
        

        
        return convertView;
  }

	
	
}
