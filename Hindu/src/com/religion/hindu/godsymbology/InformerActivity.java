package com.religion.hindu.godsymbology;

import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;

public class InformerActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_howtouse);
		String[] Message =  new String[7];
		String htmlText = "<html><body style=\"text-align:justify\"> %s </body></Html>";
		Message[0] = "This is for how how to use";
		Message[1] = "This is for about the app";
		Message[2] = "This is for Credentials";
		Message[3] = "This is for Contact";
		Message[4] = "This is for summary";
		Message[5] = "This is for symbology";
		Message[6] = "Something is wrong";
		
		WebView webView = (WebView) findViewById(R.id.webView1);
		webView.loadData(String.format(htmlText, Message[getIntent().getIntExtra("index",6)]), "text/html", "utf-8");
	}

}
