package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import android.view.MotionEvent;
import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.Renderable;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by xvnm on 8/17/17.
 */

abstract public class Player {

    Player(GameUI.GameUIController controller) {
        mDeck = new Deck(controller.getMetaCards());
        mHand = new LinkedList<>();
        mController = controller;
        mHP = 5200;
    }

    public int getHP() {
        return mHP;
    }

    public void setHP(int hp) {
        mHP = hp;
    }

    protected void draw() {
        for (int i = mHand.size(); i < 5; i++) {
            if (mDeck.getCardCount() > 0) {
                mHand.add(new HandCard(mDeck.draw(), mController.getRenderer()));
            } else {
                setHP(getHP() - 500);
            }
        }
    }

    public int getMana() {
        return mMana;
    }

    public void subMana(int m) {
        mMana -= m;
    }

    public int getManaMax() {
        return mManaMax;
    }

    public void takeTurn(Field.Control field, boolean attack) {
        if (mManaMax < 10)
            mManaMax++;
        mMana = mManaMax;
    }

    abstract public void render(GLRenderer renderer);
    abstract public boolean onTouch(MotionEvent event);

    private int mHP;
    private int mManaMax, mMana;
    private Deck mDeck;
    protected GameUI.GameUIController mController;
    protected LinkedList<HandCard> mHand;

}
