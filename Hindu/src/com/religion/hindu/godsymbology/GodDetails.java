package com.religion.hindu.godsymbology;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;


public class GodDetails extends TabActivity{
		
		public static Context appContext;

		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_goddetails_main);
	        appContext = getApplicationContext();
	        
	        TabHost tabHost = getTabHost();
	        TabHost.TabSpec spec;
	        Intent intent;
	        
	        intent = new Intent().setClass(this, MarkerActivity.class);
	        spec = tabHost.newTabSpec("Marker").setIndicator("Show Marker")
                    .setContent(intent);
	        tabHost.addTab(spec);
	        		
	        intent = new Intent().setClass(this, InformerActivity.class);
	     	intent.putExtra("index",4);
	     	spec = tabHost.newTabSpec("Summary").setIndicator("Summary")
	                         .setContent(intent);
	     	tabHost.addTab(spec);
	     	        		
	     	intent = new Intent().setClass(this, InformerActivity.class);
  	     	intent.putExtra("index",5);
  	     	spec = tabHost.newTabSpec("Symbology").setIndicator("Symbology")
   	  	                         .setContent(intent);
   	  	    tabHost.addTab(spec);
   	  	    tabHost.setCurrentTab(2);
	        
	    }

}
