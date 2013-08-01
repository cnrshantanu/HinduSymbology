package com.rethink.hindugodsymbology;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
			
		
		Class ourClass;
		 switch (v.getId())
	    {
	    	case R.id.button1:
	    		/*temp = new Intent("com.rethink.hindugodsymbology.TabGodNames");
	    		startActivity(temp);*/
	    		
	    	
				try
				{
					Intent i = new Intent(LauncherActivity.this, TabGodNames.class);
					Bundle b = new Bundle();
					
					i.putExtras(b);

					startActivity(i);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		break;
	    		
	    	case R.id.button2:
	    	
				try
				{
					Intent i = new Intent(LauncherActivity.this, InformerActivity.class);
					Bundle b = new Bundle();
					b.putString("index","0");
					i.putExtras(b);

					startActivity(i);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		
	    		
    			break;
	    	case R.id.button3:
				try
				{
					Intent i = new Intent(LauncherActivity.this, InformerActivity.class);
					Bundle b = new Bundle();
					b.putString("index","1");
					i.putExtras(b);

					startActivity(i);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	    		
	    		
    			break;
	    	case R.id.button4:
				try
				{
					Intent i = new Intent(LauncherActivity.this, InformerActivity.class);
					Bundle b = new Bundle();
					b.putString("index","2");
					i.putExtras(b);

					startActivity(i);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			break;
	    	case R.id.button5:
				try
				{
					Intent i = new Intent(LauncherActivity.this, InformerActivity.class);
					Bundle b = new Bundle();
					b.putString("index","3");
					i.putExtras(b);

					startActivity(i);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			break;
    		
	    }
		
	}

}
