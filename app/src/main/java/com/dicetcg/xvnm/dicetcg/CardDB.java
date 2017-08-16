package com.dicetcg.xvnm.dicetcg;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Knock on 2017-08-15.
 */

public class CardDB extends SQLiteOpenHelper {

    public CardDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table;
        create_table = "create table if not exists card(CardName text primary key, HP integer, DiceTop integer, DiceBottom integer)";
        String input_data;
        sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists card");
        onCreate(sqLiteDatabase);
    }

    public class Card {
        private int HP;
        private int DiceTop;
        private int DiceBottom;
        private String name;

        public Card() {
        }

        public Card(int HP, int DiceTop, int DiceBottom, String name) {
            this.HP = HP;
            this.DiceTop = DiceTop;
            this.DiceBottom = DiceBottom;
            this.name = name;
        }

        public int getHP() {
            return HP;
        }

        public void setHP(int HP) {
            this.HP = HP;
        }

        public int getDiceTop() {
            return DiceTop;
        }

        public void setDiceTop(int diceTop) {
            DiceTop = diceTop;
        }

        public int getDiceBottom() {
            return DiceBottom;
        }

        public void setDiceBottom(int diceBottom) {
            DiceBottom = diceBottom;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

        public void addCard(Card Card){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues example = new ContentValues();
            example.put("이름", Card.getName());
            example.put("0", Card.getHP());
            example.put("0", Card.getDiceTop());
            example.put("0", Card.getDiceBottom());
            db.insert("card", null, example);

            ContentValues amute = new ContentValues();
            amute.put("암무트", Card.getName());
            amute.put("2100", Card.getHP());
            amute.put("2000", Card.getDiceTop());
            amute.put("1700", Card.getDiceBottom());
            db.insert("card", null, amute);

            ContentValues bigfoot = new ContentValues();
            bigfoot.put("빅풋", Card.getName());
            bigfoot.put("2000", Card.getHP());
            bigfoot.put("2300", Card.getDiceTop());
            bigfoot.put("1400", Card.getDiceBottom());
            db.insert("card", null, bigfoot);

            ContentValues bloodknight = new ContentValues();
            bloodknight.put("피빛의기사", Card.getName());
            bloodknight.put("2300", Card.getHP());
            bloodknight.put("2000", Card.getDiceTop());
            bloodknight.put("1500", Card.getDiceBottom());
            db.insert("card", null, bloodknight);

            ContentValues bongun = new ContentValues();
            bongun.put("본건", Card.getName());
            bongun.put("2100", Card.getHP());
            bongun.put("2100", Card.getDiceTop());
            bongun.put("1000", Card.getDiceBottom());
            db.insert("card", null, bongun);

            ContentValues chung = new ContentValues();
            chung.put("청이", Card.getName());
            chung.put("2400", Card.getHP());
            chung.put("2300", Card.getDiceTop());
            chung.put("1900", Card.getDiceBottom());
            db.insert("card", null, chung);

            ContentValues cinoby = new ContentValues();
            cinoby.put("시노비", Card.getName());
            cinoby.put("1900", Card.getHP());
            cinoby.put("2000", Card.getDiceTop());
            cinoby.put("1700", Card.getDiceBottom());
            db.insert("card", null, cinoby);

            ContentValues clock = new ContentValues();
            clock.put("클락", Card.getName());
            clock.put("1900", Card.getHP());
            clock.put("2100", Card.getDiceTop());
            clock.put("1700", Card.getDiceBottom());
            db.insert("card", null, clock);

            ContentValues munak = new ContentValues();
            munak.put("무낙", Card.getName());
            munak.put("1600", Card.getHP());
            munak.put("1200", Card.getDiceTop());
            munak.put("900", Card.getDiceBottom());
            db.insert("card", null, munak);

            ContentValues darkpriest = new ContentValues();
            darkpriest.put("다크프리스트", Card.getName());
            darkpriest.put("2100", Card.getHP());
            darkpriest.put("2000", Card.getDiceTop());
            darkpriest.put("1700", Card.getDiceBottom());
            db.insert("card", null, darkpriest);

            ContentValues poporing = new ContentValues();
            poporing.put("포포링", Card.getName());
            poporing.put("1600", Card.getHP());
            poporing.put("2100", Card.getDiceTop());
            poporing.put("1200", Card.getDiceBottom());
            db.insert("card", null, clock);

            ContentValues undead = new ContentValues();
            undead.put("배회하는자", Card.getName());
            undead.put("1900", Card.getHP());
            undead.put("2600", Card.getDiceTop());
            undead.put("1300", Card.getDiceBottom());
            db.insert("card", null, undead);

            db.close();

    }

}
