package com.zcrain.wanandroid.ui.qa

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zcrain.wanandroid.Constant
import com.zcrain.wanandroid.model.ArticleBean
import com.zcrain.wanandroid.model.ListResponse
import com.zcrain.wanandroid.net.Repository
import com.zcrain.wanandroid.net.doFailure
import com.zcrain.wanandroid.net.doSuccess
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * @author CWQ
 * @date 11/26/20
 */
class QAViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    private var mPage = 0

    val loading = MutableLiveData<Boolean>()

    val failure = MutableLiveData<String>()

    val wenDaDatas = MutableLiveData<ListResponse<ArticleBean>>()

    /**
     * 问答列表
     * @param refreshType 刷新方式
     */
    fun getWenDaData(refreshType: Int) = viewModelScope.launch {
        if (refreshType == Constant.REFRESH) {
            mPage = 0
        } else {
            mPage++
        }
        repository.getWenDaList(mPage)
            .onStart {
                loading.value = true
            }
            .catch {
                loading.value = false
            }
            .onCompletion {
                loading.value = false
            }
            .collectLatest { netResult ->
                netResult.doSuccess {
                    wenDaDatas.postValue(it)
                }
                netResult.doFailure {
                    failure.postValue(it?.msg)
                }
            }
    }
}