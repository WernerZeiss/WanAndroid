package com.zcrain.wanandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayout
import com.zcrain.wanandroid.R
import com.zcrain.wanandroid.databinding.ItemAdapterTreeBinding
import com.zcrain.wanandroid.model.TreeBean
import java.util.*

/**
 * @author CWQ
 * @date 12/22/20
 */
class TreeAdapter(private var datas: List<TreeBean>) : RecyclerView.Adapter<TreeViewHolder>() {

    private val mFlexItemTextViewCaches = LinkedList<TextView>()
    private var mInflater: LayoutInflater? = null
    private var mOnClickItem: ((TreeBean, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeViewHolder {
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_tree, parent, false)
        return TreeViewHolder(root)
    }

    override fun onBindViewHolder(holder: TreeViewHolder, position: Int) {
        holder.mBinding.tvName.text = datas[position].name
        val children = datas[position].children
        if (!children.isNullOrEmpty()) {
            for (i in children.indices) {
                val tv = creativeOrGetCacheTextView(holder.mBinding.fbl)
                tv.setOnClickListener {
                    mOnClickItem?.invoke(datas[position], i)
                }
                tv.text = children[i].name
                holder.mBinding.fbl.addView(tv)
            }
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onViewRecycled(holder: TreeViewHolder) {
        super.onViewRecycled(holder)
        for (i in 0..holder.mBinding.fbl.childCount) {
            mFlexItemTextViewCaches.offer(holder.mBinding.fbl.getChildAt(i) as TextView?)
        }
        holder.mBinding.fbl.removeAllViews()
    }


    fun setOnClickItemListener(listener: (TreeBean, Int) -> Unit) {
        mOnClickItem = listener
    }

    fun setNewData(newDatas: List<TreeBean>?) {
        datas = newDatas ?: emptyList()
        notifyDataSetChanged()
    }

    private fun creativeOrGetCacheTextView(flexboxLayout: FlexboxLayout): TextView {
        return mFlexItemTextViewCaches.poll() ?: creativeTextView(flexboxLayout)
    }

    private fun creativeTextView(flexboxLayout: FlexboxLayout): TextView {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(flexboxLayout.context)
        }
        return mInflater!!.inflate(R.layout.item_rv_tree_child, flexboxLayout, false) as TextView
    }
}


class TreeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val mBinding: ItemAdapterTreeBinding by lazy {
        requireNotNull(DataBindingUtil.bind(view))
    }
}