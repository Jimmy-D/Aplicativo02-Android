package com.example.daniel.aplicativo02.pool;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Daniel on 30/10/2017.
 */

public class PoolInputManager extends GestureDetector.SimpleOnGestureListener{
    private GestureDetector mGestureDetector;
    private PoolView        mView;
    private PoolTable       mTable;

    private PointF          mTempPosition = new PointF();
    private PointF          mTempVelocity = new PointF();
    private PointF          mScallingFactor;
    private Point           mOffsetFromOrigin;

    private boolean         mWasPressed;

    public PoolInputManager(Context context, PoolView view) {
        mGestureDetector = new GestureDetector(context, this);
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
        mTable.getWhiteBall().setIsPressed(false);
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (mWasPressed) {
            if (mTable.hasNoMovement()) {
                mTempVelocity.set(velocityX / mScallingFactor.x, velocityY / mScallingFactor.y);
            }
        }
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        mTable = mView.getTable();
        mScallingFactor = mView.getRenderer().getViewport().getScalingFactor();
        mOffsetFromOrigin = mView.getRenderer().getViewport().getOffsetFromOrigin();
        mTempPosition.set(e.getX() / mScallingFactor.x - mOffsetFromOrigin.x,
                e.getY() / mScallingFactor.y - mOffsetFromOrigin.y);
        if (mTable.getWhiteBall().isPressed(mTempPosition)) {
            mTempVelocity.set(0, 0);
            mTable.getWhiteBall().setVelocity(mTempVelocity);
            mWasPressed = true;
        } else {
            mWasPressed = false;
        }
        return true;
    }
}
