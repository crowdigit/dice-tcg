package com.dicetcg.xvnm.dicetcg;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

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

    CardDBHandler cardDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new GLView(this);
        setContentView(mGLView);
        //Renderable test = new TestShape(0.0f, 0.0f, 0.0f, 100.0f, 100.0f);
        //getRenderer().registerRenderable(test);

        cardDB = new CardDBHandler(this, "card.db", null, 1);
        AddCard();

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

    public void AddCard(){

        int HP;
        int DiceTop;
        int DiceBottom;
        int DiceMax;
        String name;
        String insert_data;
        SQLiteDatabase db;

        name = "암무트"; HP = 2100; DiceTop = 2000; DiceBottom = 1700; DiceMax = 5;
        insert_data = "insert into card values('" + name + "'," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db = cardDB.getWritableDatabase();
        db.execSQL(insert_data);

        name = "빅풋"; HP = 2000; DiceTop = 2300; DiceBottom = 1400; DiceMax = 3;
        insert_data = "insert into card values('" + name + "'," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db = cardDB.getWritableDatabase();
        db.execSQL(insert_data);

        name = "피빛의기사"; HP = 2300; DiceTop = 2000; DiceBottom = 1500; DiceMax = 3;
        insert_data = "insert into card values('" + name + "'," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db = cardDB.getWritableDatabase();
        db.execSQL(insert_data);

        name = "본건"; HP = 2100; DiceTop = 2100; DiceBottom = 1000; DiceMax = 2;
        insert_data = "insert into card values('" + name + "'," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db = cardDB.getWritableDatabase();
        db.execSQL(insert_data);

        name = "청이"; HP = 2400; DiceTop = 2300; DiceBottom = 1900; DiceMax = 4;
        insert_data = "insert into card values('" + name + "'," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db = cardDB.getWritableDatabase();
        db.execSQL(insert_data);

        db.close();
        Toast.makeText(this, "데이터 저장 완료", Toast.LENGTH_SHORT).show();
    }

}
