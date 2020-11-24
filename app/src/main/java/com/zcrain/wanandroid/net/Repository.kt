package com.zcrain.wanandroid.net

import com.zcrain.wanandroid.model.BannerBean
import com.zcrain.wanandroid.model.BaseResponse
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

    suspend fun getBannerData(): Flow<BaseResponse<List<BannerBean>>> {
        return flow {
            val bannerResult = apiService.getBanner()
            emit(bannerResult)
        }.flowOn(Dispatchers.IO)
    }
}