package com.example.daniel.aplicativo02;

import android.os.Bundle;

import com.example.daniel.aplicativo02.structure.SwipeActivity;
import com.example.daniel.aplicativo02.structure.SwipeView;

/**
 * Created by Daniel on 21/10/2017.
 */

public class Swipe01 extends SwipeActivity {
    private SwipeView   mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableFullScreen();
        enableKeepScreenOn();

        mView = new View01(this);
        setContentView(mView);
    }
}
