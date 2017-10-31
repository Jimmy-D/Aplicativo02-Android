package com.example.daniel.aplicativo02.pool;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;

/**
 * Created by Daniel on 30/10/2017.
 */

public class PoolTable {
    public static final float   BALL_RADIUS = 12.8f;
    public static final float   COR = 0.95f;
    public static final float   DESACELATION_RATIO = 0.98f;
    public static final int     DISTANCE_FROM_BOARDER = 54;

    private Point               mDimensions;
    private PointF              mTempDistanceVector = new PointF();

    private PoolBallManager     mBallManager;
    private PoolBall            mTempBall;
    private PoolBall            mWhiteBall;

    public PoolTable(Point dimensions) {
        mDimensions = new Point(dimensions);

        mBallManager = new PoolBallManager();
    }

    public void setup() {
        mWhiteBall = mBallManager.createBall(new PointF(600, 300), BALL_RADIUS, Color.WHITE, true,
                0);
        mBallManager.createBall(new PointF(300, 300), BALL_RADIUS, Color.YELLOW, true, 0);
    }

    public void step(float elapsedTimeInSeconds) {
        for (PoolBall ball : mBallManager.getBallList()) {
            ball.getPosition().set(
                    ball.getPosition().x + ball.getVelocity().x * elapsedTimeInSeconds,
                    ball.getPosition().y + ball.getVelocity().y * elapsedTimeInSeconds);
            ball.getVelocity().set(ball.getVelocity().x * DESACELATION_RATIO,
                    ball.getVelocity().y * DESACELATION_RATIO);
            if (ball.getVelocity().length() < 1) {
                ball.getVelocity().set(0, 0);
            }
        }
    }

    public boolean colidedWithBall(PoolBall ball1, PoolBall ball2) {
        mTempDistanceVector.set(ball2.getPosition().x - ball1.getPosition().x,
                ball2.getPosition().y - ball1.getPosition().y);
        float centerDistance = mTempDistanceVector.length();
        if (centerDistance >= ball1.getRadius() + ball2.getRadius()) {
            return false;
        } else {
            float p1 = _dotOperation(mTempDistanceVector, ball1.getPosition()) /
                    _dotOperation(mTempDistanceVector, mTempDistanceVector);
            PointF proj1 = new PointF(mTempDistanceVector.x * p1, mTempDistanceVector.y * p1);
            float p2 = _dotOperation(mTempDistanceVector, ball2.getPosition()) /
                    _dotOperation(mTempDistanceVector, mTempDistanceVector);
            PointF proj2 = new PointF(mTempDistanceVector.x * p2, mTempDistanceVector.y * p2);
            PointF result = new PointF(proj1.x - proj2.x, proj1.y - proj2.y);
            if (_dotOperation(result, mTempDistanceVector) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNoMovement() {
        for (PoolBall ball : mBallManager.getBallList()) {
            if (ball.getVelocity().length() != 0) {
                return false;
            }
        }
        return true;
    }

    private float _dotOperation(PointF x, PointF y) {
        float result = x.x * y.x + x.y * y.y;
        return result;
    }


    public Point getDimensions() {
        return mDimensions;
    }
    public PoolBallManager getBallManager() {
        return mBallManager;
    }
    public PoolBall getWhiteBall() {
        return mWhiteBall;
    }
}
