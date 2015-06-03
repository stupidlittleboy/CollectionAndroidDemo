package com.stupidman.admin.collectionandroiddemo.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.stupidman.admin.collectionandroiddemo.R;

/**
 * Created by admin on 2015/6/1.
 */
public class ClearEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {

    /**
     *  删除按钮引用
     */
    private Drawable mDrawable;

    /**
     * 控件是否有焦点
     */
    private boolean hasFocus;

    public ClearEditText(Context context) {
        this(context, null);
    }

    /**
     *
     * android.R.attr.editTextStyle 属性非常重要，很多属性都是在这里设置
     */
    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        //获取EditText的DrawableRight,加入我们没有设置就使用默认图片
        mDrawable = getCompoundDrawables()[2];
        if (mDrawable == null) {
            mDrawable = getResources().getDrawable(R.mipmap.delete);
        }

        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        ;
        //默认设置图标隐藏
        setClearIconVisible(false);
        //设置焦点改变的监听
        setOnFocusChangeListener(this);
        //设置输入框内内容发生改变的监听
        addTextChangedListener(this);

    }

    /**
     * 由于我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置在EditText的宽度 - 图标到控件右边的间距 - 图标的宽度和EditText的宽度
     * - 图标到控件右边的间距之间我们就算点击了图标，竖直方向没有考虑
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            boolean touchable = (event.getX() > (getWidth() - getTotalPaddingRight()))
                    && (event.getX() < (getWidth() - getPaddingLeft()));
            if (touchable) {
                this.setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     * @param visible
     */
    public void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    /**
     *
     * @param v
     * @param hasFocus
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }


    /**
     * 当输入框中内容发生变化时回调的方法
     * @param text
     * @param start
     * @param lengthBefore
     * @param lengthAfter
     */
    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (hasFocus) {
            setClearIconVisible(text.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
