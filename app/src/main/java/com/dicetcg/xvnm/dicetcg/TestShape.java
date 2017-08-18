package com.dicetcg.xvnm.dicetcg;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.Renderable;

import java.util.Random;

public class TestShape extends Renderable {

    private float mR, mG, mB;
    private float mW, mH;

    public float getW() {
        return mW;
    }

    public float getH() {
        return mH;
    }

    public float getR() {
        return mR;
    }

    public float getG() {
        return mG;
    }

    public float getB() {
        return mB;
    }

    @Override
    public void init(GLRenderer renderer) {
        mW = renderer.getScreenWidth();
        mH = renderer.getScreenHeight();
    }

    @Override
    public void render(GLRenderer renderer) {
        Random random = new Random();
        mR = random.nextFloat();
        mG = random.nextFloat();
        mB = random.nextFloat();
        mW = renderer.getScreenWidth();
        mH = renderer.getScreenHeight();
        super.render(renderer);
    }

}