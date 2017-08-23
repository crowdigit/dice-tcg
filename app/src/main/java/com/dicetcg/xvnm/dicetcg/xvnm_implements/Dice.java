package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.Renderable;

import java.util.Random;

/**
 * Created by Knock on 2017-08-21.
 */

public class Dice extends Renderable {

    public Dice() {
        num = 0;
    }

    public void roll(float sw) {
        num = (new Random()).nextInt(5) + 1;
        mStarted = System.currentTimeMillis();
        mScreenWidth = sw;
    }

    public int getNumber() {
        return num;
    }

    @Override
    public float getX() {
        if (isRolling() == 0)
            return -getW();
        if (isRolling() == 1) {
            long dt = System.currentTimeMillis() - mStarted;
            mR = -mScreenWidth / (2 * 500 * 500) * (dt - 500) * (dt - 500) + mScreenWidth / 2 - getW() / 2;
            return mR;
        }
        return mScreenWidth / 2 - getW() / 2;
    }

    @Override
    public float getRotate() {
        return isRolling() != 0 ? mR : 0;
    }

    @Override
    public float getW() {
        return 150;
    }

    @Override
    public float getH() {
        return 150;
    }

    @Override
    public float getY() {
        return mY - getH() / 2;
    }

    @Override
    public float getZ() {
        return 0.5f;
    }

    @Override
    public void render(GLRenderer renderer) {
        mY = renderer.getScreenHeight() / 2;
        super.render(renderer);
    }

    public int isRolling() {
        long dt = System.currentTimeMillis() - mStarted;
        if (dt <= 500)
            return 1;
        else if (dt <= 1000)
            return 2;
        return 0;
    }

    private int num;
    private long mStarted;
    private float mScreenWidth;
    private float mY;
    private float mR;

}