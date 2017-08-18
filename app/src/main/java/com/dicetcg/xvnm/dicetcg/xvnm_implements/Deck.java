package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xvnm on 8/18/17.
 */

public class Deck {

    public Deck(ArrayList<MetaCard> metaCards) {
        mMetaCards = metaCards;
        LinkedList<Integer> tmpSeq = new LinkedList<>();
        for (int i = 0; i < mMetaCards.size(); i++)
            tmpSeq.add(i);
        Collections.shuffle(tmpSeq);
        mCardIndexSequence = tmpSeq;
    }

    public MetaCard draw() {
        return mMetaCards.get(mCardIndexSequence.poll());
    }

    public int getCardCount() {
        return mCardIndexSequence.size();
    }

    private Queue<Integer> mCardIndexSequence;
    private ArrayList<MetaCard> mMetaCards;

}
