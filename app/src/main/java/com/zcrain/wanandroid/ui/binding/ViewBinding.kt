package com.zcrain.wanandroid.ui.binding

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * @author CWQ
 * @date 11/25/20
 */
@BindingAdapter("bindLoading")
fun bindLoading(smartRefreshLayout: SmartRefreshLayout, isLoading: Boolean) {
    Log.e("bindloading","isLoading:$isLoading")
    if (!isLoading) {
        smartRefreshLayout.finishRefresh()
        smartRefreshLayout.finishLoadMore()
    }
}

@BindingAdapter("imageRes")
fun setSrc(imageView: ImageView, resId: Int) {
    imageView.setImageResource(resId)
}

@BindingAdapter("imageUrl")
fun setUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}