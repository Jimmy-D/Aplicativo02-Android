package com.example.daniel.aplicativo02;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Daniel on 27/10/2017.
 */

public class BallController extends GestureDetector.SimpleOnGestureListener {
    public static final String TAG = "swipe2";
    private GestureDetector mGestureDetector;
    private View02          mView;
    private BallTable       mTable;

    private PointF          mTempPosition = new PointF();
    private PointF          mTempVelocity = new PointF();
    private PointF          mTempAceleration = new PointF();

    private boolean         mWasPressed;

    public BallController(Context context, View02 view) {
        mGestureDetector = new GestureDetector(context, this);
        mGestureDetector.setIsLongpressEnabled(false);
        mView = view;
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        if (action == MotionEvent.ACTION_UP) {
            onUp(event);
        }
        return mGestureDetector.onTouchEvent(event);
    }

    public boolean onUp(MotionEvent event) {
        Log.d(TAG, "onUp");
        mTable.setIsPressed(false);
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll");
        if (mTable.isPressed()) {
            mTable.moveBall(-distanceX, -distanceY);
        }
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "onFling");
        if (mWasPressed) {
            mTempVelocity.set(velocityX, velocityY);
//            mTable.setBallVelocity(mTempVelocity);
//            float lenght = mTempVelocity.length();
//            mTempAceleration.set(-(mTempVelocity.x / lenght) * BallTable.BALL_ACELERATION,
//                    -(mTempVelocity.y / lenght) * BallTable.BALL_ACELERATION);
//            mTable.setBallAceleration(mTempAceleration);

        }
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "onDown");
        mTable = mView.getTable();
        mTempPosition.set(e.getX(), e.getY());
        if (mTable.IsPressed(mTempPosition)) {
            mTable.setIsPressed(true);
            mTempVelocity.set(0, 0);
//            mTempAceleration.set(0, 0);
            mTable.setBallVelocity(mTempVelocity);
            mTable.resetCollisions();
            mWasPressed = true;
        } else {
            mWasPressed = false;
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG, "onLongPress");
    }
}
