package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import android.os.Handler;
import android.os.Message;

import com.dicetcg.xvnm.dicetcg.R;

/**
 * Created by xvnm on 8/17/17.
 */

public class MetaCard {

    public MetaCard(String name, int diceLowerMax, int lowerDamage, int higherDamage,
                int hp, int dp, int summonCost, int maintainCost) {
        mName = name;
        mDiceLowerMax = diceLowerMax;
        mLowerDamage = lowerDamage;
        mHigherDamage = higherDamage;
        mHP = hp;
        mDP = dp;
        mSummonCost = summonCost;
        mMaintainCost = maintainCost;

    }

    public int evaluteDamage(int dice) {
        if (dice > mDiceLowerMax)
            return mHigherDamage;
        return mLowerDamage;
    }

    public int getHP() {
        return mHP;
    }

    public int getDP() {
        return mDP;
    }

    public int getSummonCost() {
        return mSummonCost;
    }

    public int getMaintainCost() {
        return mMaintainCost;
    }

    private String mName;
    private int mDiceLowerMax;
    private int mLowerDamage;
    private int mHigherDamage;
    private int mHP;
    private int mDP;
    private int mSummonCost;
    private int mMaintainCost;

}
