package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import android.view.MotionEvent;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;

import java.util.Random;

/**
 * Created by xvnm on 8/21/17.
 */

public class EndButton extends Touchable {

    public EndButton(GameUI.GameUIController controller) {
        Random random = new Random();
        mR = random.nextFloat();
        mG = random.nextFloat();
        mB = random.nextFloat();

        mW = controller.getRenderer().getScreenWidth() / 5;
        mH = controller.getFieldController().getY() - ((float)controller.getRenderer().getScreenWidth())/200*51;
        float offset = this.mH / 10;
        mY = controller.getFieldController().getY() - this.mH;
        mH -= offset * 2;
        mY += offset;
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return mY;
    }

    @Override
    public float getZ() {
        return 0.2f;
    }

    @Override
    public float getR() {
        return mR;
    }

    @Override
    public float getG() {
        return mG;
    }

    @Override
    public float getB() {
        return mB;
    }

    @Override
    public float getW() {
        return mW;
    }

    @Override
    public float getH() {
        return mH;
    }

    public void onTouch(MotionEvent event, GameUI.GameUIController controller) {
        if (checkTouch(event.getX(), controller.getRenderer().getScreenHeight() - event.getY())) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mButtonPressed = true;
                    break;
                case MotionEvent.ACTION_UP:
                    if (mButtonPressed) {
                        mTurnEnded = true;
                        mButtonPressed = false;
                    }
            }
        }
    }

    @Override
    public void render(GLRenderer renderer) {
        setTexture(renderer.getTexture("next"));
        super.render(renderer);
    }

    public boolean isButtonPressed() {
        return mButtonPressed;
    }

    public boolean isTurnEnded() {
        if (mTurnEnded) {
            mTurnEnded = false;
            return true;
        }
        return false;
    }

    private float mY;
    private float mW, mH;
    private float mR, mG, mB;
    private boolean mButtonPressed;
    private boolean mTurnEnded;
}
