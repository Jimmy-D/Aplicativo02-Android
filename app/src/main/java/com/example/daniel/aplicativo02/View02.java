package com.example.daniel.aplicativo02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.RectF;

import com.example.daniel.aplicativo02.structure.Renderer;
import com.example.daniel.aplicativo02.structure.SwipeView;

/**
 * Created by Daniel on 27/10/2017.
 */

public class View02 extends SwipeView {
    private BallTable   mTable;
    private Renderer    mRenderer = new Renderer();
    private RectF       mTableFrame = new RectF();
    private float       mBallRadius;
    private float       mDistanceFromBorder;

    public View02(Context context) {
        super(context);
    }

    @Override
    public void setup() {
        Point viewDimensions = getDimensions();

        mTable = new BallTable(viewDimensions);
        mTableFrame = mTable.getFrame();
        mDistanceFromBorder = mTable.getDistanceFromBorder();
        mBallRadius = mTable.getRadius();
    }

    @Override
    public void step(Canvas canvas, float elapsedTimeInSeconds) {
        mRenderer.beginDrawing(canvas);
        canvas.drawColor(Color.WHITE);
        mRenderer.drawFrame(mTableFrame, mDistanceFromBorder / 2.0f, Color.WHITE, Color.BLUE);
        mTable.step(elapsedTimeInSeconds);
        mRenderer.drawBall(mTable.getBallPosition(), mBallRadius, Color.RED);
    }

    public BallTable getTable() {
        return mTable;
    }
}
