package com.dicetcg.xvnm.dicetcg.xvnm_implements;

/**
 * Created by dogtrollin on 8/17/17.
 */

public class Player {

    public int getHP() {
        return mHP;
    }

    public void setHP(int hp) {
        mHP = hp;
    }

    public boolean isDefeated() {
        return mHP <= 0;
    }

    private int mHP;
    private int mMaxCost, mCost;

}
