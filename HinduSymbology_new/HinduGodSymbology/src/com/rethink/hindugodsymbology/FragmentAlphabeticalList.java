package com.rethink.hindugodsymbology;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Intent;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
//import com.rethink.hindugodsymbology.R;



public class FragmentAlphabeticalList extends SherlockListFragment {
	

	 private List<God_Bean> myGods = new ArrayList<God_Bean>();	
	 private ArrayList<God_Bean> rowItems;
	 ArrayList<God_Bean> BeanItems;
	 private Boolean isInit = false;
	 	  
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		ArrayAdapter<God_Bean> adapter = new GodsListAdapter(getActivity().getApplicationContext(),R.layout.gods_view,myGods);
		adapter.clear();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(!isInit)
		doDBoperation();
		isInit = true;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ArrayAdapter<God_Bean> adapter = new GodsListAdapter(getActivity().getApplicationContext(),R.layout.gods_view,myGods);
		adapter.clear();
		isInit = false;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		if(!isInit)
			doDBoperation();
		isInit = true;
		return super.onCreateView(inflater, container, savedInstanceState);
        
    }
	private void doDBoperation(){
		
		Bundle b = null;
		try {
			b = getActivity().getIntent().getExtras();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (b != null)
		{
			BeanItems = b.getParcelableArrayList("BEANLIST");
			Log.d("FRag","cow bundle is not null");
		}
		if (BeanItems == null){
			
		
			Log.d("FRag","cow bean is  null");
			new DBOperation().execute("");
		}
		else 
		{
			rowItems = BeanItems;
			populateGodsList();
			populateListView();
		}

		
	}
	private class DBOperation extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			DataBaseHelper myDbHelper = new DataBaseHelper(
					getActivity());
			myDbHelper = new DataBaseHelper(getActivity());

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
				Log.d("filter","cow"+ bean.get(i).getGod_Name() + ","
						+ bean.get(i).getMain_Image()+","+bean.get(i).getMarkers().size());
				rowItems.add(bean.get(i));
			}

			return "Executed";
		}

		@Override
		protected void onPostExecute(String result) {
			populateGodsList();
			populateListView();
			Intent temp = getActivity().getIntent();
			Bundle tempBundle = new Bundle();
			rowItems.removeAll(Collections.singleton(null));
			tempBundle.putParcelableArrayList("BEANLIST", rowItems);
			temp.putExtras(tempBundle);
			getActivity().setIntent(temp);
			
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
		ArrayAdapter<God_Bean> adapter = new GodsListAdapter(getActivity().getApplicationContext(),R.layout.gods_view,myGods);
		setListAdapter(adapter);
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		God_Bean godClicked = myGods.get(position);
		String message = "You selected " + godClicked.getGod_Name();
		Intent i = new Intent(getActivity(),TabGodDetails.class);
		Bundle b = new Bundle();
		b.putParcelable("BEAN", myGods.get(position));
		i.putExtras(b);
		startActivity(i);
	}

}