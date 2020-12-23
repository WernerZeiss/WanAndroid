package com.zcrain.wanandroid.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zcrain.wanandroid.ui.home.HomeFragment
import com.zcrain.wanandroid.ui.mine.MineFragment
import com.zcrain.wanandroid.ui.qa.QAFragment
import com.zcrain.wanandroid.ui.tree.TreeFragment

/**
 * @author CWQ
 * @date 11/24/20
 */
class TabPagerAdapter(fm: FragmentManager,lifecycle: Lifecycle) :
    FragmentStateAdapter(fm,lifecycle) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> QAFragment()
            2 -> TreeFragment()
            else -> MineFragment()
        }
    }
}