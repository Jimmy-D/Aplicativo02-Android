package com.example.daniel.aplicativo02;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.Random;

/**
 * Created by Daniel on 25/10/2017.
 */

public class InputManager extends GestureDetector.SimpleOnGestureListener{
    public static final String TAG = "Aplicativo02";

    private GestureDetector mGestureDetector;
    private View01 mView;
    private Point mTempPosition = new Point();
    private Point mInitialPosition = new Point();
    private BallGenerator mBallGenerator;
    private BallGenerator.Ball mTempBall;
    private Random mRandom = new Random();
    private int mTempColor;
    private float mTempRadius;
    private boolean isColided;

    public InputManager(Context context, View01 view) {
        mGestureDetector = new GestureDetector(context, this);
        mView = view;
    }

    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        mBallGenerator = mView.getBallGenerator2();
        mInitialPosition.set((int) motionEvent.getX(), (int) motionEvent.getY());
        isColided = mBallGenerator.detectCollision(mInitialPosition);
        return true;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if (!isColided) {
            mTempColor = Color.rgb(mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
            mTempRadius = mRandom.nextFloat() * 10.0f + 2.0f;
            mBallGenerator.createBall(mTempColor, mInitialPosition, mTempRadius);
            mView.invalidate();
        }
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if(!isColided){
            mTempPosition.set((int) motionEvent1.getX(), (int) motionEvent1.getY());
            mTempBall = mBallGenerator.detectBallCollision(mTempPosition);
            if (mTempBall != null) {
                mBallGenerator.deleteBall(mTempBall);
                mView.invalidate();
            }
        }
        return true;
    }
}
