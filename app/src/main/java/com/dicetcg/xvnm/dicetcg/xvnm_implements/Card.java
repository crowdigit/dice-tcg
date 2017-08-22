package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import com.dicetcg.xvnm.dicetcg.render.Renderable;

/**
 * Created by xvnm on 8/17/17.
 */

public class Card extends Renderable {

    public Card(MetaCard meta, int texID) {
        mMeta = meta;
        renderTexture(true);
        setTexture(texID);
        mHP = mMeta.getHP();
        mAttacking = 0;
    }

    public void attack(boolean attackEnemy, boolean fromUser, float targetY) {
        if (fromUser) {
            if (attackEnemy)
                mAttacking = 1;     // user is attacking enemy player
            else
                mAttacking = 2;     // user is attacking enemy card;
        } else {
            if (attackEnemy)
                mAttacking = 3;     // enemy is attacking user
            else
                mAttacking = 4;     // enemy is attacking user card;
        }
        mTargetY = targetY;
        mAttackStarted = System.currentTimeMillis();
    }

    public int getHP() {
        return mHP;
    }

    public void setHP(int hp) {
        mHP = hp;
    }

    boolean isAttacking() {
        return mAttacking != 0;
    }

    @Override
    public float getX() {
        return mX;
    }

    @Override
    public float getY() {
        long dt = 0;
        if (mAttacking != 0)
            dt = System.currentTimeMillis() - mAttackStarted;
        switch (mAttacking) {
            case 1:
                if (dt <= 500)
                    return mY + (mTargetY - mH - mY) / (500 * 500) * dt * dt;
                else if (dt <= 1000)
                    return mY + (mTargetY - mH - mY) / (500 * 500) * (1000 - dt) * (1000 - dt);
                else mAttacking = 0;
                break;
            case 2:
                if (dt <= 500)
                    return mY + 0.5f * mH / (500 * 500) * dt * dt;
                else if (dt <= 1000)
                    return mY + 0.5f * mH / (500 * 500) * (1000 - dt) * (1000 - dt);
                else mAttacking = 0;
                break;
            case 3:
                if (dt <= 500)
                    return mY - mY / (500 * 500) * dt * dt;
                else if (dt <= 1000)
                    return mY - mY / (500 * 500) * (1000 - dt) * (1000 - dt);
                else mAttacking = 0;
                break;
            case 4:
                if (dt <= 500)
                    return mY - 0.5f * mH / (500 * 500) * dt * dt;
                else if (dt <= 1000)
                    return mY - 0.5f * mH / (500 * 500) * (1000 - dt) * (1000 - dt);
                else mAttacking = 0;
                break;
        }
        return mY;
    }

    @Override
    public float getZ() {
        return mAttacking != 0 ? 0.4f : 0.3f;
    }

    @Override
    public float getW() {
        return mW;
    }

    @Override
    public float getH() {
        return mH;
    }

    @Override
    public float getR() {
        return 0.5f;
    }

    public void setX(float x) {
        mX = x;
    }

    public void setY(float y) {
        mY = y;
    }

    public void setW(float w) {
        mW = w;
    }

    public void setH(float h) {
        mH = h;
    }

    public MetaCard getMetaCard() {
        return mMeta;
    }

    private MetaCard mMeta;
    private float mX, mY;
    private float mW, mH;
    private int mHP;
    private int mAttacking;
    private long mAttackStarted;
    private float mTargetY;

}