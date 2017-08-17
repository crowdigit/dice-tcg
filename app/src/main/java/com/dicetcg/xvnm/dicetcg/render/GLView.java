package com.dicetcg.xvnm.dicetcg.render;

import android.content.Context;
import android.opengl.GLSurfaceView;


/**
 * Created by xvnm on 8/15/17.
 */

public class GLView extends GLSurfaceView {

    private GLRenderer mGLRenderer;

    public GLView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);
        mGLRenderer = new GLRenderer(getContext());
        setRenderer(mGLRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    public GLRenderer getRenderer() {
        return mGLRenderer;
    }

}
