package com.dicetcg.xvnm.dicetcg;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by xvnm on 8/15/17.
 */

public class GLRenderer implements GLSurfaceView.Renderer {

    private int mProgram;
    private int mScreenWidth;
    private int mScreenHeight;
    private int mVBO;
    private int mVertexAttrib;
    private LinkedList<Renderable> mRenderables;
    private int mMatLocation;
    private float[]mOrtho = new float[] {
            1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f,
    };
    private ArrayList<Integer> mTextureIDs;
    private Resources mResources;
    private String mPackageName;

    public GLRenderer(Context context) {
        mResources = context.getResources();
        mRenderables = new LinkedList<Renderable>();
        mPackageName = context.getPackageName();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mScreenWidth = width;
        mScreenHeight = height;
        ortho(0.0f, width, 0.0f, height);
    }

    private void ortho(float left, float right, float bottom, float top) {
        Matrix.setIdentityM(mOrtho, 0);
        Matrix.orthoM(mOrtho, 0, left, right, bottom, top, 0.0f, 1.0f);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        InputStream vertInput = mResources.openRawResource(R.raw.vert);
        InputStream fragInput = mResources.openRawResource(R.raw.frag);
        mProgram = linkProgram(
                loadShader(GLES20.GL_VERTEX_SHADER, vertInput),
                loadShader(GLES20.GL_FRAGMENT_SHADER, fragInput)
        );
        GLES20.glUseProgram(mProgram);
        mVertexAttrib = GLES20.glGetAttribLocation(mProgram, "Vertex");
        mMatLocation = GLES20.glGetUniformLocation(mProgram, "M");
        mVBO = createVertexArray();

        // int testTex = loadTexture();

        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
        GLES20.glClearDepthf(1.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVBO);
        GLES20.glEnableVertexAttribArray(mVertexAttrib);
        GLES20.glVertexAttribPointer(mVertexAttrib, 2, GLES20.GL_FLOAT, false, 4 * 2, 0);

        float[] mat = new float[16];
        for (Renderable r : mRenderables) {
            r.prerender(this);
            r.render(this);
        }

        GLES20.glDisableVertexAttribArray(mVertexAttrib);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }

    private int loadShader(int type, InputStream file) {
        int shader = GLES20.glCreateShader(type);
        String source = new String();
        try {
            int len = file.available();
            byte[]str = new byte[len];
            file.read(str);
            source = new String(str);
        } catch (IOException e) {
            e.printStackTrace();
            GLES20.glDeleteShader(shader);
            Log.e("shader", "error loading shader");
        }
        GLES20.glShaderSource(shader, source);
        GLES20.glCompileShader(shader);

        int[]status = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, IntBuffer.wrap(status));
        if (status[0] == GLES20.GL_FALSE) {
            String err = GLES20.glGetShaderInfoLog(shader);
            Log.e("shader", err);
        }

        return shader;
    }

    private int linkProgram(int vert, int frag) {
        int program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vert);
        GLES20.glAttachShader(program, frag);
        GLES20.glLinkProgram(program);

        int[]status = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, IntBuffer.wrap(status));
        if (status[0] == GLES20.GL_FALSE) {
            String err = GLES20.glGetProgramInfoLog(program);
            Log.e("shader", err);
        }

        GLES20.glDetachShader(program, vert);
        GLES20.glDetachShader(program, frag);
        GLES20.glDeleteShader(vert);
        GLES20.glDeleteShader(frag);

        return program;
    }

    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }

    private int createVertexArray() {
        int[]arr = new int[1];
        GLES20.glGenBuffers(1, IntBuffer.wrap(arr));
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, arr[0]);
        float[]vertices = new float[] {
                0.0f, 0.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
        };
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, vertices.length * 4, FloatBuffer.wrap(vertices), GLES20.GL_STATIC_DRAW);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        return arr[0];
    }

    public void registerRenderable(List<Renderable> renderables) {
        mRenderables.addAll(renderables);
    }

    public void registerRenderable(Renderable renderable) {
        mRenderables.add(renderable);
    }

    public float[] getOrtho() { return mOrtho; }

    public int getMatrixLocation() {
        return mMatLocation;
    }

    /*
    private int loadTexture(String textureName) {
        int resId = mResources.getIdentifier(textureName, "drawable", mPackageName);
        if (resId == 0) {
            Log.e("texture", "failed to open texture \"" + textureName + "\"");
            return 0;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        // Bitmap bitmap = BitmapFactory.decodeResource(mResources, resId,)
    }
    */

}
