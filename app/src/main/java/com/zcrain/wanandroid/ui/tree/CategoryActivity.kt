package com.zcrain.wanandroid.ui.tree

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zcrain.wanandroid.R
import com.zcrain.wanandroid.databinding.ActivityCategoryBinding
import com.zcrain.wanandroid.model.TreeBean
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author CWQ
 * @date 12/23/20
 * 体系分类
 */
@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() {

    private val mModel: CategoryModel by viewModels()
    private lateinit var mBinding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_category)
    }


    companion object {

        inline fun start(treeBean: TreeBean, position: Int) {

        }
    }
}