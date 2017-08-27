package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.Renderable;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by xvnm on 8/24/2017.
 */

public class Cost {

    public class Number extends Renderable {

        public Number(GameUI.GameUIController controller, int num) {
            mNumber = num;
            renderTexture(true);
        }

        public void setX(float x) {
            mX = x;
        }

        public void setY(float y) {
            mY = y;
        }

        public void setSide(float side) {
            mSide = side;
        }

        @Override
        public void render(GLRenderer renderer) {
            setTexture(renderer.getTexture(Integer.toString(mNumber)));
            super.render(renderer);
        }

        @Override
        public float getX() {
            return mX;
        }

        @Override
        public float getY() {
            return mY;
        }

        @Override
        public float getZ() {
            return 0.1f;
        }

        @Override
        public float getW() {
            return mSide / 2;
        }

        @Override
        public float getH() {
            return (float) (mSide * 0.5);
        }

        private float mX, mY;
        private float mSide;
        private int mNumber;

    }

    public Cost() {
        mDigits = new LinkedList<>();
    }

    public void setNumber(int num, GameUI.GameUIController controller) {
        synchronized (mDigits) {
            if (num < 0)
                num = 0;
            LinkedList<Integer> digits = new LinkedList<>();
            if (num == 0) {
                digits.add(0);
            } else
                while (num != 0) {
                    digits.add(num % 10);
                    num /= 10;
                }
            Collections.reverse(digits);

            mDigits.clear();

            int cnt = 0;
            for (int n : digits) {
                Cost.Number c = new Cost.Number(controller, n);
                float side = (float) ((controller.getFieldController().getY() - ((float) controller.getRenderer().getScreenWidth()) / 200 * 51)*0.75);
                float offset = side / 10;
                float y = controller.getFieldController().getY() - side;
                side -= offset * 2;
                y += offset;
                c.setSide(side);
                c.setY((float) (y * 1.1));
                c.setX((float) ((controller.getRenderer().getScreenWidth() - c.getW() * 3 - (float) digits.size() / 2 * c.getW() + c.getW() * cnt++)*1.1));
                mDigits.add(c);
            }
        }
    }

    public void render(GLRenderer renderer) {
        synchronized (this) {
            for (Renderable r : mDigits)
                r.render(renderer);
        }
    }

    protected LinkedList<Renderable> getDigits() {
        return mDigits;
    }

    private LinkedList<Renderable> mDigits;

}
