package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import android.util.Log;
import android.view.MotionEvent;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.Renderable;

/**
 * Created by dogtrollin on 8/17/17.
 */

public class MainUI extends Renderable implements UI {

    @Override
    public void start(GLRenderer renderer) {
        button = new Touchable() {
            @Override
            public float getX() {
                return mX;
            }

            @Override
            public float getY() {
                return mY;
            }

            @Override
            public float getZ() {
                return 0;
            }

            @Override
            public float getW() {
                return mW;
            }

            @Override
            public float getH() {
                return mH;
            }

            @Override
            public void init(GLRenderer renderer) {
                float scale = renderer.getScreenWidth() / 100;
                mW = scale * 50;
                mH = mW / 3;

                mX = renderer.getScreenWidth()/2 - mW/2;
                mY = renderer.getScreenHeight()/2 - mH/2;
            }

            private float mX, mY;
            private float mW, mH;

        };

        renderer.clearRenderables();
        renderer.registerRenderable(this);
        renderer.registerRenderable(button);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        if (button.checkTouch(event.getX(), event.getY()));
            Log.i("asfd", "zxcv");
        return true;
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public float getZ() {
        return -0.1f;
    }

    @Override
    public float getW() {
        return mW;
    }

    @Override
    public float getH() {
        return mH;
    }

    @Override
    public float getR() {
        return 0.1f;
    }

    @Override
    public float getG() {
        return 0.1f;
    }

    @Override
    public float getB() {
        return 0.4f;
    }

    @Override
    public void init(GLRenderer renderer) {
        mW = renderer.getScreenWidth();
        mH = renderer.getScreenHeight();
        renderTexture(false);

        button.init(renderer);
    }

    @Override
    public void render(GLRenderer renderer) {
        super.render(renderer);
        button.render(renderer);
    }

    private float mW, mH;
    private Touchable button;

}
