package com.bag.hindugodsymbology;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class HTMLContentActivity extends Activity {

	God_Bean bean;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.html_content);
		
		Bundle b = this.getIntent().getExtras();
		if(b!=null)
		   bean = b.getParcelable("BEAN");
		
		//ImageView Main_Image = (ImageView) findViewById(R.id.imMainImage);
		Button but_marker = (Button) findViewById(R.id.bMarker);
		Button but_symbology = (Button) findViewById(R.id.bSymbology);
		Button but_summary = (Button) findViewById(R.id.bSummary);
		
		Intent intent = getIntent();
		String type = intent.getStringExtra("Type");
		
		Log.d("COW",bean.getSummary());
		WebView wv = (WebView) findViewById(R.id.wvHTMLContent);
		wv.getSettings().setJavaScriptEnabled(true);
		if(type.equals("Summary"))
			wv.loadUrl("file:///android_asset/"+bean.getSummary());		
		else
		if(type.equals("Symbology"))
				wv.loadUrl("file:///android_asset/"+bean.getSymbology());		
				
		
		
		
        
		but_marker.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Class ourClass;
				try
				{
					ourClass = Class.forName("com.bag.hindugodsymbology.NewMarkerActivity");

					Intent i = new Intent(HTMLContentActivity.this, ourClass);
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

					Intent i = new Intent(HTMLContentActivity.this, ourClass);
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

					Intent i = new Intent(HTMLContentActivity.this, ourClass);
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

		}



	}