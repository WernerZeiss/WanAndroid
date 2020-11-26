package com.zcrain.wanandroid.ui.qa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.zcrain.wanandroid.Constant
import com.zcrain.wanandroid.R
import com.zcrain.wanandroid.adapter.ArticlesAdapter
import com.zcrain.wanandroid.databinding.FragmentQaBinding

/**
 * @author CWQ
 * @date 11/24/20
 */
class QAFragment : Fragment() {

    lateinit var mBinding: FragmentQaBinding
    private val mViewModel: QAViewModel by activityViewModels()
    private var mAdapter: ArticlesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_qa, container, false)
        mBinding.srlWenda.setOnRefreshListener {
            mViewModel.getWenDaData(Constant.REFRESH)
        }
        mBinding.srlWenda.setOnLoadMoreListener {
            mViewModel.getWenDaData(Constant.LOAD_MORE)
        }
        mViewModel.getWenDaData(Constant.REFRESH)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.wenDaDatas.observe(viewLifecycleOwner, {
            if (mAdapter == null) {
                mAdapter = ArticlesAdapter()
                mBinding.rvWenda.adapter = mAdapter
            }
            if (it.curPage == 0) {
                mAdapter?.setCommonData(it.datas)
            } else {
                mAdapter?.addCommonData(it.datas)
            }
        })

        mViewModel.failure.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }
}