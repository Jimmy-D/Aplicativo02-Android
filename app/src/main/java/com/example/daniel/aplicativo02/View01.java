package com.example.daniel.aplicativo02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;

import com.example.daniel.aplicativo02.structure.SwipeView;

/**
 * Created by Daniel on 21/10/2017.
 */

public class View01 extends SwipeView {
    private BallGenerator mBallGenerator;

    public View01(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Point viewDimensions = getDimensions();
        mBallGenerator = new BallGenerator(canvas, viewDimensions);
        canvas.drawColor(Color.LTGRAY);
        mBallGenerator.createBall(Color.RED);
        mBallGenerator.createBall(Color.BLUE);
        mBallGenerator.createBall(Color.YELLOW);
        mBallGenerator.createBall(Color.GREEN);
    }
}
