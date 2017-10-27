package com.example.daniel.aplicativo02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;

import com.example.daniel.aplicativo02.structure.Renderer;
import com.example.daniel.aplicativo02.structure.SwipeView;

/**
 * Created by Daniel on 21/10/2017.
 */

public class View01 extends SwipeView {
    private BallGenerator mBallGenerator1;
    private BallGenerator mBallGenerator2;
    private Renderer mRenderer = new Renderer();
    private Point mPosition = new Point();

    public View01(Context context) {
        super(context);
    }

    @Override
    public void setup() {
        Point viewDimensions = getDimensions();

        mBallGenerator1 = new BallGenerator(viewDimensions);
        float distance = viewDimensions.x / 40.0f;
        for(int y = (int) distance / 2; y < (viewDimensions.y); y = y + (int) distance) {
            for(int x = (int) distance / 2; x < (viewDimensions.x); x = x + (int) distance) {
                mPosition.set(x, y);
                mBallGenerator1.createBall(Color.rgb(180,180,180), mPosition, 0.5f);
            }
        }

        mBallGenerator2 = new BallGenerator(viewDimensions);
        mBallGenerator2.createBall(Color.RED);
        mBallGenerator2.createBall(Color.BLUE);
        mBallGenerator2.createBall(Color.YELLOW);
        mBallGenerator2.createBall(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mRenderer.beginDrawing(canvas);
        canvas.drawColor(Color.LTGRAY);
        mRenderer.drawAllBalls(mBallGenerator1.getBalls());
        mRenderer.drawAllBalls(mBallGenerator2.getBalls());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    public BallGenerator getBallGenerator1() {
        return mBallGenerator1;
    }

    public BallGenerator getBallGenerator2() {
        return mBallGenerator2;
    }
}
