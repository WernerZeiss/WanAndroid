package com.zcrain.wanandroid.ui.home

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zcrain.wanandroid.Constant
import com.zcrain.wanandroid.model.ArticleBean
import com.zcrain.wanandroid.model.BannerBean
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
 * @date 11/24/20
 */
class HomeViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    companion object {
        const val TAG = "HomeViewModel"
    }

    private var mPage = 0

    val mLoading = ObservableBoolean()

    val failure = MutableLiveData<String>()

    val bannerData = MutableLiveData<List<BannerBean>>()

    //首页文章列表
    val articlesData = MutableLiveData<ListResponse<ArticleBean>>()

    //置顶文章列表
    val topArticleData = MutableLiveData<List<ArticleBean>>()


    fun getBannerData() = viewModelScope.launch {
        repository.getBannerData()
            .onStart {
                mLoading.set(true)
            }
            .catch {
                mLoading.set(false)
            }
            .onCompletion {
                mLoading.set(false)
            }
            .collectLatest { netResult ->
                netResult.doSuccess {
                    bannerData.postValue(it)
                }
                netResult.doFailure {
                    failure.postValue(it?.msg)
                }
            }
    }

    /**
     * 置顶文章列表
     * @return Job
     */
    fun getTopArticles() = viewModelScope.launch {
        repository.getTopArticles()
            .onStart {
                mLoading.set(true)
            }
            .catch {
                mLoading.set(false)
            }
            .onCompletion {
                mLoading.set(false)
            }
            .collectLatest { netResult ->
                netResult.doSuccess {
                    topArticleData.postValue(it)
                }
                netResult.doFailure {
                    failure.postValue(it?.msg)
                }
            }
    }

    /**
     * 获取文章列表
     * @param refreshType Int
     * @return Job
     */
    fun getArticles(refreshType: Int) = viewModelScope.launch {
        if (refreshType == Constant.REFRESH) {
            mPage = 0
        } else {
            mPage++
        }
        repository.getArticles(mPage)
            .onStart {
                mLoading.set(true)
            }
            .catch {
                Log.e(TAG, "catch:$this")
                mLoading.set(false)
            }
            .onCompletion {
                mLoading.set(false)
            }
            .collectLatest { netResult ->
                netResult.doSuccess {
                    articlesData.postValue(it)
                }
                netResult.doFailure {
                    failure.postValue(it?.msg)
                }
            }
    }
}