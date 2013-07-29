package com.bag.hindugodsymbology;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class FragmentMarker extends SherlockFragment  implements android.view.View.OnClickListener{
    
    private FrameLayout m_GodPicture;
    private God_Bean bean;
    private float m_width = 0,m_height = 0;
    
        
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	FrameLayout layout = (FrameLayout)getActivity().findViewById(R.id.goddetails);
    	
        return inflater.inflate(R.layout.activity_goddetails_marker, container, false);
    }
    
	
	@Override
	public void onStart() {
			super.onStart();
			Bundle b = getActivity().getIntent().getExtras();
			if(b!=null)
			   bean = b.getParcelable("BEAN");
			
		m_GodPicture = (FrameLayout) getActivity().findViewById(R.id.goddetails);
		Drawable l_drawable = null;
		try {
			  
			 Bitmap l_bitmap = BitmapFactory.decodeStream(getActivity().getAssets().open(bean.getMain_Image()));
			 //d = Drawable.createFromStream(getActivity().getAssets().open(bean.getMain_Image()), null);//Setting the image here
			 l_drawable = new BitmapDrawable(getResources(),l_bitmap);
			 m_width 	= l_bitmap.getWidth();
			 m_height	= l_bitmap.getHeight();
			 Log.d("DEBUG","goat height "+m_height+"Cow width "+ m_width);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_GodPicture.setBackgroundDrawable(l_drawable);
		onLayoutInflate();
	}
	
	private void onLayoutInflate(){
		  if(m_GodPicture.getHeight() ==0 || m_GodPicture.getWidth() ==0)
		  {
			  Log.d("DEBUG", "in if");
			  new Thread(new Runnable(){

				@Override
				public void run() {
					Log.d("DEBUG", "Cow width "+m_GodPicture.getWidth() + " height "+m_GodPicture.getHeight());
					onLayoutInflate();
				}
			}).start();
		  }
		  else{
			  Log.d("DEBUG", "in else");
			   getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       configureButtons();
                   }
               });

		  }
	}
	private void configureButtons(){
		
		Button b;
		FrameLayout.LayoutParams params;
		Iterator iter = bean.getMarkers().entrySet().iterator();//bean.getMarkers will return the hash map of markers key contains
		
		int i = 0;
		while (iter.hasNext())//iterating through the hashmap 
		{
			Map.Entry mEntry = (Map.Entry) iter.next();
			
			String[] coord = mEntry.getKey().toString().split(",");// this is to divide the x and y coordinate separated by ','
			float x = Integer.parseInt(coord[0])/m_width;//1st coordinate is the x coord
			float y = Integer.parseInt(coord[1])/m_height;//2nd coordinate is the y coord
			params = new FrameLayout.LayoutParams(

					FrameLayout.LayoutParams.WRAP_CONTENT,
					FrameLayout.LayoutParams.WRAP_CONTENT
	        );
			b = new Button(getActivity());
			params.width  = params.height = 50;
			params.leftMargin = m_GodPicture.getLeft() + (int)((float)x * m_GodPicture.getWidth()) - params.width/2;//i've put x in place of imageleft
			params.topMargin  = m_GodPicture.getTop()  + (int)((float)y * m_GodPicture.getHeight()) - params.height/2;//i've put y in place of image top
			b.setLayoutParams(params);
			b.setContentDescription(mEntry.getValue().toString());// putting the significance which can be toasted
			b.setId(i);
			b.setBackgroundDrawable(getResources().getDrawable(R.drawable.marker));
			b.setOnClickListener(this);
			m_GodPicture.addView(b, params);
			i++;
			Log.d("Marker Acti","width image" + m_width + "height of image" + m_height+ "x" + x + "y" + y + "left margin  "+params.leftMargin + "width" + m_GodPicture.getWidth() + "top margin "+params.topMargin + "height "+ m_GodPicture.getHeight());
		}

	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String message = v.getContentDescription().toString();
		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        //alert.setTitle("Registration"); //Set Alert dialog title here
        alert.setMessage(message); //Message here

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int whichButton) {
        		
        	}
        });
        
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
		//String message = "You selected :" + v.getId();
		//Toast toast = Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT);//toasting the significance
		//toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
		//toast.show(); 
		
	}

}
