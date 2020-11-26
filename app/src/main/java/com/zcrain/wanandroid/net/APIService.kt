package com.zcrain.wanandroid.net

import com.zcrain.wanandroid.model.ArticleBean
import com.zcrain.wanandroid.model.BannerBean
import com.zcrain.wanandroid.model.BaseResponse
import com.zcrain.wanandroid.model.ListResponse
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
    suspend fun getArticleList(@Path("page") page: Int): BaseResponse<ListResponse<List<ArticleBean>>>

    /**
     * banner
     */
    @GET("banner/json")
    suspend fun getBanner(): BaseResponse<List<BannerBean>>

    /**
     * 置顶文章列表
     * @return BaseResponse<List<ArticleBean>>
     */
    @GET("article/top/json")
    suspend fun getTopArticles(): BaseResponse<List<ArticleBean>>

    /**
     * 问答列表
     * @param page Int
     * @return BaseResponse<ListResponse<List<ArticleBean>>>
     */
    @GET("wenda/list/{page}/json")
    suspend fun getWenList(@Path("page") page: Int): BaseResponse<ListResponse<List<ArticleBean>>>
}