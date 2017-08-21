package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import com.dicetcg.xvnm.dicetcg.render.Renderable;

/**
 * Created by xvnm on 8/17/17.
 */

public class Card extends Renderable {

    public Card(MetaCard meta, int texID) {
        mMeta = meta;
        renderTexture(true);
        setTexture(texID);
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
        return 0.2f;
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
        return 0.5f;
    }

    public void setX(float x) {
        mX = x;
    }

    public void setY(float y) {
        mY = y;
    }

    public void setW(float w) {
        mW = w;
    }

    public void setH(float h) {
        mH = h;
    }

    private MetaCard mMeta;
    private float mX, mY;
    private float mW, mH;

}