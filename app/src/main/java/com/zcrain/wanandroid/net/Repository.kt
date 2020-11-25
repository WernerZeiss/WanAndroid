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

    suspend fun getBannerData(): Flow<NetResult<List<BannerBean>>> {
        return flow {
            val bannerResult = apiService.getBanner()
            if (bannerResult.errorCode == 0) {
                emit(NetResult.Success(bannerResult.data!!))
            } else {
                emit(NetResult.Failure(NetError(bannerResult.errorCode, bannerResult.errorMsg)))
            }
        }.flowOn(Dispatchers.IO)
    }
}