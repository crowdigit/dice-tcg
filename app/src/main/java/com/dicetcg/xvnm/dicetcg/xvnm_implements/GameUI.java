package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import android.view.MotionEvent;

import com.dicetcg.xvnm.dicetcg.MainActivity;
import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.Renderable;

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

        GameUIController userController = new GameUIController() {
            @Override
            Field.Control getFieldController() {
                return mField.getUserFieldController();
            }
            @Override
            int getCurrentMana() {
                return mUser.getMana();
            }
        };

        GameUIController enemyController = new GameUIController() {
            @Override
            Field.Control getFieldController() {
                return mField.getEnemyFieldController();
            }
            @Override
            int getCurrentMana() {
                return mEnemy.getMana();
            }
        };

        mController = new GameUIController() {
            @Override
            Field.Control getFieldController() {
                return null;
            }
            @Override
            int getCurrentMana() {
                return 0;
            }
        };

        mField = new Field(mController);
        mUser = new User(userController);
        mEnemy = new Enemy(enemyController);

        mUser.draw();
        mEnemy.draw();

        Thread game = new Thread(new Runnable() {
            public void run() {
                boolean attack = true; // TODO random

                // while (mUser.getHP() > 0 && mEnemy.getHP() > 0) { TODO
                while (true) {
                    String texName;
                    if (attack) texName = "attack";
                    else texName = "defend";
                    mPopup = new Popup(2000, mController.getRenderer().getTexture(texName));
                    while (!mPopup.done()) { }
                    mPopup = null;

                    if (attack) {
                        System.out.println("player attacks");
                        mUser.enableControl();
                        mUser.takeTurn(mField.getUserFieldController(), attack);
                        mEnemy.takeTurn(mField.getEnemyFieldController(), attack);
                    } else {
                        System.out.println("enemy attacks");
                        mEnemy.takeTurn(mField.getEnemyFieldController(), attack);
                        mUser.enableControl();
                        mUser.takeTurn(mField.getUserFieldController(), attack);
                    }
                    System.out.println("poped up");
                    mField.combat(attack);
                    attack = !attack;
                    System.out.println("P: " + mUser.getHP() + ", E: " + mEnemy.getHP());
                    System.out.println("turn");
                }
            }
        });
        game.start();

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
        return mUser.onTouch(event);
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
        mField.render(renderer);
        mUser.render(renderer);
        mEnemy.render(renderer);
        if (mPopup != null)
            mPopup.render(renderer);
    }

    protected abstract class GameUIController {

        ArrayList<MetaCard> getMetaCards() {
            return mActivity.getMetaCards();
        }

        GLRenderer getRenderer() {
            return mActivity.getRenderer();
        }

        public void deal(boolean attack, int damage) {
            if (attack)
                mEnemy.setHP(mEnemy.getHP() - damage);
            else
                mUser.setHP(mUser.getHP() - damage);
        }

        abstract Field.Control getFieldController();
        abstract int getCurrentMana();

    }

    private MainActivity mActivity;
    private int mW, mH;
    private Fader mFader;
    private User mUser;
    private Enemy mEnemy;
    private Field mField;
    private GameUIController mController;
    private Popup mPopup;

}
