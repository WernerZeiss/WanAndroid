package com.zcrain.wanandroid.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.youth.banner.adapter.BannerAdapter
import com.zcrain.wanandroid.model.BannerBean

/**
 * @author CWQ
 * @date 11/25/20
 */
class HomeBannerAdapter(private val datas: List<BannerBean>) :
    BannerAdapter<BannerBean, BannerViewHolder>(datas) {

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent?.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        return BannerViewHolder(imageView)
    }

    override fun onBindView(
        holder: BannerViewHolder,
        data: BannerBean?,
        position: Int,
        size: Int
    ) {
        Glide.with(holder.itemView).load(datas[position].imagePath)
            .into(holder.itemView as ImageView)
    }
}

class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}