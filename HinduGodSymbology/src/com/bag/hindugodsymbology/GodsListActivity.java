package com.bag.hindugodsymbology;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;



public class GodsListActivity extends Activity implements OnItemClickListener {

		ListView listView;
	List<God_Bean> rowItems;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new DBOperation().execute("");

	}

	private class DBOperation extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) 
		{
			DataBaseHelper myDbHelper = new DataBaseHelper(GodsListActivity.this);
			myDbHelper = new DataBaseHelper(GodsListActivity.this);

			try {

				myDbHelper.createDataBase();

			} catch (IOException ioe) {

				throw new Error("Unable to create database");

			}

			try {

				myDbHelper.openDataBase();

			}catch(SQLException sqle){

				throw sqle;

			}


			List<God_Bean> bean =  myDbHelper.getAllGodNames();
			myDbHelper.close();

			rowItems = new ArrayList<God_Bean>();
			for (int i = 0; i < bean.size(); i++) {
				//God_Bean item = new God_Bean(bean.get(i).getMain_Image(), bean.get(i).getGod_Name(), );
				Log.d("filter", bean.get(i).getGod_Name()+","+bean.get(i).getMain_Image());
				rowItems.add(bean.get(i));
			}


			return "Executed";
		}      

		@Override
		protected void onPostExecute(String result) {
			listView = (ListView) findViewById(R.id.list);
			CustomListViewAdapter adapter = new CustomListViewAdapter(GodsListActivity.this,R.layout.list_item, rowItems);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(GodsListActivity.this);
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	} 



	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast toast = Toast.makeText(getApplicationContext(), "Item "
				+ (position + 1) + ": " + rowItems.get(position).getGod_Name(),
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show();
		Class ourClass;
		try
		{
		ourClass = Class.forName("com.bag.hindugodsymbology.GodDetails");

		Intent i = new Intent(GodsListActivity.this, ourClass);
		Bundle b = new Bundle();
		b.putParcelable("BEAN", rowItems.get(position));
		i.putExtras(b);
		//i.setClass(this, GodDetails.class);
		startActivity(i);
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

}
