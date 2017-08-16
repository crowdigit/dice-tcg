package com.dicetcg.xvnm.dicetcg;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.FloatBuffer;

/**
 * Created by xvnm on 8/15/17.
 */

public abstract class Renderable {

    abstract public int getTextureID();
    abstract public float getX();
    abstract public float getY();
    abstract public float getZ();
    abstract public float getW();
    abstract public float getH();

    private int mTexID;

    public int getTexture() {
        return mTexID;
    }

    public void setTexture(int tex) {
        mTexID = tex;
    }

    void prerender(GLRenderer renderer) {
    }

    final void render(GLRenderer renderer) {
        float[]mat = new float[16];
        Matrix.setIdentityM(mat, 0);
        Matrix.translateM(mat, 0, getX(), getY(), getZ());
        Matrix.scaleM(mat, 0, getW(), getH(), 1.0f);
        Matrix.multiplyMM(mat, 0, renderer.getOrtho(), 0, mat, 0);
        GLES20.glUniformMatrix4fv(renderer.getMatrixLocation(), 1, false, FloatBuffer.wrap(mat));
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);
    }

}