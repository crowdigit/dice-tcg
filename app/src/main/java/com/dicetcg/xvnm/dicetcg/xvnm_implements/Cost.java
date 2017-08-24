package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;
import com.dicetcg.xvnm.dicetcg.render.Renderable;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by xvnm on 8/24/2017.
 */

public class Cost {

    private class Number extends Renderable {

        public Number(GameUI.GameUIController controller, int num, int totaldigit, int digit) {
            mNumber = num;
            mDigit = digit;
            renderTexture(true);
            mSide = controller.getFieldController().getY() - ((float)controller.getRenderer().getScreenWidth())/200*51;
            // mX = (float)controller.getRenderer().getScreenWidth() / 2 - (float)totaldigit / 2 * getW() + getW() * digit;
            mX = controller.getRenderer().getScreenWidth() - getW() * 3 - (float)totaldigit / 2 * getW() + getW() * digit;
            float offset = getH() / 10;
            mY = controller.getFieldController().getY() - getH();
            mSide -= offset * 2;
            mY += offset;
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
            return mSide;
        }

        private float mX, mY;
        private float mSide;
        private int mNumber, mDigit;

    }

    public Cost() {
        mDigits = new LinkedList<>();
    }

    public void setNumber(int num, GameUI.GameUIController controller) {
        LinkedList<Integer> digits = new LinkedList<>();
        if (num == 0) {
            digits.add(0);
        }
        else
            while (num != 0) {
                digits.add(num % 10);
                num /= 10;
            }
        Collections.reverse(digits);

        mDigits.clear();

        int cnt = 0;
        for (int n : digits)
            mDigits.add(new Cost.Number(controller, n, digits.size(), cnt++));
    }

    public void render(GLRenderer renderer) {
        for (Renderable r : mDigits)
            r.render(renderer);
    }

    private LinkedList<Renderable> mDigits;

}
