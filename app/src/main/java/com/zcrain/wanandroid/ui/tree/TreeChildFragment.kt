package com.zcrain.wanandroid.ui.tree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.zcrain.wanandroid.R
import com.zcrain.wanandroid.databinding.FragmentTreeChildBinding

/**
 * @author CWQ
 * @date 12/1/20
 */
class TreeChildFragment : Fragment() {

    private lateinit var mBinding: FragmentTreeChildBinding
    private val mViewModel: TreeViewModel by activityViewModels()
    private var mType = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tree_child, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.run {
            mType = getInt("type", 0)
        }
        mBinding.tvTest.text = mType.toString()
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