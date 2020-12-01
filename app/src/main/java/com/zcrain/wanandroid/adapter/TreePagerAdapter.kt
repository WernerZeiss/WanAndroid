package com.zcrain.wanandroid.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zcrain.wanandroid.ui.tree.TreeChildFragment

/**
 * @author CWQ
 * @date 12/1/20
 */
class TreePagerAdapter(fragment: Fragment):FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return TreeChildFragment.newInstance(position)
    }

}