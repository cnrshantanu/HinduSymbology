package com.religion.hindu.godsymbology;

import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class LauncherActivity extends Activity implements android.view.View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_launcher);
		
		Button btn1 = (Button)findViewById(R.id.button1);
		btn1.setOnClickListener(this);
		btn1 = (Button)findViewById(R.id.button2); 
		btn1.setOnClickListener(this);
		btn1 = (Button)findViewById(R.id.button3); 
		btn1.setOnClickListener(this);
		btn1 = (Button)findViewById(R.id.button4); 
		btn1.setOnClickListener(this);
		btn1 = (Button)findViewById(R.id.button5); 
		btn1.setOnClickListener(this);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
			
		Intent temp;
		 switch (v.getId())
	    {
	    	case R.id.button1:
	    		temp = new Intent("com.religion.hindu.godsymbology.GodDetails");
	    		startActivity(temp);
	    			break;
	    	case R.id.button2:
	    		temp = new Intent("com.religion.hindu.godsymbology.Howtouse");
	    		temp.putExtra("index",0);
	    		startActivity(temp);
    			break;
	    	case R.id.button3:
	    		temp = new Intent("com.religion.hindu.godsymbology.Howtouse");
	    		temp.putExtra("index",1);
	    		startActivity(temp);
    			break;
	    	case R.id.button4:
	    		temp = new Intent("com.religion.hindu.godsymbology.Howtouse");
	    		temp.putExtra("index",2);
	    		startActivity(temp);
    			break;
	    	case R.id.button5:
	    		temp = new Intent("com.religion.hindu.godsymbology.Howtouse");
	    		temp.putExtra("index",3);
	    		startActivity(temp);
    			break;
	    }
		
	}

}
