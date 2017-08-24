package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.Renderable;

/**
 * Created by xvnm on 8/22/17.
 */

public class Popup extends Renderable {

    public Popup(long duration, int texID) {
        renderTexture(true);
        setTexture(texID);
        mStartedTime = System.currentTimeMillis();
        mDuration = duration;
    }

    @Override
    public void render(GLRenderer renderer) {
        mW = renderer.getScreenWidth();
        mX = 0;
        mY = renderer.getScreenHeight() / 2 - mW / 2;
        super.render(renderer);
    }

    @Override
    public float getX() {
        long dt = System.currentTimeMillis() - mStartedTime;
        long hd = mDuration / 2;
        return mW / (hd * hd * hd) * (dt - hd) * (dt - hd) * (dt - hd);
    }

    @Override
    public float getY() {
        return mY;
    }

    @Override
    public float getZ() {
        return 0.6f;
    }

    @Override
    public float getW() {
        return mW;
    }

    @Override
    public float getH() {
        return mW;
    }

    public boolean done() {
        if (System.currentTimeMillis() - mStartedTime > mDuration)
            return true;
        return false;
    }

    private long mStartedTime, mDuration;
    private float mW;
    private float mX, mY;

}
