package com.zcrain.wanandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zcrain.wanandroid.R
import com.zcrain.wanandroid.adapter.TabPagerAdapter
import com.zcrain.wanandroid.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        val tabs = listOf("首页", "问答", "体系", "我的")
        val tabIcons = listOf(
            ContextCompat.getDrawable(this, R.mipmap.icon_bottom_home),
            ContextCompat.getDrawable(this, R.mipmap.icon_bottom_qa),
            ContextCompat.getDrawable(this, R.mipmap.icon_bottom_tree),
            ContextCompat.getDrawable(this, R.mipmap.icon_bottom_mine)
        )

        mBinding.mainVp.adapter = TabPagerAdapter(this)
        TabLayoutMediator(
            mBinding.mainTl, mBinding.mainVp, false, true
        ) { tab, position ->
            tab.text = tabs[position]
            tab.icon = tabIcons[position]
        }.attach()
    }
}