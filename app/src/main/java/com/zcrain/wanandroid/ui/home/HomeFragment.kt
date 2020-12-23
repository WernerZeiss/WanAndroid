package com.zcrain.wanandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.youth.banner.indicator.CircleIndicator
import com.zcrain.wanandroid.Constant
import com.zcrain.wanandroid.R
import com.zcrain.wanandroid.adapter.ArticlesAdapter
import com.zcrain.wanandroid.adapter.HomeBannerAdapter
import com.zcrain.wanandroid.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author CWQ
 * @date 11/24/20
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var mBinding: FragmentHomeBinding

    private val mViewModel: HomeViewModel by viewModels()

    private var mAdapter: ArticlesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        mBinding.apply {
            this.viewModel = mViewModel
            this.lifecycleOwner = this@HomeFragment
            homeSrl.setOnRefreshListener {
                mViewModel.getBannerData()
                mViewModel.getTopArticles()
                mViewModel.getArticles(Constant.REFRESH)
            }
            homeSrl.setOnLoadMoreListener {
                mViewModel.getArticles(Constant.LOAD_MORE)
            }
        }
        mViewModel.getBannerData()
        mViewModel.getTopArticles()
        mViewModel.getArticles(Constant.REFRESH)
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

        mViewModel.topArticleData.observe(viewLifecycleOwner, {
            if (mAdapter == null) {
                mAdapter = ArticlesAdapter()
                mBinding.homeRv.adapter = mAdapter
            }
            mAdapter?.setTopData(it!!)
        })

        mViewModel.articlesData.observe(viewLifecycleOwner, {
            if (it.datas != null) {
                if (mAdapter == null) {
                    mAdapter = ArticlesAdapter()
                    mBinding.homeRv.adapter = mAdapter
                    mAdapter?.setCommonData(it.datas)
                } else {
                    if (it.curPage == 1) {
                        mAdapter?.setCommonData(it.datas)
                    } else {
                        mAdapter?.addCommonData(it.datas)
                    }
                }
            }
        })
    }
}