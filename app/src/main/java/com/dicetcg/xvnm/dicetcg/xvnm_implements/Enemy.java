package com.dicetcg.xvnm.dicetcg.xvnm_implements;

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
}
