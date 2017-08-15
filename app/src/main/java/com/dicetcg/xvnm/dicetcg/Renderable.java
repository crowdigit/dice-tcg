package com.dicetcg.xvnm.dicetcg;

/**
 * Created by xvnm on 8/15/17.
 */

public class Renderable {

    Renderable(String textureID, int x, int y, int w, int h) {
        mTextureID = textureID;
        mX = x;
        mY = y;
        mW = w;
        mH = h;
    }

    void render() {
        // TODO
    }

    public String getTextureID() {
        return mTextureID;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public int getW() {
        return mW;
    }

    public int getH() {
        return mH;
    }

    public void setX(int x) {
        mX = x;
    }

    public void setY(int y) {
        mY = y;
    }

    public void setW(int w) {
        mW = w;
    }

    public void setH(int h) {
        mH = h;
    }

    private String mTextureID;
    private int mX;
    private int mY;
    private int mW;
    private int mH;

}
