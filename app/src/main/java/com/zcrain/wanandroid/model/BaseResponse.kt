package com.zcrain.wanandroid.model

/**
 * @author CWQ
 * @date 11/24/20
 */
data class BaseResponse<T>(
    var errorCode: Int,
    var errorMsg: String = "",
    var data: T?
)
