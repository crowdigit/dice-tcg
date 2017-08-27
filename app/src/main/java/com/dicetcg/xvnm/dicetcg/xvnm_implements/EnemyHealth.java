package com.dicetcg.xvnm.dicetcg.xvnm_implements;

import com.dicetcg.xvnm.dicetcg.render.GLRenderer;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by xvnm on 8/25/2017.
 */

public class EnemyHealth extends Cost {

    private Touchable result;
    public Health h;
    public int EnemyHp;

    @Override
    public void setNumber(int num, GameUI.GameUIController controller) {

        if (num < 0)
            num = 0;

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

        synchronized (getDigits()) {
            getDigits().clear();
        }

        int cnt = 0;
        for (int n : digits) {
            Cost.Number c = new Cost.Number(controller, n);
            float side = (float)((controller.getFieldController().getY() - ((float)controller.getRenderer().getScreenWidth())/200*51)*0.75);
            float offset = side / 10;

            float cardHeight = ((float)controller.getRenderer().getScreenWidth()/100*17) / 2 * 3;
            float y = (controller.getFieldController().getY() + controller.getFieldController().getH());

            side -= offset * 2;
            y += offset;
            c.setSide(side);
            c.setY((float) (y * 1.05));
            c.setX(controller.getRenderer().getScreenWidth() / 2 - (float)digits.size() / 2 * c.getW() + c.getW() * cnt++);
            synchronized (super.getDigits()) {
                getDigits().add(c);
            }
        }
    }
    public int getEnemyHp(){
        return EnemyHp;
    }
}
