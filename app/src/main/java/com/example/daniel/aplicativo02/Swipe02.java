package com.example.daniel.aplicativo02;

import android.os.Bundle;
import android.view.MotionEvent;

import com.example.daniel.aplicativo02.structure.SwipeActivity;

/**
 * Created by Daniel on 27/10/2017.
 */

public class Swipe02 extends SwipeActivity {
    private View02 mView;
    private BallController mBallController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableKeepScreenOn();
        enableFullScreen();

        mView = new View02(this);
        setContentView(mView);
        mBallController = new BallController(this, mView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mBallController.onTouchEvent(event);
    }
}
