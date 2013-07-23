package com.religion.hindu.godsymbology;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class MarkerActivity extends Activity{
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_goddetails_marker);
        ImageView im = (ImageView) findViewById(R.id.imageView1);
        im.setBackgroundDrawable(getResources().getDrawable(R.drawable.launcher_bg));
	}

}
