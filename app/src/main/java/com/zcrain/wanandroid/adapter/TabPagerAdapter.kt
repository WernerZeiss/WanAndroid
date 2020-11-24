package com.zcrain.wanandroid.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zcrain.wanandroid.ui.HomeFragment
import com.zcrain.wanandroid.ui.MineFragment
import com.zcrain.wanandroid.ui.QAFragment
import com.zcrain.wanandroid.ui.TreeFragment

/**
 * @author CWQ
 * @date 11/24/20
 */
class TabPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

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