package com.zcrain.wanandroid.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author CWQ
 * @date 12/21/20
 */
class MainViewModel : ViewModel() {

    val currentMainIndex = MutableLiveData<Int>()

    fun setCurrentMainIndex(index:Int){
        currentMainIndex.value = index
    }
}