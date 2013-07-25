package com.bag.hindugodsymbology;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GodDetails extends Activity {

	God_Bean bean;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.god_details);

		
		
		
		Bundle b = this.getIntent().getExtras();
		if(b!=null)
			bean = b.getParcelable("BEAN");

		
		ImageView Main_Image = (ImageView) findViewById(R.id.imMainImage);
		Button but_marker = (Button) findViewById(R.id.bMarker);
		Button but_symbology = (Button) findViewById(R.id.bSymbology);
		Button but_summary = (Button) findViewById(R.id.bSummary);


		
		AssetManager assetManager = getAssets();

		InputStream istr;
		Bitmap bitmap = null;
		try {
			istr = assetManager.open(bean.getMain_Image());
			bitmap = BitmapFactory.decodeStream(istr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main_Image.setImageBitmap(bitmap);

		but_marker.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Class ourClass;
				try
				{
					ourClass = Class.forName("com.bag.hindugodsymbology.NewMarkerActivity");

					Intent i = new Intent(GodDetails.this, ourClass);
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

					Intent i = new Intent(GodDetails.this, ourClass);
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

					Intent i = new Intent(GodDetails.this, ourClass);
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
