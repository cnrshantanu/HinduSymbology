package com.rethink.hindugodsymbology;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

public class InformerActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_howtouse);
		
		
		
		
		Intent intent = getIntent();
		String type = intent.getStringExtra("index");
		
		
		WebView wv = (WebView) findViewById(R.id.webView1);
		//wv.getSettings().setJavaScriptEnabled(true);
		if(type.equals("0"))
			wv.loadUrl("file:///android_asset/www/HowToUse.html");		
		else
		if(type.equals("1"))
			wv.loadUrl("file:///android_asset/www/AboutTheApp.html");		
		else
			if(type.equals("2"))
				wv.loadUrl("file:///android_asset/www/Credentials.html");		
		else
			if(type.equals("3"))
				wv.loadUrl("file:///android_asset/www/ContactUs.html");			
		
		
	
		
	
	}

}
