package com.dicetcg.xvnm.dicetcg.render;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;

import com.dicetcg.xvnm.dicetcg.R;

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

    private int mScreenWidth, mScreenHeight;
    private int mVBO;
    private LinkedList<Renderable> mRenderables;
    private LinkedList<Renderable> mRenderablesQueue;
    private float[]mOrtho = new float[] {
            1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f,
    };
    private LinkedList<String> mTextureNames;
    private ArrayList<Integer> mTextureIDs;
    private ArrayList<Shader> mPrograms;
    private Resources mResources;
    private String mPackageName;

    public GLRenderer(Context context) {
        mResources = context.getResources();
        mRenderables = new LinkedList<>();
        mRenderablesQueue = new LinkedList<>();
        mPackageName = context.getPackageName();
        // mTextureNames TODO
        mPrograms = new ArrayList<>();
        mTextureIDs = new ArrayList<>();
        mFade = 1.0f;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mScreenWidth = width;
        mScreenHeight = height;
        ortho(0.0f, width, 0.0f, height);
    }

    private void ortho(float left, float right, float bottom, float top) {
        Matrix.setIdentityM(mOrtho, 0);
        Matrix.orthoM(mOrtho, 0, left, right, bottom, top, -2.0f, 2.0f);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mPrograms.clear();
        mPrograms.add(new TextureShader(loadProgram(R.raw.texv, R.raw.texf)));
        mPrograms.add(new ColorShader(loadProgram(R.raw.colorv, R.raw.colorf)));

        mVBO = createVertexArray();

        mTextureIDs.add(loadTexture("test"));

        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
        GLES20.glClearDepthf(1.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVBO);

        if (mRenderablesQueue.size() > 0) {
            for (Renderable r : mRenderablesQueue)
                r.init(this);
            mRenderables.addAll(mRenderablesQueue);
            mRenderablesQueue.clear();
        }

        for (Renderable r : mRenderables) {
            r.prerender(this);
            r.render(this);
        }

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

    private int loadProgram(int vert, int frag) {
        InputStream vertInput = mResources.openRawResource(vert);
        InputStream fragInput = mResources.openRawResource(frag);
        return linkProgram(
                loadShader(GLES20.GL_VERTEX_SHADER, vertInput),
                loadShader(GLES20.GL_FRAGMENT_SHADER, fragInput)
        );
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
        mRenderablesQueue.addAll(renderables);
    }

    public void registerRenderable(Renderable renderable) {
        mRenderablesQueue.add(renderable);
    }

    public float[] getOrtho() { return mOrtho; }

    private int loadTexture(String textureName) {
        int resId = mResources.getIdentifier(textureName, "drawable", mPackageName);
        if (resId == 0) {
            Log.e("texture", "failed to open texture \"" + textureName + "\"");
            return 0;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmap = BitmapFactory.decodeResource(mResources, resId, options);

        int[]tex = new int[1];
        GLES20.glGenTextures(1, IntBuffer.wrap(tex));

        if (tex[0] != 0) {
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex[0]);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
            bitmap.recycle();
        } else {
            Log.e("texture", "error loading image \'" + textureName + "\'");
        }
        return tex[0];
    }

    public ColorShader getColorProgram() {
        return (ColorShader)mPrograms.get(1);
    }

    public TextureShader getTextureProgram() {
        return (TextureShader)mPrograms.get(0);
    }

    public int getTexture(int index) {
        return mTextureIDs.get(index);
    }

    public void clearRenderables() {
        mRenderables.clear();
        mRenderablesQueue.clear();
    }

    private float mFade;

    public float getFade() {
        return mFade;
    }

    public void setFade(float fade) {
        mFade = fade;
    }

}
