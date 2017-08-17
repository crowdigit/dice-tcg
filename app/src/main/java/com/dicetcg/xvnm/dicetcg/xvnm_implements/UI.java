package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import android.view.MotionEvent;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;

/**
 * Created by dogtrollin on 8/17/17.
 */

public interface UI {

    void start(GLRenderer renderer);
    boolean onTouch(MotionEvent event);

}
