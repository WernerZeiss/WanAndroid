package com.zcrain.wanandroid.ui.binding

import android.util.Log
import androidx.databinding.BindingAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * @author CWQ
 * @date 11/25/20
 */
@BindingAdapter("bindLoading")
fun bindLoading(smartRefreshLayout: SmartRefreshLayout, isLoading: Boolean) {
    Log.e("bindLoading","isLoading:$isLoading")
    if (!isLoading) {
        smartRefreshLayout.finishRefresh()
        smartRefreshLayout.finishLoadMore()
    }
}