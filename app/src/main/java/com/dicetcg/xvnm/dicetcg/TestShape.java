package com.dicetcg.xvnm.dicetcg;

/**
 * Created by xvnm on 8/16/17.
 */

public class TestShape extends Renderable {

    private float mX, mY, mZ, mW, mH;

    TestShape(float x, float y, float z, float w, float h) {
        mX = x;
        mY = y;
        mZ = z;
        mW = w;
        mH = h;
    }

    @Override
    public String getTextureID() {
        return "";
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
        return mZ;
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
    public void prerender(GLRenderer renderer) {
        mX += 0.3;
    }

}
