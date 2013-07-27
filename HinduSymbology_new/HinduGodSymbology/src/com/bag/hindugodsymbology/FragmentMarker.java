package com.bag.hindugodsymbology;

import java.io.IOException;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.actionbarsherlock.app.SherlockFragment;

public class FragmentMarker extends SherlockFragment {
    
    private FrameLayout m_GodPicture;
    private God_Bean bean;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_goddetails_marker, container, false);
    }
    
	
	@Override
	public void onStart() {
			super.onStart();
			Bundle b = getActivity().getIntent().getExtras();
			if(b!=null)
			   bean = b.getParcelable("BEAN");
			
		m_GodPicture = (FrameLayout) getActivity().findViewById(R.id.goddetails);
		Drawable d = null;
		try {
			 d = Drawable.createFromStream(getActivity().getAssets().open(bean.getMain_Image()), null);//Setting the image here
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_GodPicture.setBackgroundDrawable(d);
	}
}