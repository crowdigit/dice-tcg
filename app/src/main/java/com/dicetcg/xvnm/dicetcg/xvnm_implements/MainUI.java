package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import android.view.MotionEvent;

import com.dicetcg.xvnm.dicetcg.MainActivity;
import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.Renderable;

/**
 * Created by dogtrollin on 8/17/17.
 */

public class MainUI extends Renderable implements UI {

    public MainUI(MainActivity activity) {
        mActivity = activity;
    }

    private MainActivity mActivity;

    @Override
    public void start() {
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

        mActivity.getRenderer().clearRenderables();
        mActivity.getRenderer().registerRenderable(this);
        mActivity.getRenderer().registerRenderable(button);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        if (!mFadeStarted && button.checkTouch(event.getX(), mH - event.getY())) {
            // mActivity.setCurrentUI(1);
            mFadeStartedTime = System.currentTimeMillis();
            mFadeStarted = true;
        }
        return false;
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

    @Override
    public void prerender(GLRenderer renderer) {
        if (mFadeStarted) {
            float fade = (1000 - (System.currentTimeMillis() - mFadeStartedTime)) / 1000.f;
            if (fade < 0)
                mActivity.setCurrentUI(1);
            renderer.setFade(fade);
        }
    }

    private float mW, mH;
    private Touchable button;
    private long mFadeStartedTime;
    private boolean mFadeStarted;

}
