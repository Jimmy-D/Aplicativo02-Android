package com.example.daniel.aplicativo02.pool;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;

/**
 * Created by Daniel on 30/10/2017.
 */

public class PoolTable {
    public static final float   BALL_RADIUS = 30.0f;
    public static final float   COR = 0.95f;
    public static final float   DESACELATION_RATIO = 0.98f;
    public static final int     DISTANCE_FROM_BOARDER = 54;

    private Point               mDimensions;
    private PointF              mTempDistanceVector = new PointF();

    private PoolBallManager     mBallManager;
    private PoolBall            mWhiteBall;

    public PoolTable(Point dimensions) {
        mDimensions = new Point(dimensions);

        mBallManager = new PoolBallManager();
    }

    public void setup() {
        mWhiteBall = mBallManager.createBall(new PointF(600, 300), BALL_RADIUS, Color.WHITE, 0);
        mBallManager.createBall(new PointF(400, 270), BALL_RADIUS, Color.YELLOW, 1);
        mBallManager.createBall(new PointF(400, 330), BALL_RADIUS, Color.BLUE, 2);
    }

    public void step(float elapsedTimeInSeconds) {
        if(elapsedTimeInSeconds > 1.0f)
        {
            elapsedTimeInSeconds = 0.1f;
        }
        for (PoolBall ball : mBallManager.getBallList()) {
            ball.getPosition().set(
                    ball.getPosition().x + ball.getVelocity().x * elapsedTimeInSeconds,
                    ball.getPosition().y + ball.getVelocity().y * elapsedTimeInSeconds);
            ball.getVelocity().set(ball.getVelocity().x * DESACELATION_RATIO,
                    ball.getVelocity().y * DESACELATION_RATIO);
            if (ball.getVelocity().length() < 1) {
                ball.getVelocity().set(0, 0);
            }
            for (PoolBall ball2 : mBallManager.getBallList()) {
                if (ball != ball2) {
                    colidedWithBall(ball, ball2);
                }
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
            float p1 = _dotOperation(mTempDistanceVector, ball1.getVelocity()) /
                    _dotOperation(mTempDistanceVector, mTempDistanceVector);
            PointF projV1 = new PointF(mTempDistanceVector.x * p1, mTempDistanceVector.y * p1);
            float p2 = _dotOperation(mTempDistanceVector, ball2.getVelocity()) /
                    _dotOperation(mTempDistanceVector, mTempDistanceVector);
            PointF projV2 = new PointF(mTempDistanceVector.x * p2, mTempDistanceVector.y * p2);
            PointF result = new PointF(projV1.x - projV2.x, projV1.y - projV2.y);
            if (_dotOperation(result, mTempDistanceVector) > 0) {
                PointF rject1 = new PointF(ball1.getVelocity().x - projV1.x,
                        ball1.getVelocity().y - projV1.y);
                PointF rject2 = new PointF(ball2.getVelocity().x - projV2.x,
                        ball2.getVelocity().y - projV2.y);
                float Vx = (COR * (projV2.x - projV1.x) + (projV1.x + projV2.x)) / 2;
                float Vy = (COR * (projV2.y - projV1.y) + (projV1.y + projV2.y)) / 2;
                PointF projV1After = new PointF(Vx, Vy);
                Vx = (COR * (projV1.x - projV2.x) + (projV1.x + projV2.x)) / 2;
                Vy = (COR * (projV1.y - projV2.y) + (projV1.y + projV2.y)) / 2;
                PointF projV2After = new PointF(Vx, Vy);
                ball1.setVelocity(projV1After.x + rject1.x, projV1After.y + rject1.y);
                ball2.setVelocity(projV2After.x + rject2.x, projV2After.y + rject2.y);
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
