package com.smallnew.snowfall;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.smallnew.snowfall.snow.SnowView;

public class SnowFallActivity extends AppCompatActivity {
    SnowView snowView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snow_fall);
        snowView = (SnowView)this.findViewById(R.id.snow);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                snowView.startSnow();
            }
        }, 1000);

    }
}
