package com.zcrain.wanandroid.ui

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.viewModelScope
import com.zcrain.wanandroid.model.BannerBean
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

    val mLoading = ObservableBoolean()

    val failure = MutableLiveData<String>()

    val bannerData = MutableLiveData<List<BannerBean>>()


    fun getBannerData() = viewModelScope.launch {
        repository.getBannerData()
            .onStart {
                Log.e(TAG,"onStart")
                mLoading.set(true)
            }
            .catch {
                mLoading.set(false)
            }
            .onCompletion {
                Log.e(TAG,"onCompletion")
                mLoading.set(false)
            }
            .collectLatest { netResult ->
                Log.e(TAG,"collectLatest")
                netResult.doSuccess {
                    bannerData.postValue(it)
                }
                netResult.doFailure {
                    failure.postValue(it?.msg)
                }
            }
    }
}