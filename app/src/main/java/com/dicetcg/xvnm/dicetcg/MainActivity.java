package com.dicetcg.xvnm.dicetcg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GLView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new GLView(this);
        setContentView(mGLView);

        if (android.os.Build.VERSION.SDK_INT >= 11)
            mGLView.setPreserveEGLContextOnPause(true);

        Renderable card = new TestShape("asdf", 100, 0.0f);
        TestShape carbB = new TestShape("aszxczxv", 200, 200.0f);

        getRenderer().registerRenderable(card);
        // getRenderer().registerRenderable(carbB);
    }

    public GLRenderer getRenderer() {
        return mGLView.getRenderer();
    }

}
