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

        Renderable test1 = new TestShape2();
        test1.renderTexture(true);
        test1.setTexture(0);

        Renderable test2 = new TestShape();
        test2.renderTexture(false);

        getRenderer().registerRenderable(test1);
        getRenderer().registerRenderable(test2);

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

        name = "cinoby"; HP = 1900; AC = 200; SC = 5; MC = 2; DiceTop = 1600; DiceBottom = 1200; DiceMax = 4;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "clock"; HP = 1900; AC = 500; SC = 6; MC = 4; DiceTop = 2100; DiceBottom = 1700; DiceMax = 3;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "darkframe"; HP = 2400; AC = 200; SC = 5; MC = 3; DiceTop = 1800; DiceBottom = 1400; DiceMax = 4;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "darkill"; HP = 2500; AC = 100; SC = 7; MC = 4; DiceTop = 2400; DiceBottom = 1500; DiceMax = 3;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "darkpriest"; HP = 2100; AC = 200; SC = 5; MC = 3; DiceTop = 2000; DiceBottom = 1700; DiceMax = 4;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "haty"; HP = 2400; AC = 200; SC = 7; MC = 3; DiceTop = 2800; DiceBottom = 1600; DiceMax = 4;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "honet"; HP = 900; AC = 0; SC = 3; MC = 0; DiceTop = 1700; DiceBottom = 700; DiceMax = 4;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "horong"; HP = 600; AC = 0; SC = 1; MC = 0; DiceTop = 1200; DiceBottom = 200; DiceMax = 3;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "marin"; HP = 1200; AC = 0; SC = 2; MC = 1; DiceTop = 1100; DiceBottom = 900; DiceMax = 4;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "mimic"; HP = 700; AC = 300; SC = 1; MC = 0; DiceTop = 600; DiceBottom = 400; DiceMax = 4;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "muka"; HP = 1000; AC = 100; SC = 1; MC = 0; DiceTop = 700; DiceBottom = 200; DiceMax = 3;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "munak"; HP = 1600; AC = 200; SC = 3; MC = 1; DiceTop = 1200; DiceBottom = 900; DiceMax = 5;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "owlduke"; HP = 1200; AC = 0; SC = 6; MC = 1; DiceTop = 2400; DiceBottom = 1000; DiceMax = 2;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "permiler"; HP = 1100; AC = 0; SC = 4; MC = 1; DiceTop = 1800; DiceBottom = 1100; DiceMax = 3;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "poporing"; HP = 1600; AC = 0; SC = 4; MC = 2; DiceTop = 2100; DiceBottom = 1200; DiceMax = 4;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "poring"; HP = 600; AC = 0; SC = 1; MC = 0; DiceTop = 800; DiceBottom = 400; DiceMax = 2;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "pupa"; HP = 800; AC = 200; SC = 1; MC = 0; DiceTop = 600; DiceBottom = 300; DiceMax = 3;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "raflecia"; HP = 1600; AC = 300; SC = 6; MC = 4; DiceTop = 1300; DiceBottom = 1100; DiceMax = 3;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "rocker"; HP = 1300; AC = 0; SC = 4; MC = 1; DiceTop = 1600; DiceBottom = 800; DiceMax = 2;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "rodafrog"; HP = 1100; AC = 0; SC = 2; MC = 0; DiceTop = 800; DiceBottom = 500; DiceMax = 3;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "sohyei"; HP = 1800; AC = 0; SC = 4; MC = 2; DiceTop = 1800; DiceBottom = 1400; DiceMax = 4;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "spoar"; HP = 1500; AC = 0; SC = 3; MC = 1; DiceTop = 1100; DiceBottom = 900; DiceMax = 3;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "undead"; HP = 1900; AC = 0; SC = 5; MC = 3; DiceTop = 2600; DiceBottom = 1300; DiceMax = 3;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "willow"; HP = 1900; AC = 0; SC = 4; MC = 2; DiceTop = 1900; DiceBottom = 1100; DiceMax = 4;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        name = "wormtail"; HP = 1100; AC = 0; SC = 2; MC = 0; DiceTop = 1000; DiceBottom = 100; DiceMax = 2;
        insert_data = "insert into myCardDB values('" + name + "'," + AC + "," + SC + "," + MC + "," + HP + "," + DiceTop + "," + DiceBottom + "," + DiceMax + ");";
        db.execSQL(insert_data);

        db.close();
        Toast.makeText(this, "데이터 저장 완료", Toast.LENGTH_SHORT).show();
    }

}
