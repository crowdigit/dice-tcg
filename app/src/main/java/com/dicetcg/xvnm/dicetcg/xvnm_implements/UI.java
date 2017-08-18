package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import android.view.MotionEvent;

/**
 * Created by xvnm on 8/17/17.
 */

public interface UI {

    void start();
    void stop();
    boolean onTouch(MotionEvent event);

}
