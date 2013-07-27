package com.bag.hindugodsymbology;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class TabGodDetails extends SherlockFragmentActivity {
	  // store the active tab here
	  public static String ACTIVE_TAB = "activeTab";

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.main);

	        ActionBar actionbar = getSupportActionBar();
	        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	        Tab tab1 = actionbar.newTab().setText("Markers");
	        Tab tab2 = actionbar.newTab().setText("Symbology");
	        Tab tab3 = actionbar.newTab().setText("Summary");

	        tab1.setTabListener(new MyTabListener<FragmentMarker>(this, "tab1",
	                FragmentMarker.class));

	        tab2.setTabListener(new MyTabListener<FragmentWebview>(this, "tab2",
	                FragmentWebview.class));
	        
	        tab3.setTabListener(new MyTabListener<FragmentWebview>(this, "tab3",
	                FragmentWebview.class));

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
	  protected void onSaveInstanceState(Bundle outState) {
	    // save active tab
	    outState.putInt(ACTIVE_TAB,
	            getSupportActionBar().getSelectedNavigationIndex());
	    super.onSaveInstanceState(outState);
	  }
	}