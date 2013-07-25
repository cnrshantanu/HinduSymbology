package com.bag.hindugodsymbology;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class NewMarkerActivity extends Activity implements android.view.View.OnClickListener{
	
	private FrameLayout m_GodPicture;
	private ArrayList m_buttonList;
	God_Bean bean;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_goddetails_marker);
        Bundle b = this.getIntent().getExtras();
		if(b!=null)
		   bean = b.getParcelable("BEAN");
		
		//ImageView Main_Image = (ImageView) findViewById(R.id.imMainImage);//I've got it covered through bean which links to image in asset
		Button but_marker = (Button) findViewById(R.id.bMarker);
		Button but_symbology = (Button) findViewById(R.id.bSymbology);
		Button but_summary = (Button) findViewById(R.id.bSummary);
		
        
        but_marker.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Class ourClass;
				try
				{
					ourClass = Class.forName("com.bag.hindugodsymbology.NewMarkerActivity");

					Intent i = new Intent(NewMarkerActivity.this, ourClass);
					Bundle b = new Bundle();
					b.putParcelable("BEAN", bean);
					i.putExtras(b);
					
					startActivity(i);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
        but_summary.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Class ourClass;
				try
				{
					ourClass = Class.forName("com.bag.hindugodsymbology.HTMLContentActivity");

					Intent i = new Intent(NewMarkerActivity.this, ourClass);
					Bundle b = new Bundle();
					i.putExtra("Type", "Summary");
					b.putParcelable("BEAN", bean);
					i.putExtras(b);
					
					startActivity(i);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		but_symbology.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Class ourClass;
				try
				{
					ourClass = Class.forName("com.bag.hindugodsymbology.HTMLContentActivity");

					Intent i = new Intent(NewMarkerActivity.this, ourClass);
					Bundle b = new Bundle();
					b.putParcelable("BEAN", bean);
					i.putExtra("Type", "Symbology");
					i.putExtras(b);
					
					startActivity(i);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

        
        
        m_GodPicture = (FrameLayout) findViewById(R.id.goddetails);
        Drawable d = null;
        try {
			 d = Drawable.createFromStream(getAssets().open(bean.getMain_Image()), null);//Setting the image here
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        m_GodPicture.setBackgroundDrawable(d);
        m_buttonList = new ArrayList<Button>();
        configureButtons();
     }
	@Override
	 public void onWindowFocusChanged(boolean hasFocus) {
	  // TODO Auto-generated method stub
	  super.onWindowFocusChanged(hasFocus);
	  //currently I have not found the api where we get the callback after the view has been inflated
	  //so incase u dont see the buttons press home and come back again
	  configureButtons();
	
	 }
	
	//so from your beans object I need the no of markers
	//and the position x,y coordinates of all the markers
	
	private void configureButtons(){
		
		Button b;
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(

				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT
        );
		
		/*params.width  = params.height = 10;
		params.leftMargin = m_GodPicture.getLeft() + (int)(0.5f * m_GodPicture.getWidth()) - params.width/2;
		params.topMargin  = m_GodPicture.getTop()  + (int)(0.5f * m_GodPicture.getHeight()) - params.height/2;*/
		
		Iterator iter = bean.getMarkers().entrySet().iterator();//bean.getMarkers will return the hash map of markers key contains
																//coordinate in the form of 234,121 and value is the significance
		 
		int i = 0;
		while (iter.hasNext())//iterating through the hashmap 
		{
			Map.Entry mEntry = (Map.Entry) iter.next();
			
			String[] coord = mEntry.getKey().toString().split(",");// this is to divide the x and y coordinate separated by ','
			int x = Integer.parseInt(coord[0]);//1st coordinate is the x coord
			int y = Integer.parseInt(coord[1]);//2nd coordinate is the y coord
			b = new Button(this);
			params.width  = params.height = 10;
			params.leftMargin = x + (int)(0.5f * m_GodPicture.getWidth()) - params.width/2;//i've put x in place of imageleft
			params.topMargin  = y  + (int)(0.5f * m_GodPicture.getHeight()) - params.height/2;//i've put y in place of image top
			b.setLayoutParams(params);
			b.setContentDescription(mEntry.getValue().toString());// putting the significance which can be toasted
			b.setId(i);
			b.setWidth(20);
			b.setHeight(20);
			b.setBackgroundDrawable(getResources().getDrawable(R.drawable.marker));
			b.setOnClickListener(this);
			m_GodPicture.addView(b, params);
			i++;
			
			
		}

		
		
			  
		Log.d("Marker Acti","left margin  "+params.leftMargin + "width" + m_GodPicture.getWidth() + "top margin "+params.topMargin + "height "+ m_GodPicture.getHeight());
		//don't need this anymore
		/*for(int i = 0;i<bean.getMarkers().size();i++){
			b = new Button(this);
			params.width  = params.height = 10;
			params.leftMargin = m_GodPicture.getLeft() + (int)(0.5f * m_GodPicture.getWidth()) - params.width/2;
			params.topMargin  = m_GodPicture.getTop()  + (int)(0.5f * m_GodPicture.getHeight()) - params.height/2;
			b.setLayoutParams(params);
			b.setId(i);
			b.setWidth(20);
			b.setHeight(20);
			b.setBackgroundDrawable(getResources().getDrawable(R.drawable.marker));
			b.setOnClickListener(this);
			m_GodPicture.addView(b);
			
		}*/
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast toast = Toast.makeText(getApplicationContext(),  v.getContentDescription(),Toast.LENGTH_SHORT);//toasting the significance
		toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show(); 
		
	}

}
