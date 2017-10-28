package com.example.daniel.aplicativo02;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by Daniel on 27/10/2017.
 */

public class BallTable {
    public final static float        BALL_RADIUS = 5.0f;
    public final static float        BALL_ACELERATION = 10.0f;

    private PointF      mBallPosition = new PointF();
    private PointF      mBallVelocity = new PointF();
    private PointF      mBallAceleration = new PointF();
    private float       mDesaceleration;
    private float       mRadius;
    private Point       mDimensions = new Point();
    private RectF       mFrame = new RectF();
    private float       mDistanceFromBorder;

    private boolean     mIsPressed;

    private boolean     mLeftCollided;
    private boolean     mTopCollided;
    private boolean     mRightCollided;
    private boolean     mBottomCollided;

    public BallTable(Point dimensions) {
        mDimensions = dimensions;
        mDistanceFromBorder = 0.05f * mDimensions.x;
        mFrame.set(mDistanceFromBorder, mDistanceFromBorder, (float) mDimensions.x -
                mDistanceFromBorder, (float) mDimensions.y - mDistanceFromBorder);
        mBallPosition.set((float) mDimensions.x / 2.0f, (float) mDimensions.y / 2.0f);
        mBallVelocity.set(0, 0);
        mRadius = (BALL_RADIUS / 100.0f) * mDimensions.x;
    }

    public void step(float elapsedTimeInSeconds) {
        if (isPressed()) {
            _collisionTest(mBallPosition);
        } else {
            mBallPosition.set(mBallPosition.x + mBallVelocity.x * elapsedTimeInSeconds,
                    mBallPosition.y + mBallVelocity.y * elapsedTimeInSeconds);
            mBallVelocity.set(mBallVelocity.x + mBallAceleration.x,
                    mBallVelocity.y + mBallAceleration.y);
            if (mBallVelocity.length() < mBallAceleration.length()) {
                mBallVelocity.set(0, 0);
                mBallAceleration.set(0, 0);
            }
            _collisionResult();
        }
    }

    public boolean IsPressed(PointF position) {
        if (position.y > mBallPosition.y - mRadius &&
                position.y < mBallPosition.y + mRadius) {
            float x = (float) Math.sqrt(mRadius * mRadius -
                    (position.y - mBallPosition.y) * (position.y - mBallPosition.y));
            if (position.x > mBallPosition.x - x && position.x < mBallPosition.x + x) {
                return true;
            }
        }
        return false;
    }

    private void _collisionResult() {
        if (mBallPosition.x - mRadius < mFrame.left && !mLeftCollided) {
            mBallVelocity.set(-mBallVelocity.x, mBallVelocity.y);
            mBallAceleration.set(-mBallAceleration.x, mBallAceleration.y);
            mLeftCollided = true;
            mTopCollided = false;
            mRightCollided = false;
            mBottomCollided = false;
        }else if (mBallPosition.y - mRadius < mFrame.top && !mTopCollided) {
            mBallVelocity.set(mBallVelocity.x, -mBallVelocity.y);
            mBallAceleration.set(mBallAceleration.x, -mBallAceleration.y);
            mLeftCollided = false;
            mTopCollided = true;
            mRightCollided = false;
            mBottomCollided = false;
        }else if (mBallPosition.x + mRadius > mFrame.right && !mRightCollided) {
            mBallVelocity.set(-mBallVelocity.x, mBallVelocity.y);
            mBallAceleration.set(-mBallAceleration.x, mBallAceleration.y);
            mLeftCollided = false;
            mTopCollided = false;
            mRightCollided = true;
            mBottomCollided = false;
        }else if (mBallPosition.y + mRadius > mFrame.bottom && !mBottomCollided) {
            mBallVelocity.set(mBallVelocity.x, -mBallVelocity.y);
            mBallAceleration.set(mBallAceleration.x, -mBallAceleration.y);
            mLeftCollided = false;
            mTopCollided = false;
            mRightCollided = false;
            mBottomCollided = true;
        }
    }

    private void _collisionTest(PointF position) {
        if (mBallPosition.x - mRadius < mFrame.left) {
            mBallPosition.x = mFrame.left + mRadius;
        }else if (mBallPosition.y - mRadius < mFrame.top) {
            mBallPosition.y = mFrame.top + mRadius;
        }if (mBallPosition.x + mRadius > mFrame.right) {
            mBallPosition.x = mFrame.right - mRadius;
        }if (mBallPosition.y + mRadius > mFrame.bottom) {
            mBallPosition.y = mFrame.bottom - mRadius;
        }
    }

    public void moveBall(float offsetX, float offsetY) {
        mBallPosition.x += offsetX;
        mBallPosition.y += offsetY;
    }

    public void resetCollisions() {
        mLeftCollided = false;
        mTopCollided = false;
        mRightCollided = false;
        mBottomCollided = false;
    }

    public void setBallPosition(PointF ballPosition) {
        mBallPosition = ballPosition;
    }

    public void setBallVelocity(PointF ballVelocity) {
        mBallVelocity = ballVelocity;
    }

    public void setBallAceleration(PointF ballAceleration) {
        mBallAceleration = ballAceleration;
    }

    public void setIsPressed(boolean isPressed) {
        mIsPressed = isPressed;
    }

    public void setFrame(RectF frame) {
        mFrame = frame;
    }

    public PointF getBallPosition() {
        return mBallPosition;
    }

    public float getRadius() {
        return mRadius;
    }

    public RectF getFrame() {
        return mFrame;
    }

    public float getDistanceFromBorder() {
        return mDistanceFromBorder;
    }

    public boolean isPressed() {
        return mIsPressed;
    }
}
