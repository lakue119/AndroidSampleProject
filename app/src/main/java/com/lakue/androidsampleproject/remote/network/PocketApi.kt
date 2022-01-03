package com.lakue.androidsampleproject.remote.network

import com.lakue.androidsampleproject.remote.model.ResponsePocket
import retrofit2.http.GET
import retrofit2.http.Query

interface PocketApi {

    @GET("/api/v2/pokemon")
    suspend fun getPocketInfo(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ResponsePocket

}