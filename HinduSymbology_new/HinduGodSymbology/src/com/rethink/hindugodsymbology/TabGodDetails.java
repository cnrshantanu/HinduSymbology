package com.rethink.hindugodsymbology;

import android.os.Bundle;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

public class TabGodDetails extends SherlockFragmentActivity {
	  // store the active tab here
	  public static String ACTIVE_TAB = "activeTab";

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.main);
		  	
		  	getSupportActionBar().setDisplayShowHomeEnabled(false);
		  	getSupportActionBar().setDisplayShowTitleEnabled(false);

	        ActionBar actionbar = getSupportActionBar();
	        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	        Tab tab1 = actionbar.newTab().setText("Markers");
	        Tab tab2 = actionbar.newTab().setText("Symbology");
	        Tab tab3 = actionbar.newTab().setText("Summary");

	        tab1.setTabListener(new MyTabListener<FragmentMarker>(this, "tab1",
	                FragmentMarker.class));

	        tab2.setTabListener(new MyTabListener<FragmentSymbology>(this, "tab2",
	                FragmentSymbology.class));
	        
	        tab3.setTabListener(new MyTabListener<FragmentSummary>(this, "tab3",
	                FragmentSummary.class));

	        actionbar.addTab(tab1);
	        actionbar.addTab(tab2);
	        actionbar.addTab(tab3);

	    // check if there is a saved state to select active tab
	    if( savedInstanceState != null ){
	      getSupportActionBar().setSelectedNavigationItem(
	                  savedInstanceState.getInt(ACTIVE_TAB));
	    }
	  }
	  
	  @Override
		 public boolean  onCreateOptionsMenu(Menu menu) {
			// super.onCreateOptionsMenu(menu,inflater);
		  
		  
			return true;
			 
		 }
	  @Override
	  protected void onSaveInstanceState(Bundle outState) {
	    // save active tab
	    outState.putInt(ACTIVE_TAB,
	            getSupportActionBar().getSelectedNavigationIndex());
	    super.onSaveInstanceState(outState);
	  }
	}