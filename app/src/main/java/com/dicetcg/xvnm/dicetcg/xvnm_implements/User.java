package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;

import java.util.ArrayList;

/**
 * Created by xvnm on 8/18/17.
 */

public class User extends Player {

    public User(GameUI.GameUIController controller) {
        super(controller);
    }

    @Override
    public void takeTurn(Field.Control field, boolean attack) {
        draw();
    }

}
