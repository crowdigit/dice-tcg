package com.dicetcg.xvnm.dicetcg;

import java.util.Random;

public class TestShape extends Renderable {

    private float mR, mG, mB;
    private float mW, mH;

    TestShape() {
        mR = 0.0f;
        mG = 0.0f;
        mB = 0.0f;
        mW = 1.0f;
        mH = 1.0f;
    }

    public int getTextureID() {
        return 0;
    }

    public float getX() { return 0.0f; }

    public float getY() { return 0.0f; }

    public float getZ() { return 0.0f; }

    public float getW() { return mW; }

    public float getH() { return mH; }

    public float getR() { return mR; }

    public float getG() { return mG; }

    public float getB() { return mB; }

    @Override
    public void prerender(GLRenderer renderer) {
       /* Random random = new Random();
        mR = random.nextFloat();
        mG = random.nextFloat();
        mB = random.nextFloat();
        mW = renderer.getScreenWidth();
        mH = renderer.getScreenHeight(); */
    }
}