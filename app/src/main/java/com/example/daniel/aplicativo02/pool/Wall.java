package com.example.daniel.aplicativo02.pool;

import android.graphics.Color;
import android.graphics.PointF;

/**
 * Created by Daniel on 29/10/2017.
 */

public class Wall {
    public enum wallPosition {
        HORIZONTAL,
        VERTICAL,
        DIAGONAL
    }

    private boolean     mIsVisible;
    private String      mCategory;
    private int         mDebugColor = Color.RED;
    private int         mId;
    private PointF      mInicialPoint = new PointF();
    private PointF      mFinalPoint = new PointF();
    private PointF      normalVector = new PointF();

}
