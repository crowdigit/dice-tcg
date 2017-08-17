package com.dicetcg.xvnm.dicetcg.render;

import android.opengl.GLES20;

/**
 * Created by dogtrollin on 8/17/17.
 */

public class ColorShader extends Shader {

    public ColorShader(int program) {
        super(program);
        mColorLocation = GLES20.glGetUniformLocation(getProgram(), "color");
    }

    public void uniformColor(float r, float g, float b) {
        GLES20.glUniform3f(mColorLocation, r, g, b);
    }

    private int mColorLocation;

}
