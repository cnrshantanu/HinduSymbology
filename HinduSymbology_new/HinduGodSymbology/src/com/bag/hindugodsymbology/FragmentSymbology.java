package com.bag.hindugodsymbology;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.actionbarsherlock.app.SherlockFragment;

public class FragmentSymbology extends SherlockFragment {
    
	private God_Bean bean;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.html_content, container, false);
    }
    
    @Override
  	public void onStart() {
    	super.onStart();
      	Bundle b = getActivity().getIntent().getExtras();
  		if(b!=null)
  		   bean = b.getParcelable("BEAN");
  		WebView wv = (WebView) getActivity().findViewById(R.id.wvHTMLContent);
  		wv.getSettings().setJavaScriptEnabled(true);
  		wv.loadUrl("file:///android_asset/"+bean.getSymbology());
       }

}