package com.dicetcg.xvnm.dicetcg;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.Renderable;

import java.util.Random;

/**
 * Created by dogtrollin on 8/17/17.
 */

public class TestShape2 extends Renderable {

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
        return 0.5f;
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
    public void init(GLRenderer renderer) {
        mW = renderer.getScreenWidth() / 5 * 3;
        mH = renderer.getScreenHeight() / 5 * 3;
        mX = renderer.getScreenWidth() / 2 - mW / 2;
        mY = renderer.getScreenHeight() / 2 - mH / 2;
    }

    @Override
    public void render(GLRenderer renderer) {
        Random random = new Random();
        mX = random.nextFloat() * renderer.getScreenWidth();
        mY = random.nextFloat() * renderer.getScreenHeight();
        super.render(renderer);
    }

    private float mW, mH;
    private float mX, mY;
}
