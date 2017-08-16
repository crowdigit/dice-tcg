package com.dicetcg.xvnm.dicetcg;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private GLView mGLView;
    private GLRenderer mGLRenderer;

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
        mGLRenderer = new GLRenderer();
        mGLView.setRenderer(mGLRenderer);
        mGLView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        setContentView(mGLView);
        Turn();
    }

    public void runDice(View v){
        diceNum = (int)Math.random() * 6 + 1;
        switch (diceNum){
            case 1 :
                break;
            case 2 :
                break;
            case 3 :
                break;
            case 4 :
                break;
            case 5 :
                break;
            case 6 :
                break;
        }
    }

    public void Turn(){
        if (playerTimeFlag != 0) {

            if (gameOverFlag != 0){
                Turn();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        else if (event.getAction() == MotionEvent.ACTION_MOVE) {

        }
        else if (event.getAction() == MotionEvent.ACTION_UP) {

        }
        return false;
    }

}
