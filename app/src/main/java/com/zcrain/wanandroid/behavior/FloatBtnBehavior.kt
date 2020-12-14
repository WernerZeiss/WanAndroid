package com.zcrain.wanandroid.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * @author CWQ
 * @date 12/14/20
 */
class FloatBtnBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<TextView>(context, attrs) {

    companion object {
        const val TAG = "FloatBtnBehavior"
    }


    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: TextView,
        ev: MotionEvent
    ): Boolean {
        return super.onInterceptTouchEvent(parent, child, ev)
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: TextView,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return (axes and ViewCompat.SCROLL_AXIS_VERTICAL) != 0
    }


    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: TextView,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        Log.e(TAG, "dx:$dx,dy:$dy")
        if (target is RecyclerView){
            when(target.scrollState){
                RecyclerView.SCROLL_STATE_SETTLING -> {
                    //滚动
                }
                RecyclerView.SCROLL_STATE_IDLE -> {
                    //停止
                }
            }
        }
    }
}