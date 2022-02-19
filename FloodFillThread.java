package com.example.proceduralcolouringbook;

import android.graphics.Bitmap;
import android.graphics.Point;

public class FloodFillThread extends Thread
{
    Bitmap mBitmap;
    int mTargetColor;
    int mNewColor;
    Point mPoint;

    public FloodFillThread(Bitmap bitmap, Point pt, int targetColor, int newColor)
    {
        mBitmap = bitmap;
        mPoint = pt;
        mTargetColor = targetColor;
        mNewColor = newColor;
    }

    @Override
    public void run()
    {
        QueueLinearFloodFiller filler = new QueueLinearFloodFiller(mBitmap, mTargetColor, mNewColor);

        filler.setTolerance(10);
        filler.floodFill(mPoint.x, mPoint.y);
        System.out.println("FLOOD FILL DONE");
    }
}