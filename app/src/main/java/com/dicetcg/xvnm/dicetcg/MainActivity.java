package com.dicetcg.xvnm.dicetcg;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.GLView;
import com.dicetcg.xvnm.dicetcg.render.Renderable;
import com.dicetcg.xvnm.dicetcg.xvnm_implements.GameUI;
import com.dicetcg.xvnm.dicetcg.xvnm_implements.MainUI;
import com.dicetcg.xvnm.dicetcg.xvnm_implements.UI;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private UI mCurrentUI;
    private ArrayList<UI> mUIs;
    private GLView mGLView;

    List<String> myCardName = new ArrayList<String>();
    List<Integer> myCardHP = new ArrayList<Integer>();
    List<Integer> myCardAC = new ArrayList<Integer>();
    List<Integer> myCardSC = new ArrayList<Integer>();
    List<Integer> myCardMC = new ArrayList<Integer>();
    List<Integer> myDiceTop = new ArrayList<Integer>();
    List<Integer> myDiceBottom = new ArrayList<Integer>();
    List<Integer> myDiceMax = new ArrayList<Integer>();

    CardDBHandler myCardDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new GLView(this);
        mGLView.setOnTouchListener(this);
        setContentView(mGLView);

<<<<<<< HEAD
        //Renderable test1 = new TestShape2();
        //test1.renderTexture(true);
        //test1.setTexture(0);

        //Renderable test2 = new TestShape();
        //test2.renderTexture(false);

        //getRenderer().registerRenderable(test1);
        //getRenderer().registerRenderable(test2);
=======
        // setContentView(R.layout.debug); // -- uncommet here to debug
>>>>>>> 95a11a8160427d614ec360341c6f88532cfe607a

        mUIs = new ArrayList<>();
        mUIs.add(new MainUI(this));
        mUIs.add(new GameUI(this));
        mCurrentUI = mUIs.get(0);
        mCurrentUI.start();

        myCardDB = new CardDBHandler(this, "myCardDB.db", null, 1);
        AddCard();           //나중에 DB 불러왔을 때 DB가 비었다면 그 때만 실행하는 방향으로

        if (android.os.Build.VERSION.SDK_INT >= 11)
            mGLView.setPreserveEGLContextOnPause(true);

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

        ReadCard();

        Toast.makeText(this, "데이터 저장 완료", Toast.LENGTH_SHORT).show();
    }


    public void ReadCard(){
        String select_data;
        select_data = "select * from myCardDB;";

        SQLiteDatabase db = myCardDB.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_data, null);

        while(cursor.moveToNext())
        {
            String mName = cursor.getString(cursor.getColumnIndex("CardName"));
            myCardName.add(mName);

            int mHP = cursor.getInt(cursor.getColumnIndex("HP"));
            myCardHP.add(mHP);

            int mAC = cursor.getInt(cursor.getColumnIndex("AC"));
            myCardAC.add(mAC);

            int mSC = cursor.getInt(cursor.getColumnIndex("SC"));
            myCardSC.add(mSC);

            int mMC = cursor.getInt(cursor.getColumnIndex("MC"));
            myCardMC.add(mMC);

            int mDT = cursor.getInt(cursor.getColumnIndex("DiceTop"));
            myDiceTop.add(mDT);

            int mDB = cursor.getInt(cursor.getColumnIndex("DiceBottom"));
            myDiceBottom.add(mDB);

            int mDM = cursor.getInt(cursor.getColumnIndex("DiceMax"));
            myDiceMax.add(mDM);
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mCurrentUI.onTouch(event);
    }

    public void setCurrentUI(int index) {
        mCurrentUI = mUIs.get(index);
        mCurrentUI.start();
    }
}
