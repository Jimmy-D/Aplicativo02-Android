package com.example.daniel.aplicativo02.pool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Region;
import android.os.Build;

import com.example.daniel.aplicativo02.structure.Viewport;

import java.util.ArrayList;

/**
 * Created by Daniel on 29/10/2017.
 */

public class Renderer2 {
    private Canvas mTempCanvas;
    private PointF mTempPosition = new PointF();
    private float mTempRadius;
    private Paint mTempPaint = new Paint();
    private Viewport    mViewport;

    public Renderer2() {
    }

    @SuppressWarnings("deprecation")
    public void beginDrawing(Canvas canvas, int screenColor, int viewportColor) {
        mTempCanvas = canvas;
        mTempCanvas.drawColor(screenColor);
        if (mViewport != null) {
            if (Build.VERSION.SDK_INT < 26) {
                canvas.clipRect(mViewport.getDrawingArea(), Region.Op.REPLACE);
            } else {
                canvas.clipOutRect(mViewport.getDrawingArea());
            }
            canvas.drawColor(viewportColor);
        }
    }

    public void drawBackgroundImage(Image image) {
        if (mViewport != null) {
            if (image != null) {
                Bitmap bitmap = image.getBitmap();
                mTempCanvas.drawBitmap(bitmap, null, mViewport.getDrawingArea(), mTempPaint);
            }
        }
    }

    public void drawBall(PoolBall ball) {
        if (mViewport != null) {
            Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
            PointF scallingFactor = mViewport.getScalingFactor();

            mTempPosition.set(ball.getPosition().x * scallingFactor.x + offsetFromOrigin.x,
                    ball.getPosition().y * scallingFactor.y + offsetFromOrigin.y);
            mTempRadius = ball.getRadius() * scallingFactor.x;

            mTempPaint.setColor(ball.getColor());
            mTempPaint.setStyle(Paint.Style.FILL);
            mTempCanvas.drawCircle(mTempPosition.x, mTempPosition.y, mTempRadius,
                    mTempPaint);
        }

    }

    public void drawAllBalls(ArrayList<PoolBall> list) {
        for (PoolBall ball : list) {
            drawBall(ball);
        }
    }

    public void setViewport(Viewport viewport) {
        mViewport = viewport;
    }
    public Viewport getViewport() {
        return mViewport;
    }
}
