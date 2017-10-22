package com.example.daniel.aplicativo02.structure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;

/**
 * Created by Daniel on 12/10/2017.
 */

public class SwipeView extends View {
    private Point       mDimensions = new Point();
    private Stepwatch   mStepwatch = new Stepwatch();

    public SwipeView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        step(canvas, mStepwatch.tick());
//
//        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mDimensions.set(w, h);
    }

    public void step(Canvas canvas, float elapsedTimeInSeconds) {
    }

    public void setup() {
    }

    public Point getDimensions() {
        return mDimensions;
    }
}
