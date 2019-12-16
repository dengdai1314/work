package com.demo.customview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView iv_src;
    ImageView iv_copy;
    Paint mpaint;
    Canvas canvas;
    Bitmap srcBitmap;
    Bitmap copyBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_src = findViewById(R.id.iv_src);
        iv_copy = findViewById(R.id.iv_copy);
        srcBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test);
        iv_src.setImageBitmap(srcBitmap);
        copyBitmap = Bitmap.createBitmap(srcBitmap.getWidth(),srcBitmap.getHeight(),srcBitmap.getConfig());
        mpaint = new Paint();
        canvas = new Canvas(copyBitmap);
        canvas.drawBitmap(srcBitmap,new Matrix(),mpaint);
        for (int i=0;i<20;i++){
            copyBitmap.setPixel(20+i,30, Color.RED);
        }
        iv_copy.setImageBitmap(copyBitmap);
    }
}
