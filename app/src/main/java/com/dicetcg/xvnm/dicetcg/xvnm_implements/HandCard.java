package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import android.view.MotionEvent;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;

import java.util.Random;

/**
 * Created by xvnm on 8/19/17.
 */

public class HandCard extends Touchable {

    final float mag = 2.0f;

    public HandCard(MetaCard metaCard, GLRenderer renderer) {
        mMetaCard = metaCard;
        mW = ((float)renderer.getScreenWidth()/100) * 17;
        mH = mW / 2 * 3;

        Random random = new Random();
        mR = random.nextFloat();
        mG = random.nextFloat();
        mB = random.nextFloat();

        mX = 0;
        mY = 0;

        mPressed = false;
        mMoving = false;

        renderTexture(true);
        setTexture(renderer.getTexture(metaCard.getName()));
    }

    public void render(int total, int index, GLRenderer renderer) {
        if (!mMoving) {
            float width = (total * mW) - 0.3f / total;
            float offset = (renderer.getScreenWidth() - width) / 2;
            mX = (width / total) * index + offset;
        }
        super.render(renderer);
    }

    @Override
    public float getX() {
        return mX;
    }

    @Override
    public float getY() {
        return mY;
    }

    @Override
    public float getZ() {
        return mPressed ? 0.3f : 0.2f;
    }

    @Override
    public float getW() {
        return mPressed ? mW * mag : mW ;
    }

    @Override
    public float getH() {
        return mPressed ? mH * mag : mH;
    }

    @Override
    public float getR() {
        return mR;
    }

    @Override
    public float getB() {
        return mB;
    }

    @Override
    public float getG() {
        return mG;
    }

    public void setY(float y) {
        mY = y;
    }

    public MetaCard getMetaCard() {
        return mMetaCard;
    }

    public int onTouch(MotionEvent event, GameUI.GameUIController controller) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (checkTouch(event.getX(), controller.getRenderer().getScreenHeight() - event.getY())) {
                    mPressed = true;
                    return 1;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mPressed)
                    break;
                mMoving = true;
                mX = event.getX() - mag / 2 * mW;
                mY = controller.getRenderer().getScreenHeight() - event.getY();
                return 1;
            case MotionEvent.ACTION_UP:
                if (mPressed) {
                    mPressed = false;
                    mY = 0;
                }
                if (mMoving) {
                    mMoving = false;
                    int slot = controller.getFieldController().evalSlot(event.getX(), controller.getRenderer().getScreenHeight() - event.getY());
                    System.out.println("cost: " + mMetaCard.getSummonCost() + ", mana: " + controller.getCurrentMana());    // TODO Render cost
                    if (slot != -1 && controller.getFieldController().slotAvailable(slot) && controller.getCurrentMana() >= mMetaCard.getSummonCost()) {
                        controller.getFieldController().playCard(slot, mMetaCard, true);
                        return 2;
                    }
                    return 3;
                }
                break;
            default:
        }

        return 0;
    }

    public boolean isPressed() {
        return mPressed;
    }

    private MetaCard mMetaCard;
    private float mW, mH;
    private float mR, mG, mB;
    private float mX, mY;
    private boolean mPressed, mMoving;

}
