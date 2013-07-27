package com.bag.hindugodsymbology;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GodsAlphabeticalActivity extends Activity {

	private List<God_Bean> myGods = new ArrayList<God_Bean>();	

	ListView listView;
	ArrayList<God_Bean> rowItems;
	ArrayList<God_Bean> BeanItems;
	Button bClassify;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alphabetical_layout);


		bClassify = (Button) findViewById(R.id.bClassification);
		

		Bundle b = null;
		try {
			b = this.getIntent().getExtras();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (b != null)
			BeanItems = b.getParcelableArrayList("BEANLIST");
		if (BeanItems == null)
			new DBOperation().execute("");
		else 
		{
			rowItems = BeanItems;
			
			bClassify.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Class ourClass;
					try {
						ourClass = Class
								.forName("com.bag.hindugodsymbology.GodsClassification");

						Intent i = new Intent(GodsAlphabeticalActivity.this,TabGodDetails.class);
						Bundle b = new Bundle();
						b.putParcelableArrayList("BEANLIST", BeanItems);
						i.putExtras(b);

						startActivity(i);

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			/////////////////////////////////////////////////////////////////////////
			

			//imageName = getResources().getResourceName(R.drawable.babykrishna);
			populateGodsList();
			populateListView();
			registerClickCallback();
			
		}
	}


private class DBOperation extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {
		DataBaseHelper myDbHelper = new DataBaseHelper(
				GodsAlphabeticalActivity.this);
		myDbHelper = new DataBaseHelper(GodsAlphabeticalActivity.this);

		try {

			myDbHelper.createDataBase();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}

		try {

			myDbHelper.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}

		List<God_Bean> bean = myDbHelper.getAllGodNames();
		myDbHelper.close();

		rowItems = new ArrayList<God_Bean>();
		for (int i = 0; i < bean.size(); i++) {
			// God_Bean item = new God_Bean(bean.get(i).getMain_Image(),
			// bean.get(i).getGod_Name(), );
			Log.d("filter", bean.get(i).getGod_Name() + ","
					+ bean.get(i).getMain_Image()+","+bean.get(i).getMarkers().size());
			rowItems.add(bean.get(i));
		}

		return "Executed";
	}

	@Override
	protected void onPostExecute(String result) {
		populateGodsList();
		populateListView();
		registerClickCallback();
		bClassify.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Class ourClass;
				try {
					ourClass = Class
							.forName("com.bag.hindugodsymbology.GodsClassification");

					//Intent i = new Intent(GodsAlphabeticalActivity.this, ourClass);
					Intent i = new Intent(GodsAlphabeticalActivity.this,TabGodDetails.class);
					Bundle b = new Bundle();
					rowItems.removeAll(Collections.singleton(null));
					b.putParcelableArrayList("BEANLIST", rowItems);
					i.putExtras(b);

					startActivity(i);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	protected void onPreExecute() {
	}

	@Override
	protected void onProgressUpdate(Void... values) {
	}
}







private void populateGodsList() {
	// TODO Auto-generated method stub
	
		for(int i = 0;i <rowItems.size(); i++){
			//String god = godsName[i]
			myGods.add(rowItems.get(i));
		}

	
}


private void populateListView() {
	// TODO Auto-generated method stub
	
	ArrayAdapter<God_Bean> adapter = new GodsListAdapter(getApplicationContext(),R.layout.gods_view,myGods);
	ListView list = (ListView) findViewById(R.id.godsList);
	list.setAdapter(adapter);
	
}

private void registerClickCallback() {
	// TODO Auto-generated method stub
	ListView list = (ListView) findViewById(R.id.godsList);
	list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
				long id) {

			God_Bean godClicked = myGods.get(position);
			String message = "You selected " + godClicked.getGod_Name();
			Toast.makeText(GodsAlphabeticalActivity.this, message, Toast.LENGTH_LONG).show();
			Class ourClass;
			try {
				ourClass = Class.forName("com.bag.hindugodsymbology.GodDetails");

				//Intent i = new Intent(GodsAlphabeticalActivity.this, ourClass);
				Intent i = new Intent(GodsAlphabeticalActivity.this,TabGodDetails.class);
				Bundle b = new Bundle();
				b.putParcelable("BEAN", rowItems.get(position));
				i.putExtras(b);
				// i.setClass(this, GodDetails.class);
				startActivity(i);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	});
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	//getMenuInflater().inflate(R.menu.main, menu);
	return true;
}

}
