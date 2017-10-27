package com.example.daniel.aplicativo02.structure;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.daniel.aplicativo02.BallGenerator;

import java.util.ArrayList;

/**
 * Created by Daniel on 22/10/2017.
 */

public class Renderer {
    private Canvas mTempCanvas;
    private Point mTempPosition = new Point();
    private Paint mTempPaint = new Paint();

    public Renderer() {
    }

    public void beginDrawing(Canvas canvas) {
        mTempCanvas = canvas;
    }

    public void drawAllBalls(ArrayList<BallGenerator.Ball> balls) {
        for (BallGenerator.Ball ball : balls) {
            drawBall(ball);
        }
    }

    public void drawBall(Point position, float radius, int color) {
        mTempPaint.setColor(color);
        mTempPaint.setStyle(Paint.Style.FILL);
        mTempCanvas.drawCircle(position.x, position.y, radius, mTempPaint);
    }

    public void drawBall(BallGenerator.Ball ball) {
        mTempPaint.setColor(ball.getColor());
        mTempPaint.setStyle(Paint.Style.FILL);
        mTempCanvas.drawCircle(ball.getPosition().x, ball.getPosition().y,
                ball.getRadius(), mTempPaint);
    }
}
