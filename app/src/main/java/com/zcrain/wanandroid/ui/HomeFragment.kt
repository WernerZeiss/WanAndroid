package com.zcrain.wanandroid.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.youth.banner.indicator.CircleIndicator
import com.zcrain.wanandroid.R
import com.zcrain.wanandroid.adapter.HomeBannerAdapter
import com.zcrain.wanandroid.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author CWQ
 * @date 11/24/20
 */
class HomeFragment : Fragment() {

    lateinit var mBinding: FragmentHomeBinding

    private val mViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        mBinding.apply {
            mBinding.viewModel = mViewModel
            homeSrl.setOnRefreshListener {
                mViewModel.getBannerData()
            }
            homeSrl.setOnLoadMoreListener {
                it.finishLoadMore()
            }
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.bannerData.observe(viewLifecycleOwner, {
            mBinding.homeBanner.addBannerLifecycleObserver(this)
                .setAdapter(HomeBannerAdapter(it))
                .setIndicator(CircleIndicator(context))
        })

        mViewModel.failure.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        mViewModel.getBannerData()
    }
}