package com.zcrain.wanandroid.ui.tree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.zcrain.wanandroid.R
import com.zcrain.wanandroid.adapter.TreePagerAdapter
import com.zcrain.wanandroid.databinding.FragmentTreeBinding

/**
 * @author CWQ
 * @date 11/24/20
 */
class TreeFragment : Fragment() {

    private lateinit var mBinding: FragmentTreeBinding
    private val tabs = listOf("体系", "导航")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tree, container, false)
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.treeVp2.adapter = TreePagerAdapter(this)
        TabLayoutMediator(mBinding.treeTl, mBinding.treeVp2) { tab, position ->
            tab.text = tabs[position]
        }.attach()
    }
}