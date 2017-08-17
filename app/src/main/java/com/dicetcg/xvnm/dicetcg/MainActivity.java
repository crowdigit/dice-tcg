package com.dicetcg.xvnm.dicetcg;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.GLView;
import com.dicetcg.xvnm.dicetcg.xvnm_implements.MainUI;
import com.dicetcg.xvnm.dicetcg.xvnm_implements.UI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private GLView mGLView;

    CardDBHandler cardDB;
    private UI mCurrentUI;
    private ArrayList<UI> mUIs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new GLView(this);
        mGLView.setOnTouchListener(this);
        setContentView(mGLView);

        mUIs = new ArrayList<>();
        mUIs.add(new MainUI());
        mCurrentUI = mUIs.get(0);
        mCurrentUI.start(getRenderer());

        cardDB = new CardDBHandler(this, "card.db", null, 1);
        // AddCard();
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mCurrentUI.onTouch(event);
    }
}
