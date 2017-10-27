package com.example.daniel.aplicativo02;

import android.os.Bundle;
import android.view.MotionEvent;

import com.example.daniel.aplicativo02.structure.SwipeActivity;

/**
 * Created by Daniel on 21/10/2017.
 */

public class Swipe01 extends SwipeActivity {
    private View01   mView;
    private InputManager mInputManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableFullScreen();
        enableKeepScreenOn();

        mView = new View01(this);
        setContentView(mView);
        mInputManager = new InputManager(this, mView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return mInputManager.onTouchEvent(event);
    }
}
