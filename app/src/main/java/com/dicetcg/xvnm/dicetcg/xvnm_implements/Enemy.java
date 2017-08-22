package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import android.view.MotionEvent;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;

/**
 * Created by xvnm on 8/18/17.
 */

public class Enemy extends Player {

    public Enemy(GameUI.GameUIController controller) {
        super(controller);
    }

    @Override
    public void takeTurn(Field.Control field, boolean attack) {
        // TODO implement enemy AI
        if (mHand.size() > 0)
            for (int i = 0; i < 3; i++)
                if (field.slotAvailable(i)) {
                    field.playCard(i, mHand.get(0).getMetaCard(), false);
                    mHand.remove(0);
                    break;
                }
    }

    @Override
    public void render(GLRenderer renderer) {
        int index = 0;
        synchronized (mHand) {
            for (HandCard card : mHand) {
                card.setY(renderer.getScreenHeight() - card.getH());
                card.render(mHand.size(), index++, renderer);
            }
        }
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        return false;
    }
}
