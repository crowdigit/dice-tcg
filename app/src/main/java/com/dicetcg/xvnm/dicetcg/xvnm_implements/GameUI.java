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
        renderTexture(true);

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

                mUser.updateHealth();
                mEnemy.updateHealth();

                while (mUser.getHP() > 0 && mEnemy.getHP() > 0) {
                    String texName;
                    if (attack) texName = "attack";
                    else texName = "defend";
                    mPopup = new Popup(1600, mController.getRenderer().getTexture(texName));
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
                    mPopup = new Popup(1600, mController.getRenderer().getTexture("battle"));
                    while (!mPopup.done()) { }
                    mPopup = null;
                    mField.combat(attack);
                    attack = !attack;
                    System.out.println("P: " + mUser.getHP() + ", E: " + mEnemy.getHP());
                    System.out.println("turn");
                }
                if (mUser.getHP() < 0)
                    mPopup = new Popup(1600, mController.getRenderer().getTexture("lose"));
                else
                    mPopup = new Popup(1600, mController.getRenderer().getTexture("win"));
                while (!mPopup.done()) { }
                mFader = new Fader();
                mFader.start(1000, true);
                while (mFader != null) { }
                mActivity.setCurrentUI(0);
            }
        });
            if (mActivity.intro.isPlaying()) mActivity.intro.stop();
            if (mActivity.lobby.isPlaying()) mActivity.lobby.stop();
        game.start();
    }

    @Override
    public void stop() {
    }

    @Override
    public void init(GLRenderer renderer) {
        mW = renderer.getScreenWidth();
        mH = renderer.getScreenHeight();
        renderTexture(true);
        setTexture(renderer.getTexture("start"));

        mActivity.lobby.setLooping(true);
        mActivity.lobby.setVolume(100,100);
        mActivity.lobby.start();

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
        return 0.5f;
    }

    @Override
    public float getG() {
        return 0.3f;
    }

    @Override
    public float getB() {
        return 0.3f;
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

        public void updateUserHealth() {
            mUser.updateHealth();
        }

        public void updateEnemyHealth() {
            mEnemy.updateHealth();
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
