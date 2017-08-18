package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import com.dicetcg.xvnm.dicetcg.render.Renderable;

import java.util.ArrayList;

/**
 * Created by xvnm on 8/18/17.
 */

public class Field extends Renderable {

    public Field() {
        mUserCard = new ArrayList<>(3);
        mEnemyCard = new ArrayList<>(3);
    }

    public Control getUserFieldController() {
        return new Control(mUserCard);
    }

    public Control getEnemyFieldController() {
        return new Control(mEnemyCard);
    }

    protected class Control {

        public Control(ArrayList<Card> slots) {
            mSlots = slots;
        }

        public boolean slotAvailable(int index) {
            if (mSlots.get(index) == null)
                return true;
            return false;
        }

        public void playCard(int slotNumber, MetaCard metaCard) {
            mSlots.set(slotNumber, new Card(metaCard));
        }

        private ArrayList<Card> mSlots;

    }

    private ArrayList<Card> mUserCard;
    private ArrayList<Card> mEnemyCard;

}
