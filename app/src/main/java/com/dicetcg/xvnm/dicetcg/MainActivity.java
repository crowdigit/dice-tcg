package com.dicetcg.xvnm.dicetcg;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GLView mGLView;
    private GLRenderer mGLRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new GLView(this);
        mGLRenderer = new GLRenderer();
        mGLView.setRenderer(mGLRenderer);
        mGLView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        setContentView(mGLView);
    }
}
