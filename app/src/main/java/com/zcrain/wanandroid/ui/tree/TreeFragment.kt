package com.zcrain.wanandroid.ui.tree

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.zcrain.wanandroid.R
import com.zcrain.wanandroid.adapter.TreePagerAdapter
import com.zcrain.wanandroid.databinding.FragmentTreeBinding
import com.zcrain.wanandroid.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author CWQ
 * @date 11/24/20
 */
@AndroidEntryPoint
class TreeFragment : Fragment() {

    private val tabs = listOf("体系", "导航")
    private lateinit var mBinding: FragmentTreeBinding
    private lateinit var mMainModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tree, container, false)
        mBinding.lifecycleOwner = this
        mMainModel = ViewModelProvider {
            requireActivity().viewModelStore
        }.get(MainViewModel::class.java)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }


    private fun initView() {
        mBinding.treeVp2.adapter = TreePagerAdapter(childFragmentManager, lifecycle)
        mBinding.treeVp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            private var lastPosition = 0
            private var currentState = 0

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                Log.e("Tree", "state:$state")
                currentState = state
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (currentState == 1 && lastPosition == 1 && position == 1 && positionOffsetPixels == 0) {
                    mMainModel.setCurrentMainIndex(3)
                } else if (currentState == 1 && lastPosition == 0 && position == 0 && positionOffsetPixels == 0) {
                    mMainModel.setCurrentMainIndex(1)
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                lastPosition = position
            }
        })
        TabLayoutMediator(mBinding.treeTl, mBinding.treeVp2) { tab, position ->
            tab.text = tabs[position]
        }.attach()
    }
}