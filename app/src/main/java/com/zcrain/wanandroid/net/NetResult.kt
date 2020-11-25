package com.zcrain.wanandroid.net

/**
 * @author CWQ
 * @date 11/25/20
 */
sealed class NetResult<out T> {

    data class Success<out T>(val value: T) : NetResult<T>()

    data class Failure(val error: NetError) : NetResult<Nothing>()
}

inline fun <reified T> NetResult<T>.doSuccess(success: (T) -> Unit) {
    if (this is NetResult.Success) {
        success(value)
    }
}

inline fun <reified T> NetResult<T>.doFailure(failure: (NetError?) -> Unit) {
    if (this is NetResult.Failure) {
        failure(error)
    }
}