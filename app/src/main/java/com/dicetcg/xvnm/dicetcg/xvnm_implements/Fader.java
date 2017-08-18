package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.Renderable;

/**
 * Created by xvnm on 8/18/17.
 */

public class Fader extends Renderable {

    public Fader() {
        renderTexture(false);
        mIsActive = false;
    }

    public void start(long duration, boolean fadeOut) {
        mStartedTime = System.currentTimeMillis();
        mDuration = duration;
        mFadeOut = fadeOut;
        mIsActive = true;
    }

    @Override
    public void render(GLRenderer renderer) {
        if (!mIsActive)
            return;

        float fade = System.currentTimeMillis() - mStartedTime;
        if (fade > mDuration)
            mIsActive = false;

        if (mFadeOut) {
            fade = (mDuration - fade) / mDuration;
            renderer.setFade(Math.max(fade, 0.0f));
        } else {
            fade = fade / mDuration;
            renderer.setFade(Math.min(fade, 1.0f));
        }
    }

    public boolean isActive() {
        return mIsActive;
    }

    private long mStartedTime, mDuration;
    private boolean mIsActive, mFadeOut;

}
