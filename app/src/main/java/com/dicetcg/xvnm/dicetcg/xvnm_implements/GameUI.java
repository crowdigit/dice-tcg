package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import android.text.LoginFilter;
import android.view.MotionEvent;

import com.dicetcg.xvnm.dicetcg.MainActivity;
import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.Renderable;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by xvnm on 8/17/17.
 */

public class GameUI extends Renderable implements UI {

    public GameUI(MainActivity activity) {
        mActivity = activity;
    }

    @Override
    public void start() {
        mActivity.getRenderer().clearRenderables();
        mActivity.getRenderer().registerRenderable(this);
        renderTexture(false);

        mFader = new Fader();
        mFader.start(500, false);

        mField = new Field();

        boolean attack = true; // TODO random

        GameUIController userController = new GameUIController() {
            @Override
            Field.Control getFieldController() {
                return mField.getUserFieldController();
            }
        };

        GameUIController enemyController = new GameUIController() {
            @Override
            Field.Control getFieldController() {
                return mField.getEnemyFieldController();
            }
        };

        mUser = new User(userController);
        mEnemy = new Enemy(enemyController);

        while (mUser.getHP() >= 0 || mEnemy.getHP() >= 0) {
            mUser.takeTurn(mField.getUserFieldController(), attack);
            mEnemy.takeTurn(mField.getEnemyFieldController(), !attack);
            attack = !attack;
        }

    }

    @Override
    public void stop() {
        mFader = null;
        mUser = null;
        mEnemy = null;
    }

    @Override
    public void init(GLRenderer renderer) {
        mW = renderer.getScreenWidth();
        mH = renderer.getScreenHeight();
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        return false;
    }

    @Override
    public float getZ() {
        return -0.1f;
    }

    @Override
    public float getW() {
        return mW;
    }

    @Override
    public float getH() {
        return mH;
    }

    @Override
    public float getR() {
        return 1.0f;
    }

    @Override
    public void render(GLRenderer renderer) {
        super.render(renderer);
        if (mFader != null) {
            mFader.render(renderer);
            if (!mFader.isActive())
                mFader = null;
        }
    }

    protected abstract class GameUIController {

        void awaitSelection() {
        }

        ArrayList<MetaCard> getMetaCards() {
            return mActivity.getMetaCards();
        }

        abstract Field.Control getFieldController();

    }

    private MainActivity mActivity;
    private int mW, mH;
    private Fader mFader;
    private Player mUser, mEnemy;
    private Field mField;

}
