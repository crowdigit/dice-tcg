package com.dicetcg.xvnm.dicetcg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GLView mGLView;

    int PlayerHP = 0;

    int[] PDH = new int[]{0,0,0};     //DiceHP  왼쪽에서 오른쪽 순서로
    int[] PDT = new int[]{0,0,0};     //DiceTop
    int[] PDB = new int[]{0,0,0};     //DiceBottom

    int EnemyHP = 0;

    int[] EDH = new int[]{0,0,0};     //DiceHP
    int[] EDT = new int[]{0,0,0};     //DiceTop
    int[] EDB = new int[]{0,0,0};     //DiceBottom

    int diceNum = 0;
    int gameOverFlag = 0;
    int playerTimeFlag = 0;

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
