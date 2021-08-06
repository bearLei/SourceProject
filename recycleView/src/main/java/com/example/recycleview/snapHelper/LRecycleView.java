package com.example.recycleview.snapHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;


/**
 * @author leix
 * @version 1
 * @createTime 2021/8/6 9:05
 * @desc
 */
public class LRecycleView extends ViewGroup {

    public LRecycleView(Context context) {
        super(context);
    }

    public LRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
