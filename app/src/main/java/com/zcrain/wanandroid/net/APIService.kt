package com.zcrain.wanandroid.net

import com.zcrain.wanandroid.model.ArticleBean
import com.zcrain.wanandroid.model.BannerBean
import com.zcrain.wanandroid.model.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author CWQ
 * @date 11/24/20
 */
interface APIService {

    /**
     * 文章列表
     * @param page Int
     * @return BaseResponse<List<ArticleBean>>
     */
    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): BaseResponse<List<ArticleBean>>

    /**
     * banner
     */
    @GET("banner/json")
    suspend fun getBanner(): BaseResponse<List<BannerBean>>
}