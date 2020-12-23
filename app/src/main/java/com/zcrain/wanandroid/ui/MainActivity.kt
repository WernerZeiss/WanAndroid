package com.zcrain.wanandroid.ui

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.zcrain.wanandroid.R
import com.zcrain.wanandroid.adapter.TabPagerAdapter
import com.zcrain.wanandroid.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val mModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
        printAllProcessName()
    }

    private fun initView() {
        val tabs = listOf("首页", "问答", "体系", "我的")
        val tabIcons = listOf(
            ContextCompat.getDrawable(this, R.mipmap.icon_bottom_home),
            ContextCompat.getDrawable(this, R.mipmap.icon_bottom_qa),
            ContextCompat.getDrawable(this, R.mipmap.icon_bottom_tree),
            ContextCompat.getDrawable(this, R.mipmap.icon_bottom_mine)
        )
        mBinding.mainVp.adapter = TabPagerAdapter(supportFragmentManager, lifecycle)
        mBinding.mainVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                mBinding.mainVp.isUserInputEnabled = position != 2
            }
        })

        TabLayoutMediator(
            mBinding.mainTl, mBinding.mainVp, false, true
        ) { tab, position ->
            tab.text = tabs[position]
            tab.icon = tabIcons[position]
        }.attach()

        mModel.currentMainIndex.observe(this) {
            mBinding.mainVp.currentItem = it
        }
    }


    private fun printAllProcessName() {
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningAppProcesses = am.runningAppProcesses
        for (element in runningAppProcesses){
            Log.e("Main","process name:${element.processName}")
        }
    }
}