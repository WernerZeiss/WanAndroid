package com.zcrain.wanandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.zcrain.wanandroid.R
import com.zcrain.wanandroid.databinding.FragmentTreeBinding

/**
 * @author CWQ
 * @date 11/24/20
 */
class TreeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mBinding:FragmentTreeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tree,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}