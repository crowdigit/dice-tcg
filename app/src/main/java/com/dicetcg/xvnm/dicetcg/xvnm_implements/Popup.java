package com.dicetcg.xvnm.dicetcg.xvnm_implements;

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

    public void render() {
        while (!done()) {

        }
    }

    public boolean done() {
        if (System.currentTimeMillis() - mStartedTime > mDuration)
            return true;
        return false;
    }

    private long mStartedTime, mDuration;

}
