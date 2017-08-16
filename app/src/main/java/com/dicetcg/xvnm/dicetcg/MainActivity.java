package com.dicetcg.xvnm.dicetcg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GLView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new GLView(this);
        setContentView(mGLView);

        Renderable test = new TestShape(0.0f, 0.0f, 0.0f, 100.0f, 100.0f);
        getRenderer().registerRenderable(test);
    }

    public GLRenderer getRenderer() {
        return mGLView.getRenderer();
    }

}
