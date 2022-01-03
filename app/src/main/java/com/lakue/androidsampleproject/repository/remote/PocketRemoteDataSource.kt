package com.lakue.androidsampleproject.repository.remote

import com.lakue.androidsampleproject.remote.model.ResponsePocket
import com.lakue.androidsampleproject.remote.network.ResultWrapper

interface PocketRemoteDataSource {
    suspend fun getPocketInfo(limit: Int, offset: Int): ResultWrapper<ResponsePocket>
}