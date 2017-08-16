package com.dicetcg.xvnm.dicetcg;

import com.dicetcg.xvnm.dicetcg.Renderable;

public class TestShape extends Renderable {

    private String mName;
    private int mAttc;
    private float mX;
    private float mW;
    private float mH;

    TestShape(String name, int attc, float x) {
        mName = name;
        mAttc = attc;
        mX = x;
        mW = 1.0f;
    }

    public int getTextureID() {
        return 0;
    }

    public float getX() {
        return mX;
    }

    public float getY() {
        return 0;
    }

    public float getZ() {
        return 0;
    }

    public float getW() {
        return mW;
    }

    public float getH() {
        return mH;
    }

    @Override
    void prerender(GLRenderer renderer) {
        float w = renderer.getScreenWidth();
        float h = renderer.getScreenHeight();
        float ratio = w/h;
        mW = ratio * 300;
        mH = ratio * 400;
    }
}