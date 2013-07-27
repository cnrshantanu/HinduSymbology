package com.bag.hindugodsymbology;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class TabMarkerFragment extends SherlockFragment {

	@Override
	  public View onCreateView(LayoutInflater inflater, 
	                 ViewGroup container, Bundle savedInstanceState) {
	    // Inflate the layout for this fragment
	    View view = inflater.inflate(R.layout.god_details, container, false);
	   // do your view initialization here
	    return view;
	  }
	
}
