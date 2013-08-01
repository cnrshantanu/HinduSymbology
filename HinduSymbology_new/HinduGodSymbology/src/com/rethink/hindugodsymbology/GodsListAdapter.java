package com.rethink.hindugodsymbology;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GodsListAdapter extends ArrayAdapter<God_Bean>{
	private List<God_Bean> m_Gods = new ArrayList<God_Bean>();
	private int			   m_resource;
	
	public GodsListAdapter(Context context, int resource, List<God_Bean> items)
	{
		super(context, resource, items);
		m_Gods 		= items;
		m_resource	= resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemView = convertView;

		if(itemView == null)
		{
			LayoutInflater vi;
	        vi = LayoutInflater.from(getContext());
			itemView = vi.inflate(m_resource, parent, false);

		}

		God_Bean currentGod = m_Gods.get(position);

		//God's image
		ImageView imageview = (ImageView) itemView.findViewById(R.id.laxmi);
		AssetManager assetManager = getContext().getAssets();

        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(currentGod.getMain_Image());
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            Log.d("DEBUG","something wrong with asset manager");
        	return null;

        }

        imageview.setImageBitmap(bitmap);

		//God's Name
		TextView textview = (TextView) itemView.findViewById(R.id.laxmiMata);
		textview.setText(currentGod.getGod_Name());


		return itemView;
		//return super.getView(position, convertView, parent);
	}

}
