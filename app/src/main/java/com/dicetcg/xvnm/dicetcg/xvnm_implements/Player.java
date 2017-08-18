package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;

import java.util.ArrayList;

/**
 * Created by xvnm on 8/17/17.
 */

abstract public class Player {

    Player(GameUI.GameUIController controller) {
        mDeck = new Deck(controller.getMetaCards());
        mHand = new MetaCard[5];
        mHandCount = 0;
    }

    public int getHP() {
        return mHP;
    }

    public void setHP(int hp) {
        mHP = hp;
    }

    protected void draw() {
        for (int i = mHandCount; i < 5; i++) {
            if (mDeck.getCardCount() > 0) {
                mHand[mHandCount] = mDeck.draw();
            } else {
                setHP(getHP() - 500);
            }
        }
    }

    abstract public void takeTurn(Field.Control field, boolean attack);

    private int mHP;
    private int mMaxCost, mCost;
    private Deck mDeck;
    protected MetaCard[]mHand;
    protected int mHandCount;

}
