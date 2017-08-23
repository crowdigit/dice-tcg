package com.dicetcg.xvnm.dicetcg.render;

import android.opengl.GLES20;
import android.opengl.Matrix;

/**
 * Created by xvnm on 8/15/17.
 */

public class Renderable {

    public float getX() {
        return 0;
    }

    public float getY() {
        return 0;
    }

    public float getZ() {
        return 0;
    }

    public float getW() {
        return 0;
    }

    public float getH() { return 0; }

    public float getR() {
        return 0;
    }

    public float getG() {
        return 0;
    }

    public float getB() {
        return 0;
    }

    public float getRotate() { return 0; }

    private int mTexID = -1;
    private boolean mRenderTexture = false;

    public int getTexture() {
        return mTexID;
    }

    public void setTexture(int tex) {
        mTexID = tex;
    }

    public void renderTexture(boolean f) {
        mRenderTexture = f;
    }

    public void init(GLRenderer renderer) { }

    public void render(GLRenderer renderer) {
        Shader s = null;
        if (mRenderTexture) {
            s = renderer.getTextureProgram();
            s.use();
            ((TextureShader)s).bindTexture(getTexture());
        } else {
            s = renderer.getColorProgram();
            s.use();
            ((ColorShader)s).uniformColor(getR(), getG(), getB());
        }

        float[]st = new float[16];
        float[]vm = new float[16];
        Matrix.setIdentityM(st, 0);
        Matrix.setIdentityM(vm, 0);
        Matrix.translateM(st, 0, getX(), getY(), getZ());
        Matrix.translateM(st, 0, getW()/2, getH()/2, 0);
        Matrix.rotateM(st, 0, getRotate(), 0, 0, 1);
        Matrix.translateM(st, 0, -getW()/2, -getH()/2, 0);
        Matrix.scaleM(st, 0, getW(), getH(), 1.0f);
        Matrix.multiplyMM(vm, 0, renderer.getOrtho(), 0, st, 0);
        s.uniformMatrix(vm);
        s.uniformFade(renderer.getFade());

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);

        s.deuse();

        if (mRenderTexture)
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }

}