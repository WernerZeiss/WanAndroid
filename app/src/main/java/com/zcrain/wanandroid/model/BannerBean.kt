package com.zcrain.wanandroid.model

/**
 * @author CWQ
 * @date 11/24/20
 */
data class BannerBean(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)
