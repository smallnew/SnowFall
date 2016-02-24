package com.smallnew.snowfall.snow;

/**
 * Created by WangRuiPeng on 2016/2/22.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


import com.smallnew.snowfall.R;

import java.util.Iterator;

public class SnowView extends View {
    public static final String TAG = "SnowView";
    private int cacheCount = 8;
    private int snowCount = 25;
    private int playTimes = 3;
    private boolean faling;
    private Bitmap glodBitmap;
    private SnowFactory snowFactory;

    public SnowView(Context paramContext) {
        this(paramContext, null);
    }

    public SnowView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.glodBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gold_piece);
        this.faling = false;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.glodBitmap.recycle();
        this.faling = true;
        if (this.snowFactory != null)
            this.snowFactory.recycle();
    }

    protected void onDraw(Canvas paramCanvas) {
        super.onDraw(paramCanvas);
        if (this.snowFactory == null)
            this.snowFactory = new SnowFactory(this.cacheCount, this.snowCount, getWidth(), getHeight(), this.glodBitmap, this.playTimes);
        //cacheCount=8 snowCount=30 playTimes =1
        Paint localPaint = new Paint();
        localPaint.setARGB(255, 255, 255, 255);
        localPaint.setAntiAlias(true);
        localPaint.setStrokeWidth(20.0F);
        Iterator localIterator;
        if (!this.faling)
            return;

        this.snowFactory.fallSnow(this.faling);
        localIterator = this.snowFactory.products.iterator();
        while (true) {
            if (!localIterator.hasNext()) {
                postInvalidateDelayed(40L);
                return;
            }
            Snow localSnow = (Snow) localIterator.next();
            paramCanvas.drawBitmap(localSnow.snowBmp, localSnow.left, localSnow.top, localPaint);
        }

    }

    protected void onMeasure(int paramInt1, int paramInt2) {
        super.onMeasure(paramInt1, paramInt2);
    }

    public void startSnow() {
        this.faling = true;
        invalidate();
    }
}