package com.dicetcg.xvnm.dicetcg.render;

import android.opengl.GLES20;
import android.opengl.Matrix;

/**
 * Created by xvnm on 8/15/17.
 */

public abstract class Renderable {

    abstract public float getX();
    abstract public float getY();
    abstract public float getZ();

    abstract public float getW();
    abstract public float getH();

    public float getR() {
        return 0;
    }

    public float getG() {
        return 0;
    }

    public float getB() {
        return 0;
    }

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

    public void prerender(GLRenderer renderer) { }

    public void init(GLRenderer renderer) { }

    public void render(GLRenderer renderer) {
        Shader s = null;
        if (mRenderTexture) {
            s = renderer.getTextureProgram();
            s.use();
            ((TextureShader)s).bindTexture(renderer.getTexture(getTexture()));
        } else {
            s = renderer.getColorProgram();
            s.use();
            ((ColorShader)s).uniformColor(getR(), getG(), getB());
        }

        float[]mat = new float[16];
        Matrix.setIdentityM(mat, 0);
        Matrix.translateM(mat, 0, getX(), getY(), getZ());
        Matrix.scaleM(mat, 0, getW(), getH(), 1.0f);
        Matrix.multiplyMM(mat, 0, renderer.getOrtho(), 0, mat, 0);
        s.uniformMatrix(mat);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);

        s.deuse();

        if (mRenderTexture)
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }

}