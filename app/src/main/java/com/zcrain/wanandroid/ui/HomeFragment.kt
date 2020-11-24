package com.zcrain.wanandroid.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.zcrain.wanandroid.R
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
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.bannerData.observe(viewLifecycleOwner, {
            Log.e("HomeFragment", "bannerData,size:${it.data?.size}")
        })

        mViewModel.getBannerData()
    }
}