package com.hencoder.hencoderpracticelayout2.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hencoder.hencoderpracticelayout2.R;

/**
 * Created by wliu on 26/06/2018.
 */

public class Sample_AtMostLinearLayout extends LinearLayout {

    private boolean isHideCenter = false;
    Button leftButton, centerButton;

    public Sample_AtMostLinearLayout(Context context) {
        super(context);
    }

    public Sample_AtMostLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Sample_AtMostLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        leftButton = findViewById(R.id.sample_left);
        centerButton = findViewById(R.id.sample_center);

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = isHideCenter ? View.VISIBLE : View.GONE;
                isHideCenter = !isHideCenter;
                centerButton.setVisibility(visibility);
                String leftLabel = isHideCenter ? "SHOW CENTER BUTTON" : "HIDE CENTER BUTTON";
                leftButton.setText(leftLabel);
            }
        });
    }

}
