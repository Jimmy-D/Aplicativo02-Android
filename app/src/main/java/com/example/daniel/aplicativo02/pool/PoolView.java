package com.example.daniel.aplicativo02.pool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;

import com.example.daniel.aplicativo02.structure.SwipeView;
import com.example.daniel.aplicativo02.structure.Viewport;

/**
 * Created by Daniel on 28/10/2017.
 */

public class PoolView extends SwipeView {
    private Renderer2       mRenderer = new Renderer2();
    private ImageFactory    mImageFactory;
    private PoolTable       mTable;
    private Image           mImgTable;

    public PoolView(Context context, PoolTable table) {
        super(context);

        mTable = table;
        mImageFactory = new ImageFactory(context);
    }


    @Override
    public void step(Canvas canvas, float elapsedTimeInSeconds) {
        mTable.step(elapsedTimeInSeconds);
        mRenderer.beginDrawing(canvas, Color.LTGRAY, Color.rgb(0, 153, 51));

        mRenderer.drawBackgroundImage(mImgTable);
        mRenderer.drawAllBalls(mTable.getBallManager().getBallList());
    }

    @Override
    public void setup() {
        Point viewDimensions = getDimensions();
        Viewport viewport = new Viewport(mTable.getDimensions(), viewDimensions,
                Viewport.ScalingMode.FULL_SCREEN_KEEP_ORIGINAL_ASPECT);
        mRenderer.setViewport(viewport);

        mImgTable = mImageFactory.createImage("images/pool_table.png");

        mTable.setup();
    }

    public Renderer2 getRenderer() {
        return mRenderer;
    }
    public ImageFactory getImageFactory() {
        return mImageFactory;
    }
    public PoolTable getTable() {
        return mTable;
    }
    public Image getImgTable() {
        return mImgTable;
    }

}
