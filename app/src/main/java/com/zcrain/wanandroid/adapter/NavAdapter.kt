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
import com.zcrain.wanandroid.model.NaviBean
import java.util.*

/**
 * @author CWQ
 * @date 12/23/20
 */
class NavAdapter(private var datas: List<NaviBean>) : RecyclerView.Adapter<NavViewHolder>() {

    private val mFlexItemTextViewCaches = LinkedList<TextView>()
    private var mInflater: LayoutInflater? = null
    private var mOnClickItem: ((NaviBean, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavViewHolder {
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_tree, parent, false)
        return NavViewHolder(root)
    }

    override fun onBindViewHolder(holder: NavViewHolder, position: Int) {
        holder.mBinding.tvName.text = datas[position].name
        val articles = datas[position].articles
        if (!articles.isNullOrEmpty()) {
            for (i in articles.indices){
                val tv = creativeOrGetCacheTextView(holder.mBinding.fbl)
                tv.setOnClickListener {
                    mOnClickItem?.invoke(datas[position],i)
                }
                tv.text = articles[i].title
                holder.mBinding.fbl.addView(tv)
            }
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }


    fun setOnItemClickListener(listener:(NaviBean, Int) -> Unit){
        mOnClickItem = listener
    }


    fun setNewData(newDatas: List<NaviBean>?) {
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

class NavViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val mBinding: ItemAdapterTreeBinding by lazy {
        requireNotNull(DataBindingUtil.bind(view))
    }
}