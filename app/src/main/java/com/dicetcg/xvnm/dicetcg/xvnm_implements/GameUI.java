package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import android.view.MotionEvent;

import com.dicetcg.xvnm.dicetcg.MainActivity;
import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.Renderable;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by xvnm on 8/17/17.
 */

public class GameUI extends Renderable implements UI {

    public GameUI(MainActivity activity) {
        mActivity = activity;
    }

    @Override
    public void start() {
        mActivity.getRenderer().clearRenderables();
        mActivity.getRenderer().registerRenderable(this);
        renderTexture(false);

        mFader = new Fader();
        mFader.start(1000, false);
    }

    @Override
    public void init(GLRenderer renderer) {
        mW = renderer.getScreenWidth();
        mH = renderer.getScreenHeight();
    }

    @Override
    public boolean onTouch(MotionEvent event) {
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
        return 1.0f;
    }

    @Override
    public void render(GLRenderer renderer) {
        super.render(renderer);
        if (mFader != null) {
            mFader.render(renderer);
            if (!mFader.isActive())
                mFader = null;
        }
    }

    private MainActivity mActivity;
    private int mW, mH;
    private Fader mFader;
}
