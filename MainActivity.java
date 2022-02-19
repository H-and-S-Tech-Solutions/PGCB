package com.example.proceduralcolouringbook;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    ImageView cow;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cow = findViewById(R.id.imageCow);
        cow.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                System.out.println("TOUCHED");
                Drawable dr = ((ImageView)cow).getDrawable();
                Bitmap bmp =  ((BitmapDrawable)dr.getCurrent()).getBitmap();
                bmp = bmp.copy(bmp.getConfig(), true);
                //bmp.setHeight(cow.getHeight());
                //bmp.setWidth(cow.getWidth());
                System.out.println("X: " + (int)event.getX()*(bmp.getWidth() / cow.getWidth()) + ", Y: " + (int)event.getY() * (bmp.getHeight() / cow.getHeight()));
                //this keeps it from crashing but as of now it doesn't get the right co-ordinates
                //maybe wont matter if we generate the bitmap into a new file from all the random pieces?
                //seems to only be an issue with the fact that the bitmap is scaled up in the imageview
                //OK THE ABOVE PRINTLINE SEEMS TO SUGGEST ITS BECAUSE ITS AN INTEGER
                //the ratio is a fraction so it's probably counting that as 0 and then multiplying by 0 to get 0
                Point mPoint = new Point((int)event.getX()*(bmp.getWidth() / cow.getWidth()), (int)event.getY() * (bmp.getHeight() / cow.getHeight()));
                int mTargetColor = bmp.getPixel(mPoint.x, mPoint.y);
                System.out.println(mTargetColor);
                int mNewColor = Color.BLUE;
                FloodFillThread f = new FloodFillThread(bmp, mPoint, mTargetColor, mNewColor);
                f.run();
                cow.setImageBitmap(bmp);
                cow.invalidate();
                return false;
            }
        });
    }
}