package com.zcrain.wanandroid.behavior

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.animation.addListener
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView

/**
 * @author CWQ
 * @date 12/14/20
 */
class FloatBtnBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<TextView>(context, attrs) {

    private val mHandle = AnimHandle()

    companion object {

        private var showAnimed = false
        private var hideAnimed = false
        private var hideTransAnim : ObjectAnimator? = null
        private var showTransAnim : ObjectAnimator? = null

        @Synchronized
        private fun doHideAnim(view: View) {
            if (!hideAnimed) {
                hideAnimed = true
                if (hideTransAnim == null){
                    hideTransAnim = ObjectAnimator.ofFloat(view, "translationX", 0f, 250f)
                    hideTransAnim?.duration = 200
                }
                hideTransAnim?.start()
            }
        }

        @Synchronized
        private fun doShowAnim(view: View) {
            if (!showAnimed) {
                showAnimed = true
                if (showTransAnim == null){
                    showTransAnim = ObjectAnimator.ofFloat(view, "translationX", 250f, 0f)
                    showTransAnim?.duration = 200
                    showTransAnim?.addListener(
                        onEnd = {
                            showAnimed = false
                            hideAnimed = false
                        }
                    )
                }
                showTransAnim?.start()
            }
        }
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
        if (target is NestedScrollView) {
            val msg = Message.obtain(mHandle, 1, child)
            mHandle.sendMessage(msg)
        }
    }


    class AnimHandle : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> {
                    doHideAnim(msg.obj as View)
                    removeMessages(2)
                    msg.what = 2
                    val msgH = Message.obtain(msg)
                    sendMessageDelayed(msgH, 300)
                }
                2 -> { //展示
                    doShowAnim(msg.obj as View)
                }
            }
        }
    }


}