package com.example.daniel.aplicativo02;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Daniel on 21/10/2017.
 */

public class BallGenerator {
    public static final String TAG = "Aplicativo02";
    public static final float   BALL_RADIUS = 2.5f;
    public static final float   BALL_EXTERNAL_RADIUS = 10.0f;

    private int                 mNumberOfBalls;
    private float               mRadius;
    private float               mExternalRadius;
    private Canvas              mTempCanvas;
    private Point               mScreenDimensions = new Point();
    private Point               mTempPosition = new Point();
    private Paint               mTempPaint = new Paint();
    Random                      mRandom = new Random();

    private ArrayList<Point>    mBalls = new ArrayList<Point>();

    public BallGenerator(Canvas canvas, Point screenDimensions) {
        mTempCanvas = canvas;
        mScreenDimensions = screenDimensions;

        mRadius = (BALL_RADIUS / 100) * screenDimensions.x;
        mExternalRadius = (BALL_EXTERNAL_RADIUS / 100) * screenDimensions.x;
    }

    public void setNumberOfBalls(int numberOfBalls) {
        mNumberOfBalls = numberOfBalls;
    }

    public boolean detectCollision (Point position) {
        for (Point ball : mBalls) {
            if (position.y < ball.y - mExternalRadius ||
                    position.y > ball.y + mExternalRadius) {
                return false;
            } else {
                float x = (float) Math.sqrt(mExternalRadius * mExternalRadius -
                        (position.y - ball.y) * (position.y - ball.y));
                if (position.x < ball.x - x || position.x > ball.x + x) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public void createBall(int color) {
        int i = 20;
        do {
            mTempPosition.set(mRandom.nextInt(mScreenDimensions.x),
                    mRandom.nextInt(mScreenDimensions.y));
            i--;
        } while (detectCollision(mTempPosition) && i > 0);
        if (i == 0) {
            Log.e(TAG, "não foi possível criar a bola");
        } else {
            mBalls.add(new Point(mTempPosition.x, mTempPosition.y));
            mTempPaint.setColor(color);
            mTempPaint.setStyle(Paint.Style.FILL);
            mTempCanvas.drawCircle(mTempPosition.x, mTempPosition.y, mRadius, mTempPaint);
        }
    }

    public void createBall(int color, Point position) {
        mBalls.add(position);
        mTempPaint.setColor(color);
        mTempPaint.setStyle(Paint.Style.FILL);
        mTempCanvas.drawCircle(position.x, position.y, mRadius, mTempPaint);
    }

    public void createBall(int color, Point position, float radius) {
        mBalls.add(position);
        mTempPaint.setColor(color);
        mTempPaint.setStyle(Paint.Style.FILL);
        float r = (radius / 100) * mScreenDimensions.x;
        mTempCanvas.drawCircle(position.x, position.y, r, mTempPaint);
    }

    public ArrayList<Point> getBalls() {
        return mBalls;
    }
}
