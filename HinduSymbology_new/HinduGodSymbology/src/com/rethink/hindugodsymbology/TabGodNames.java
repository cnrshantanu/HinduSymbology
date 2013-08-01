package com.rethink.hindugodsymbology;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
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
		  
		  getSupportActionBar().setDisplayShowHomeEnabled(false);
		  getSupportActionBar().setDisplayShowTitleEnabled(false);
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
	  
	  
	}