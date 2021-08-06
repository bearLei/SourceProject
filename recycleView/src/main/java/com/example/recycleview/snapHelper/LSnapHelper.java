package com.example.recycleview.snapHelper;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author leix
 * @version 1
 * @createTime 2021/7/30 12:04
 * @desc
 */
public abstract class LSnapHelper extends RecyclerView.OnFlingListener {

    private static final String TAG = "LSnapHelper";
    static final float MILLISECONDS_PER_INCH = 100f;
    RecyclerView mRecyclerView;


    private final RecyclerView.OnScrollListener mScrollerListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    /**
     * @param velocityX
     * @param velocityY
     * @return true:该滑动被接收处理，否则不接收
     */
    @Override
    public boolean onFling(int velocityX, int velocityY) {
        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager == null) return false;

        RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
        if (adapter == null) return false;
        int minFlingVelocity = mRecyclerView.getMinFlingVelocity();//获取recycleView最小的滚动速度
        Log.d(TAG, "minFlingVelocity:" + minFlingVelocity);
        return (Math.abs(velocityY) > minFlingVelocity || Math.abs(velocityX) > minFlingVelocity);
    }


    /**
     * 辅助方法，触发滚动
     *
     * @param layoutManager
     * @param velocityX
     * @param velocityY
     * @return
     */
    private boolean snapFromFling(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return false;
        }


        return true;
    }

    protected RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager layoutManager) {
        return null;
    }

    protected LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutManager) {
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return null;
        }

        return new LinearSmoothScroller(mRecyclerView.getContext()) {
            @Override
            protected void onTargetFound(View targetView, RecyclerView.State state, Action action) {
                if (mRecyclerView == null) {
                    return;
                }
                int[] snapDistances = calculateDistanceToFinalSnap(mRecyclerView.getLayoutManager(), targetView);
                final int dx = snapDistances[0];
                final int dy = snapDistances[1];
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
            }
        };
    }

    protected int calculateTimeForDeceleration(int dx){
        return
    }

    protected int calculateTimeForScrolling(int dx){
        return Math.ceil(Math.abs(dx) * )
    }

    private float getSpeedPerPixel(){

    }

    /**
     * 重写此方法以捕捉目标视图或容器中的特定点
     * 这个方法被调用的时机：SnapHelper打断了1个滚动并且需要需要知道精确的滚动距离 以便去对齐一个目标视图
     * @return out[0]是水平滑动的距离，out[1]是垂直滑动的距离
     */
    abstract int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View targetView);
}
