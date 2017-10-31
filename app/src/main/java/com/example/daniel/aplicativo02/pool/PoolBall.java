package com.example.daniel.aplicativo02.pool;

import android.graphics.PointF;

/**
 * Created by Daniel on 30/10/2017.
 */

public class PoolBall {
    private PointF      mPosition;
    private PointF      mVelocity = new PointF();
    private float       mRadius;
    private int         mColor;
    private boolean     mIsClickable;
    private boolean     mIsPressed;
    private int         mNumber;

    public PoolBall(PointF initialPosition, float radius, int color, boolean isClickable,
                    int number) {
        mPosition = initialPosition;
        mRadius = radius;
        mColor = color;
        mIsClickable = isClickable;
        mNumber = number;

        mVelocity.set(0, 0);
    }

    public boolean isPressed(PointF position) {
        if (position.y > mPosition.y - mRadius &&
                position.y < mPosition.y + mRadius) {
            float x = (float) Math.sqrt(mRadius * mRadius -
                    (position.y - mPosition.y) * (position.y - mPosition.y));
            if (position.x > mPosition.x - x && position.x < mPosition.x + x) {
                mIsPressed = true;
            } else {
                mIsPressed = false;
            }
        } else {
            mIsPressed = false;
        }
        return mIsPressed;
    }

    public PointF getPosition() {
        return mPosition;
    }
    public void setPosition(PointF position) {
        mPosition = position;
    }
    public PointF getVelocity() {
        return mVelocity;
    }
    public void setVelocity(PointF velocity) {
        mVelocity = velocity;
    }
    public void setIsPressed(boolean isPressed) {
        mIsPressed = isPressed;
    }
    public float getRadius() {
        return mRadius;
    }
    public int getNumber() {
        return mNumber;
    }
    public int getColor() {
        return mColor;
    }
}
