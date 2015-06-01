package com.stupidman.admin.collectionandroiddemo.game2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by admin on 2015/6/1.
 */
public class Card extends FrameLayout {

    private TextView label;
    private int num;

    public Card(Context context) {
        super(context);

        label = new TextView(getContext());
        label.setTextSize(32);
        label.setBackgroundColor(0x33ffffff);   //设置背景色
        label.setGravity(Gravity.CENTER);   //设置居中

        LayoutParams lp = new LayoutParams(-1, -1); //使方格自适应屏幕
        lp.setMargins(10, 10, 0, 0);
        addView(label, lp);

        setNum(0);
    }

    public int getNum() {
        return num;
    }

    /**
     * 定义一个在方格中显示数字
     *
     * @param num
     */
    public void setNum(int num) {
        this.num = num;

        if (num <= 0) {
            label.setText("");
        } else {
            label.setText(num + "");
        }
    }

    public boolean equal(Card card) {
        return getNum() == card.getNum();
    }
}
