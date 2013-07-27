package com.bag.hindugodsymbology;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class TabGodNames extends SherlockFragmentActivity {
	  // store the active tab here
	  public static String ACTIVE_TAB = "activeTab";
	  private List<God_Bean> myGods = new ArrayList<God_Bean>();	
	  ListView listView;
	  ArrayList<God_Bean> rowItems;
	  ArrayList<God_Bean> BeanItems;
	  Button bClassify;

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.main);

	        ActionBar actionbar = getSupportActionBar();
	        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	        Tab tab1 = actionbar.newTab().setText("Alphabetical");
	        Tab tab2 = actionbar.newTab().setText("Classification");
	        
	        tab1.setTabListener(new MyTabListener<FragmentAlphabeticalList>(this, "tab1",
	                FragmentAlphabeticalList.class));

	        tab2.setTabListener(new MyTabListener<FragmentClassifiedList>(this, "tab2",
	                FragmentClassifiedList.class));
	        
	        
	        
	        
	    if( savedInstanceState != null ){
	      getSupportActionBar().setSelectedNavigationItem(
	                  savedInstanceState.getInt(ACTIVE_TAB));
	    }
	    //Bundle b = null;
//		try {
//			b = this.getIntent().getExtras();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (b != null)
//			BeanItems = b.getParcelableArrayList("BEANLIST");
		//if (BeanItems == null)
			new DBOperation().execute("");
//		else 
//		{
//			rowItems = BeanItems;
//			//populateGodsList();
//			//populateListView();
//			//registerClickCallback();
//			
//		}

		actionbar.addTab(tab1);
        actionbar.addTab(tab2);
	  }

	  @Override
	  protected void onSaveInstanceState(Bundle outState) {
	    // save active tab
	    outState.putInt(ACTIVE_TAB,
	            getSupportActionBar().getSelectedNavigationIndex());
	    super.onSaveInstanceState(outState);
	  }
	  
	  private class DBOperation extends AsyncTask<String, Void, String> {

			@Override
			protected String doInBackground(String... params) {
				DataBaseHelper myDbHelper = new DataBaseHelper(
						TabGodNames.this);
				myDbHelper = new DataBaseHelper(TabGodNames.this);

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
				//populateListView();
				//registerClickCallback();
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
				
				Intent temp = getIntent();
				Bundle tempBundle = new Bundle();
				rowItems.removeAll(Collections.singleton(null));
				tempBundle.putParcelableArrayList("BEANLIST", rowItems);
				temp.putExtras(tempBundle);
		}

		private void populateListView() {
			// TODO Auto-generated method stub
			ArrayAdapter<God_Bean> adapter = new MyListAdapter();
			
				ListView list = (ListView) findViewById(R.id.godsList);
				list.setAdapter(adapter);
			
		}

		private class MyListAdapter extends ArrayAdapter<God_Bean>{
			private List<God_Bean> m_Gods = new ArrayList<God_Bean>();
			public MyListAdapter()
			{
				//m_Gods = myGods;
				super(TabGodNames.this, R.layout.gods_view, myGods);
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View itemView = convertView;

				if(itemView == null)
				{
					itemView = getLayoutInflater().inflate(R.layout.gods_view, parent, false);

				}
				God_Bean currentGod = m_Gods.get(position);

				//God's image
				ImageView imageview = (ImageView) itemView.findViewById(R.id.laxmi);
				AssetManager assetManager = getAssets();

		        InputStream istr;
		        Bitmap bitmap = null;
		        try {
		            istr = assetManager.open(currentGod.getMain_Image());
		            bitmap = BitmapFactory.decodeStream(istr);
		        } catch (IOException e) {
		            return null;
		        }

		        imageview.setImageBitmap(bitmap);;

				//God's Name
				TextView textview = (TextView) itemView.findViewById(R.id.laxmiMata);
				textview.setText(currentGod.getGod_Name());


				return itemView;
				//return super.getView(position, convertView, parent);
			}

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
					Toast.makeText(TabGodNames.this, message, Toast.LENGTH_LONG).show();
					Class ourClass;
					try {
						ourClass = Class.forName("com.bag.hindugodsymbology.GodDetails");

						//Intent i = new Intent(GodsAlphabeticalActivity.this, ourClass);
						Intent i = new Intent(TabGodNames.this,TabGodDetails.class);
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
	}