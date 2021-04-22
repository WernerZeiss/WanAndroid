package com.zcrain.wanandroid.ui.tree

import androidx.lifecycle.ViewModel
import com.zcrain.wanandroid.net.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author CWQ
 * @date 12/23/20
 */
@HiltViewModel
class CategoryModel @Inject constructor(val repository: Repository) : ViewModel() {

}