package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import android.view.MotionEvent;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;

/**
 * Created by xvnm on 8/18/17.
 */

public class User extends Player {

    public User(GameUI.GameUIController controller) {
        super(controller);
        mIsMovingCard = false;
        mEndTurnButton = new EndButton(controller);
        draw();
    }

    @Override
    public void takeTurn(Field.Control field, boolean attack) {
        super.takeTurn(field, attack);
        System.out.println("M: " + getManaMax() + ", m: " + getMana());
        while (isControlEnabled()) {
            // wait for user to end their turn
        }
    }

    @Override
    public void render(GLRenderer renderer) {
        int index = 0;
        for (HandCard card : mHand)
            card.render(mHand.size(), index++, renderer);
        mEndTurnButton.render(renderer);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        if (mControlEnabled) {
            boolean r = false;

            for (int i = 0; i < mHand.size(); i++) {
                int f = mHand.get(i).onTouch(event, mController);
                if ((f & 1) == 1) {
                    r = true;
                    mIsMovingCard = true;
                }

                if ((f & 2) == 2) {
                    subMana(mHand.get(i).getMetaCard().getSummonCost());
                    mHand.remove(i);
                    mIsMovingCard = false;
                }

                if (f == 4)
                    mIsMovingCard = false;
            }

            if (!mIsMovingCard) {
                mEndTurnButton.onTouch(event, mController);
                if (mEndTurnButton.isTurnEnded()) {
                    mControlEnabled = false;
                }
                if (mEndTurnButton.isButtonPressed())
                    return true;
            }

            return r;
        }
        return false;
    }

    public void enableControl() {
        mControlEnabled = true;
    }

    public boolean isControlEnabled() {
        return mControlEnabled;
    }

    private boolean mControlEnabled;
    private boolean mIsMovingCard;
    private EndButton mEndTurnButton;

}
