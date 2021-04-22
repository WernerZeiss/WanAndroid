package com.zcrain.wanandroid.ui.tree

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zcrain.wanandroid.model.NaviBean
import com.zcrain.wanandroid.model.TreeBean
import com.zcrain.wanandroid.net.Repository
import com.zcrain.wanandroid.net.doFailure
import com.zcrain.wanandroid.net.doSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author CWQ
 * @date 12/1/20
 */
@HiltViewModel
class TreeChildViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    val treeDatas = MutableLiveData<List<TreeBean>>()

    val naviDatas = MutableLiveData<List<NaviBean>>()


    fun getTreeDatas() = viewModelScope.launch {
        repository.getTreeList()
            .onStart { }
            .onCompletion { }
            .catch { }
            .collectLatest { netResult ->
                netResult.doSuccess {
                    treeDatas.postValue(it)
                }
                netResult.doFailure {

                }
            }
    }


    fun getNaviDatas() = viewModelScope.launch {
        repository.getNaviList()
            .onStart { }
            .onCompletion { }
            .catch { }
            .collectLatest { netResult ->
                netResult.doSuccess {
                    naviDatas.postValue(it)
                }
                netResult.doFailure {

                }
            }
    }
}