package com.zcrain.wanandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zcrain.wanandroid.R
import com.zcrain.wanandroid.databinding.ItemAdapterArticlesBinding
import com.zcrain.wanandroid.model.ArticleBean

/**
 * @author CWQ
 * @date 11/26/20
 */
class ArticlesAdapter : RecyclerView.Adapter<ArticlesViewHolder>() {

    var mTopDatas = mutableListOf<ArticleBean>()
    var mAllDatas = mutableListOf<ArticleBean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_adapter_articles, parent, false)
        return ArticlesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bindData(mAllDatas[position])
    }

    override fun getItemCount(): Int {
        return mAllDatas.size
    }

    fun setCommonData(newData: List<ArticleBean>?) {
        if (newData != null) {
            mAllDatas.clear()
            mAllDatas.addAll(mTopDatas)
            mAllDatas.addAll(newData)
            notifyDataSetChanged()
        }
    }

    fun setTopData(topData: List<ArticleBean>) {
        if (mTopDatas.isNotEmpty()) {
            mAllDatas.removeAll(mTopDatas)
        }
        if (topData.isNotEmpty()) {
            val tempList = mutableListOf<ArticleBean>()
            tempList.addAll(topData)
            tempList.addAll(mAllDatas)
            mAllDatas = tempList
        }
        notifyItemRangeChanged(0, mTopDatas.size.coerceAtLeast(topData.size))
        mTopDatas.clear()
        mTopDatas.addAll(topData)
    }

    fun addCommonData(newData: List<ArticleBean>?) {
        if (!newData.isNullOrEmpty()) {
            this.mAllDatas.addAll(newData)
            notifyItemRangeInserted(mAllDatas.size - newData.size, newData.size)
        }
    }
}

class ArticlesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val mBinding: ItemAdapterArticlesBinding by lazy {
        requireNotNull(
            DataBindingUtil.bind(
                view
            )
        )
    }

    fun bindData(data: ArticleBean) {
        mBinding.article = data
    }
}