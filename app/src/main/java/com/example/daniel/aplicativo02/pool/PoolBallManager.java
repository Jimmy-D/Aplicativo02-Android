package com.example.daniel.aplicativo02.pool;

import android.graphics.PointF;

import java.util.ArrayList;

/**
 * Created by Daniel on 30/10/2017.
 */

public class PoolBallManager {
    private ArrayList<PoolBall>     mBallList = new ArrayList<PoolBall>();

    public PoolBallManager() {
    }

    public PoolBall createBall(PointF initialPosition, float radius, int color, boolean isClickable,
                           int number) {
        PoolBall ball = new PoolBall(initialPosition, radius, color, isClickable, number);
        mBallList.add(ball);
        return ball;
    }

    public void deleteBall(int number) {
        for (PoolBall ball : mBallList) {
            if (ball.getNumber() == number) {
                mBallList.remove(ball);
            }
        }
    }

    public ArrayList<PoolBall> getBallList() {
        return mBallList;
    }
}
