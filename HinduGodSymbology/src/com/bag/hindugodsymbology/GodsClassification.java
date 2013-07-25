package com.bag.hindugodsymbology;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;





import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GodsClassification extends Activity{

	private List<God_Bean> primaryMaleGods = new ArrayList<God_Bean>();
	private List<God_Bean> primaryFemaleGods = new ArrayList<God_Bean>();
	private List<God_Bean> sonsOfShiva = new ArrayList<God_Bean>();
	private List<God_Bean> itihasaPurusha = new ArrayList<God_Bean>();
	private String[] maleGods, femaleGods,shivaSons,historyPurusha;

	ArrayList<God_Bean> beanList;

	int totalMaleGods,totalFemaleGods,totalShivaSons,totalItihasaPurusha;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classification_layout);

		Button bAlpha = (Button) findViewById(R.id.bAlphabetical);

		Bundle b = this.getIntent().getExtras();
		if(b!=null)
			beanList = b.getParcelableArrayList("BEANLIST");
		else
		{
			Log.d("This 4", "asdasd");
		}

		bAlpha.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Class ourClass;
				try
				{
					ourClass = Class.forName("com.bag.hindugodsymbology.GodsAlphabeticalActivity");

					Intent i = new Intent(GodsClassification.this, ourClass);
					Bundle b = new Bundle();
					b.putParcelableArrayList("BEANLIST",  beanList);
					i.putExtras(b);

					startActivity(i);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});




		/*maleGods = getResources().getStringArray(R.array.primary_male);
		femaleGods = getResources().getStringArray(R.array.primary_female);
		shivaSons= getResources().getStringArray(R.array.shiva_sons);
		historyPurusha = getResources().getStringArray(R.array.itihasa_purusha);

		totalFemaleGods = femaleGods.length;
		totalMaleGods = maleGods.length;
		totalShivaSons = shivaSons.length;
		totalItihasaPurusha= historyPurusha.length;
*/
		populateGodsList();
		//populateFemaleGodsList();
		populateListView();
		primaryMaleRegisterClickCallback();
		primaryFemaleRegisterClickCallback();
		sonsOfShivaRegisterClickCallback();
		itihasaPurushaRegisterClickCallback();
		//registerClickCallback();
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
		ArrayAdapter<God_Bean> adapter = new MyListAdapter();
		ArrayAdapter<God_Bean> adapter1 = new MyListAdapter1();
		ArrayAdapter<God_Bean> adapter2 = new MyListAdapter2();
		ArrayAdapter<God_Bean> adapter3 = new MyListAdapter3();

		ListView list = (ListView) findViewById(R.id.category1);
		list.setAdapter(adapter);
		ListView list1 = (ListView) findViewById(R.id.category2);
		list1.setAdapter(adapter1);
		ListView list2 = (ListView) findViewById(R.id.category3);
		list2.setAdapter(adapter2);
		ListView list3 = (ListView) findViewById(R.id.category4);
		list3.setAdapter(adapter3);

		/*

		else
		{
			ListView list = (ListView) findViewById(R.id.category1);
			list.setAdapter(adapter1);
		}*/
	}


	private class MyListAdapter extends ArrayAdapter<God_Bean>{
		public MyListAdapter()
		{
			super(GodsClassification.this, R.layout.gods_view, primaryMaleGods);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View itemView = convertView;

			if(itemView == null)
			{
				itemView = getLayoutInflater().inflate(R.layout.gods_view, parent, false);

			}

			God_Bean currentGod = primaryMaleGods.get(position);

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

		        imageview.setImageBitmap(bitmap);

			//God's Name
			TextView textview = (TextView) itemView.findViewById(R.id.laxmiMata);
			textview.setText(currentGod.getGod_Name());


			return itemView;
			//return super.getView(position, convertView, parent);
		}

	}



	private class MyListAdapter1 extends ArrayAdapter<God_Bean>{
		public MyListAdapter1()
		{
			super(GodsClassification.this, R.layout.gods_view, primaryFemaleGods);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View itemView = convertView;

			if(itemView == null)
			{
				itemView = getLayoutInflater().inflate(R.layout.gods_view, parent, false);

			}

			God_Bean currentGod = primaryFemaleGods.get(position);

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

		        imageview.setImageBitmap(bitmap);

			//God's Name
			TextView textview = (TextView) itemView.findViewById(R.id.laxmiMata);
			textview.setText(currentGod.getGod_Name());


			return itemView;
			//return super.getView(position, convertView, parent);
		}


	}

	private class MyListAdapter2 extends ArrayAdapter<God_Bean>{
		public MyListAdapter2()
		{
			super(GodsClassification.this, R.layout.gods_view, sonsOfShiva);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View itemView = convertView;

			if(itemView == null)
			{
				itemView = getLayoutInflater().inflate(R.layout.gods_view, parent, false);

			}

			God_Bean currentGod = sonsOfShiva.get(position);

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

		        imageview.setImageBitmap(bitmap);

			//God's Name
			TextView textview = (TextView) itemView.findViewById(R.id.laxmiMata);
			textview.setText(currentGod.getGod_Name());


			return itemView;
			//return super.getView(position, convertView, parent);
		}


	}


	private class MyListAdapter3 extends ArrayAdapter<God_Bean>{
		public MyListAdapter3()
		{
			super(GodsClassification.this, R.layout.gods_view, itihasaPurusha);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View itemView = convertView;

			if(itemView == null)
			{
				itemView = getLayoutInflater().inflate(R.layout.gods_view, parent, false);

			}

			God_Bean currentGod = itihasaPurusha.get(position);

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

		        imageview.setImageBitmap(bitmap);

			//God's Name
			TextView textview = (TextView) itemView.findViewById(R.id.laxmiMata);
			textview.setText(currentGod.getGod_Name());


			return itemView;
			//return super.getView(position, convertView, parent);
		}

	}


	private void primaryMaleRegisterClickCallback() {
		// TODO Auto-generated method stub
		ListView list = (ListView) findViewById(R.id.category1);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
					long id) {

				Class ourClass;
				try
				{
				ourClass = Class.forName("com.bag.hindugodsymbology.GodDetails");

				Intent i = new Intent(GodsClassification.this, ourClass);
				Bundle b = new Bundle();
				b.putParcelable("BEAN", primaryMaleGods.get(position));
				i.putExtras(b);
				//i.setClass(this, GodDetails.class);
				startActivity(i);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		});
	}

	private void primaryFemaleRegisterClickCallback() {
		// TODO Auto-generated method stub
		ListView list = (ListView) findViewById(R.id.category2);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
					long id) {

				Class ourClass;
				try
				{
				ourClass = Class.forName("com.bag.hindugodsymbology.GodDetails");

				Intent i = new Intent(GodsClassification.this, ourClass);
				Bundle b = new Bundle();
				b.putParcelable("BEAN", primaryFemaleGods.get(position));
				i.putExtras(b);
				//i.setClass(this, GodDetails.class);
				startActivity(i);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		});
	}

	private void sonsOfShivaRegisterClickCallback() {
		// TODO Auto-generated method stub
		ListView list = (ListView) findViewById(R.id.category3);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
					long id) {

				Class ourClass;
				try
				{
				ourClass = Class.forName("com.bag.hindugodsymbology.GodDetails");

				Intent i = new Intent(GodsClassification.this, ourClass);
				Bundle b = new Bundle();
				b.putParcelable("BEAN", sonsOfShiva.get(position));
				i.putExtras(b);
				//i.setClass(this, GodDetails.class);
				startActivity(i);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		});
	}


	private void itihasaPurushaRegisterClickCallback() {
		// TODO Auto-generated method stub
		ListView list = (ListView) findViewById(R.id.category4);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
					long id) {
				Class ourClass;
				try
				{
				ourClass = Class.forName("com.bag.hindugodsymbology.GodDetails");

				Intent i = new Intent(GodsClassification.this, ourClass);
				Bundle b = new Bundle();
				b.putParcelable("BEAN", itihasaPurusha.get(position));
				i.putExtras(b);
				//i.setClass(this, GodDetails.class);
				startActivity(i);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		});
	}
	


}
