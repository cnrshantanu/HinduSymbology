package com.religion.hindu.godsymbology;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MarkerActivity extends Activity implements android.view.View.OnClickListener{
	
	private FrameLayout m_GodPicture;
	private ArrayList m_buttonList;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_goddetails_marker);
        m_GodPicture = (FrameLayout) findViewById(R.id.goddetails);
        m_GodPicture.setBackgroundDrawable(getResources().getDrawable(R.drawable.launcher_bg));
        m_buttonList = new ArrayList<Button>();
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
		
		params.width  = params.height = 50;
		params.leftMargin = m_GodPicture.getLeft() + (int)(0.5f * m_GodPicture.getWidth()) - params.width/2;
		params.topMargin  = m_GodPicture.getTop()  + (int)(0.5f * m_GodPicture.getHeight()) - params.height/2;
		
		
			  
		Log.d("Marker Acti","left margin  "+params.leftMargin + "width" + m_GodPicture.getWidth() + "top margin "+params.topMargin + "height "+ m_GodPicture.getHeight());
		for(int i = 0;i<1;i++){
			b = new Button(this);
			b.setLayoutParams(params);
			b.setId(i);
			b.setWidth(20);
			b.setHeight(20);
			b.setBackgroundDrawable(getResources().getDrawable(R.drawable.marker));
			b.setOnClickListener(this);
			m_GodPicture.addView(b);
			
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast toast = Toast.makeText(getApplicationContext(), "Item" + v.getId(),Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show(); 
		
	}

}
