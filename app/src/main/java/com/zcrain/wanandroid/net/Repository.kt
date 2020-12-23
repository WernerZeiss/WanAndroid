package com.zcrain.wanandroid.net

import com.zcrain.wanandroid.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @author CWQ
 * @date 11/24/20
 */
class Repository @Inject constructor(private val apiService: APIService) {

    suspend fun getBannerData(): Flow<NetResult<List<BannerBean>>> {
        return flow {
            val result = apiService.getBanner()
            if (result.isSuccess()) {
                emit(NetResult.Success(result.data))
            } else {
                emit(NetResult.Failure(NetError(result.errorCode, result.errorMsg)))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getArticles(page: Int): Flow<NetResult<ListResponse<ArticleBean>>> {
        return flow {
            val result = apiService.getArticleList(page)
            if (result.isSuccess()) {
                emit(NetResult.Success(result.data))
            } else {
                emit(NetResult.Failure(NetError(result.errorCode, result.errorMsg)))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTopArticles(): Flow<NetResult<List<ArticleBean>>> {
        return flow {
            val result = apiService.getTopArticles()
            if (result.isSuccess()) {
                emit(NetResult.Success(result.data))
            } else {
                emit(NetResult.Failure(NetError(result.errorCode, result.errorMsg)))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getWenDaList(page: Int): Flow<NetResult<ListResponse<ArticleBean>>> {
        return flow {
            val result = apiService.getWenList(page)
            if (result.isSuccess()) {
                emit(NetResult.Success(result.data))
            } else {
                emit(NetResult.Failure(NetError(result.errorCode, result.errorMsg)))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTreeList(): Flow<NetResult<List<TreeBean>>> {
        return flow {
            val result = apiService.getTreeList()
            if (result.isSuccess()) {
                emit(NetResult.Success(result.data))
            } else {
                emit(NetResult.Failure(NetError(result.errorCode, result.errorMsg)))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNaviList(): Flow<NetResult<List<NaviBean>>> {
        return flow {
            val result = apiService.getNavigations()
            if (result.isSuccess()) {
                emit(NetResult.Success(result.data))
            } else {
                emit(NetResult.Failure(NetError(result.errorCode, result.errorMsg)))
            }
        }.flowOn(Dispatchers.IO)
    }
}