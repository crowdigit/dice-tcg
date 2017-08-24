package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.Renderable;

import java.util.Random;

/**
 * Created by Knock on 2017-08-21.
 */

public class Dice extends Renderable {

    private class RenderNumber extends Renderable {

        public RenderNumber() {
            RenderNumber.this.renderTexture(true);
        }

        @Override
        public void render(GLRenderer renderer) {
            RenderNumber.this.mSide = renderer.getScreenWidth() / 3;
            RenderNumber.this.mX = renderer.getScreenWidth() / 2;
            RenderNumber.this.mY = renderer.getScreenHeight() / 2;
            RenderNumber.this.setTexture(renderer.getTexture(Integer.toString(num)));
            long dt = System.currentTimeMillis() - mStarted - 500;
            if (dt <= 125)
                mSide += RenderNumber.this.mSide / 250 * dt;
            else if (dt <= 250)
                mSide += -RenderNumber.this.mSide / 250 * dt + RenderNumber.this.mSide;
            super.render(renderer);
        }

        @Override
        public float getX() {
            return mX - RenderNumber.this.getW() / 2;
        }

        @Override
        public float getY() {
            return mY - RenderNumber.this.getH() / 2;
        }

        @Override
        public float getZ() {
            return 0.7f;
        }

        @Override
        public float getW() {
            return mSide;
        }

        @Override
        public float getH() {
            return mSide;
        }

        private float mSide;
        private float mX, mY;
    }

    public Dice() {
        num = 0;
        mNumber = new RenderNumber();
        Dice.this.renderTexture(false);
    }

    @Override
    public float getR() {
        return 0.49f;
    }

    public void roll(float sw) {
        num = (int)Math.floor((new Random()).nextFloat() * 6) + 1;
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
        return mW;
    }

    @Override
    public float getH() {
        return mW;
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
        mW = renderer.getScreenWidth() / 5;
        super.render(renderer);
        if (isRolling() == 2)
            mNumber.render(renderer);
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
    private float mW;
    private RenderNumber mNumber;

}