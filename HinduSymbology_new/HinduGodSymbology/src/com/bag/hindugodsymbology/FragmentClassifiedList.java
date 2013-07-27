package com.bag.hindugodsymbology;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;


public class FragmentClassifiedList extends SherlockFragment {
    
	private List<God_Bean> primaryMaleGods = new ArrayList<God_Bean>();
	private List<God_Bean> primaryFemaleGods = new ArrayList<God_Bean>();
	private List<God_Bean> sonsOfShiva = new ArrayList<God_Bean>();
	private List<God_Bean> itihasaPurusha = new ArrayList<God_Bean>();
	private ArrayList<God_Bean> beanList;
	private int totalMaleGods,totalFemaleGods,totalShivaSons,totalItihasaPurusha;

	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		Bundle b = getActivity().getIntent().getExtras();
		if(b!=null)
			beanList = b.getParcelableArrayList("BEANLIST");
		else
		{
			Log.d("This 4", "asdasd");
		}
		
		

        return inflater.inflate(R.layout.classification_layout, container, false);
    }
	
	@Override
	public void onStart() {
			super.onStart();
			populateGodsList();
			populateListView();
			primaryMaleRegisterClickCallback();
			primaryFemaleRegisterClickCallback();
			sonsOfShivaRegisterClickCallback();
			itihasaPurushaRegisterClickCallback();
	
	}


	
	private void populateGodsList() {
		// TODO Auto-generated method stub

		for(int i = 0 ;i < beanList.size();i++)
		{
			if(beanList.get(i).getClassification().equalsIgnoreCase("Primary Male"))
				primaryMaleGods.add(beanList.get(i));
			else
				if(beanList.get(i).getClassification().equalsIgnoreCase("Primary Female"))
					primaryFemaleGods.add(beanList.get(i));
				else
					if(beanList.get(i).getClassification().equalsIgnoreCase("Sons of Shiva"))
						sonsOfShiva.add(beanList.get(i));
					else					
						if(beanList.get(i).getClassification().equalsIgnoreCase("Itihasa Purusha"))
							itihasaPurusha.add(beanList.get(i));
		}
		
	}
	
	private void populateListView() {
		// TODO Auto-generated method stub
		ArrayAdapter<God_Bean> adapter 	= new GodsListAdapter(getActivity().getApplicationContext(),R.layout.gods_view,primaryMaleGods);
		ArrayAdapter<God_Bean> adapter1 = new GodsListAdapter(getActivity().getApplicationContext(),R.layout.gods_view,primaryFemaleGods);
		ArrayAdapter<God_Bean> adapter2 = new GodsListAdapter(getActivity().getApplicationContext(),R.layout.gods_view,sonsOfShiva);
		ArrayAdapter<God_Bean> adapter3 = new GodsListAdapter(getActivity().getApplicationContext(),R.layout.gods_view,itihasaPurusha);

		ListView list = (ListView) getActivity().findViewById(R.id.category1);
		list.setAdapter(adapter);
		ListView list1 = (ListView) getActivity().findViewById(R.id.category2);
		list1.setAdapter(adapter1);
		ListView list2 = (ListView) getActivity().findViewById(R.id.category3);
		list2.setAdapter(adapter2);
		ListView list3 = (ListView) getActivity().findViewById(R.id.category4);
		list3.setAdapter(adapter3);

	}

	private void primaryMaleRegisterClickCallback() {
		// TODO Auto-generated method stub
		ListView list = (ListView) getActivity().findViewById(R.id.category1);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
					long id) {

				Intent i = new Intent(getActivity(),TabGodDetails.class);
				Bundle b = new Bundle();
				b.putParcelable("BEAN", primaryMaleGods.get(position));
				i.putExtras(b);
				startActivity(i);
				
			
		}
		});
	}

	private void primaryFemaleRegisterClickCallback() {
		// TODO Auto-generated method stub
		ListView list = (ListView) getActivity().findViewById(R.id.category2);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
					long id) {

				Intent i = new Intent(getActivity(),TabGodDetails.class);
				Bundle b = new Bundle();
				b.putParcelable("BEAN", primaryFemaleGods.get(position));
				i.putExtras(b);
				startActivity(i);
				
			
		}
		});

}

	private void sonsOfShivaRegisterClickCallback() {
		// TODO Auto-generated method stub
		ListView list = (ListView) getActivity().findViewById(R.id.category3);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
					long id) {

				Intent i = new Intent(getActivity(),TabGodDetails.class);
				Bundle b = new Bundle();
				b.putParcelable("BEAN", sonsOfShiva.get(position));
				i.putExtras(b);
				startActivity(i);
				
			
		}
		});
	}


	private void itihasaPurushaRegisterClickCallback() {
		// TODO Auto-generated method stub
		ListView list = (ListView) getActivity().findViewById(R.id.category4);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
					long id) {

				Intent i = new Intent(getActivity(),TabGodDetails.class);
				Bundle b = new Bundle();
				b.putParcelable("BEAN", itihasaPurusha.get(position));
				i.putExtras(b);
				startActivity(i);
				
			
		}
		});
	}
}