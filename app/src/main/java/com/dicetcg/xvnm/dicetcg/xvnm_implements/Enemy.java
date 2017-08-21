package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import android.view.MotionEvent;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;

import java.util.ArrayList;

/**
 * Created by xvnm on 8/18/17.
 */

public class Enemy extends Player {

    public Enemy(GameUI.GameUIController controller) {
        super(controller);
    }

    @Override
    public void takeTurn(Field.Control field, boolean attack) {
        draw();
    }

    @Override
    public void render(GLRenderer renderer) {
        int index = 0;
        for (HandCard card : mHand) {
            card.setY(renderer.getScreenHeight() - card.getH());
            card.render(mHand.size(), index++, renderer);
        }
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        return false;
    }
}
