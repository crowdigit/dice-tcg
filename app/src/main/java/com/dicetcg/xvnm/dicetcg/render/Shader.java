package com.dicetcg.xvnm.dicetcg.render;

import android.opengl.GLES20;

import java.nio.FloatBuffer;

/**
 * Created by dogtrollin on 8/16/17.
 */

abstract public class Shader {

    public Shader(int program) {
        mProgram = program;
        mVertexLocation = GLES20.glGetAttribLocation(mProgram, "Vertex");
        mMatrixLocation = GLES20.glGetUniformLocation(mProgram, "M");
    }

    public void use() {
        GLES20.glUseProgram(mProgram);
        GLES20.glEnableVertexAttribArray(mVertexLocation);
        GLES20.glVertexAttribPointer(mVertexLocation, 2, GLES20.GL_FLOAT, false, 4 * 2, 0);
    }

    public void deuse() {
        GLES20.glDisableVertexAttribArray(mVertexLocation);
    }

    public void uniformMatrix(float[]mat) {
        GLES20.glUniformMatrix4fv(mMatrixLocation, 1, false, FloatBuffer.wrap(mat));
    }

    public int getProgram() {
        return mProgram;
    }

    private int mProgram;
    private int mVertexLocation;
    private int mMatrixLocation;
}
