package com.zcrain.wanandroid.ui.tree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zcrain.wanandroid.R
import com.zcrain.wanandroid.adapter.NavAdapter
import com.zcrain.wanandroid.adapter.TreeAdapter
import com.zcrain.wanandroid.databinding.FragmentTreeChildBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author CWQ
 * @date 12/1/20
 */
@AndroidEntryPoint
class TreeChildFragment : Fragment() {

    private lateinit var mBinding: FragmentTreeChildBinding
    private val mChildViewModel: TreeChildViewModel by viewModels()
    private var mType = 0 //0体系 1导航
    private var mTreeAdapter: TreeAdapter? = null
    private var mNavAdapter: NavAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tree_child, container, false)
        mBinding.lifecycleOwner = this
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.run {
            mType = getInt("type", 0)
        }

        initView()
        loadData()
    }

    private fun initView() {
        mChildViewModel.treeDatas.observe(viewLifecycleOwner) {
            if (mTreeAdapter == null) {
                mTreeAdapter = TreeAdapter(it)
                mTreeAdapter?.setOnClickItemListener{ treeBean, position ->

                }
                mBinding.rv.adapter = mTreeAdapter
            } else {
                mTreeAdapter?.setNewData(it)
            }
        }

        mChildViewModel.naviDatas.observe(viewLifecycleOwner) {
            if (mNavAdapter == null) {
                mNavAdapter = NavAdapter(it)
                mNavAdapter?.setOnItemClickListener{naviBean, position ->

                }
                mBinding.rv.adapter = mNavAdapter
            } else {
                mNavAdapter?.setNewData(it)
            }
        }
    }


    private fun loadData() {
        if (mType == 0) {
            mChildViewModel.getTreeDatas()
        } else {
            mChildViewModel.getNaviDatas()
        }
    }


    companion object {
        /**
         * 创建实例
         * @param type Int 0体系 1导航
         * @return TreeChildFragment
         */
        fun newInstance(type: Int): TreeChildFragment {
            return TreeChildFragment().apply {
                this.arguments = Bundle().apply {
                    putInt("type", type)
                }
            }
        }
    }
}