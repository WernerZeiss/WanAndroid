package com.zcrain.wanandroid.model

/**
 * @author CWQ
 * @date 12/22/20
 */

data class TreeBean(
    val children: List<TreeBean>?,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)
