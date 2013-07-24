package com.religion.hindu.godsymbology;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();

    public DrawView(Context context) {
        super(context);
    }
    
    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void onDraw(Canvas canvas) {
        int w = canvas.getWidth(), h=canvas.getHeight();
        paint.setColor(Color.GREEN);
        paint.setTextSize(20);
        canvas.drawText("Android", w/2, h/2, paint);
    }

}
