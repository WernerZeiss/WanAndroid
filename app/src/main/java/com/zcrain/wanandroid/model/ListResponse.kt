package com.zcrain.wanandroid.model

/**
 * @author CWQ
 * @date 11/26/20
 */
data class ListResponse<T>(
    var curPage: Int,
    var datas: List<T>?,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)
