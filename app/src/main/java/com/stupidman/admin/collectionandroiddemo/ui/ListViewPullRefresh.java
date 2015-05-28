package com.stupidman.admin.collectionandroiddemo.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by admin on 2015/5/27.
 */
public class ListViewPullRefresh extends LinearLayout implements View.OnTouchListener {

    /**
     * 下拉状态
     */
    private static final int STATUS_PULL_TO_REFRESH = 0;

    /**
     * 释放立即刷新状态
     */
    private static final int STATUS_RELEASE_TO_REFRESH = 1;

    public ListViewPullRefresh(Context context) {
        super(context);
    }

    public ListViewPullRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewPullRefresh(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ListViewPullRefresh(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
