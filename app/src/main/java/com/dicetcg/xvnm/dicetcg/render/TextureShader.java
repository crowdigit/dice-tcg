package com.dicetcg.xvnm.dicetcg.render;

import android.opengl.GLES20;

/**
 * Created by dogtrollin on 8/16/17.
 */

public class TextureShader extends Shader {

    public TextureShader(int program) {
        super(program);
        mTextureLocation = GLES20.glGetUniformLocation(getProgram(), "tex");
    }

    public void bindTexture(int tex) {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex);
        GLES20.glUniform1f(mTextureLocation, 0);
    }

    @Override
    public void deuse() {
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        super.deuse();
    }

    private int mTextureLocation;

}
