package com.dicetcg.xvnm.dicetcg;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Knock on 2017-08-15.
 */

public class CardDBHandler extends SQLiteOpenHelper {

    public CardDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table;
        create_table = "create table if not exists myCardDB(CardName text, HP integer, AC integer, SC integer, MC integer, DiceTop integer, DiceBottom integer, DiceMax integer);";
        sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS myCardDB");
        onCreate(sqLiteDatabase);
    }

}
