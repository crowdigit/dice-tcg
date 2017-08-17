package com.dicetcg.xvnm.dicetcg;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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

    CardDBHandler myCardDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new GLView(this);
        setContentView(mGLView);

        myCardDB = new CardDBHandler(this, "myCardDB.db", null, 1);
        AddCard();           //나중에 DB 불러왔을 때 DB가 비었다면 그 때만 실행하는 방향으로

        if (android.os.Build.VERSION.SDK_INT >= 11)
            mGLView.setPreserveEGLContextOnPause(true);

        Renderable PlayerCard01 = new CardBlank(50,100);
        Renderable PlayerCard02 = new CardBlank(300,100);
        Renderable PlayerCard03 = new CardBlank(550,100);

        getRenderer().registerRenderable(PlayerCard01);
        getRenderer().registerRenderable(PlayerCard02);
        getRenderer().registerRenderable(PlayerCard03);

        myCardDB = new CardDBHandler(this, "myCardDB.db", null, 1);
        AddCard();
    }

    public GLRenderer getRenderer() {
        return mGLView.getRenderer();
    }

    public void AddCard(){

        int HP;
        int AC;
        int SC;         // Summon Cost
        int MC;         // Maintain Cost
        int DiceTop;
        int DiceBottom;
        int DiceMax;

        String name;
        String insert_data;

        SQLiteDatabase db = myCardDB.getWritableDatabase();;

        name = "amute"; HP = 2100; AC = 0; SC = 6; MC = 2; DiceTop = 2000; DiceBottom = 1700; DiceMax = 5;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "bigfoot"; HP = 2000; AC = 0; SC = 4; MC = 4; DiceTop = 2300; DiceBottom = 1400; DiceMax = 3;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "bloodknight"; HP = 2300; AC = 400; SC = 6; MC = 4; DiceTop = 2000; DiceBottom = 1500; DiceMax = 3;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "bongun"; HP = 2100; AC = 100; SC = 4; MC = 4; DiceTop = 2100; DiceBottom = 1000; DiceMax = 2;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "chung"; HP = 2400; AC = 200; SC = 6; MC = 4; DiceTop = 2300; DiceBottom = 1900; DiceMax = 4;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        db.close();
        Toast.makeText(this, "데이터 저장 완료", Toast.LENGTH_SHORT).show();
    }

}
