package com.dicetcg.xvnm.dicetcg;

import java.util.Random;

/**
 * Created by Knock on 2017-08-16.
 */

public class CardBlank extends Renderable{

    private float mR, mG, mB;
    private float mW, mH, W, H;
    private float mX, mY;

    CardBlank(float x, float y) {
        mX = x;        mY = y;
    }

    @Override
    public int getTextureID() {
        return 0;
    }

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
    public float getR() {
        return 1;
    }

    @Override
    public float getG() {
        return 0;
    }

    @Override
    public float getB() {
        return 0;
    }

    @Override
    public void prerender(GLRenderer renderer) {
        mW = renderer.getScreenWidth();
        mH = renderer.getScreenHeight();
    }
}