package com.lakue.androidsampleproject.remote.impl

import com.lakue.androidsampleproject.remote.model.ResponsePocket
import com.lakue.androidsampleproject.remote.network.PocketApi
import com.lakue.androidsampleproject.remote.network.ResultWrapper
import com.lakue.androidsampleproject.repository.remote.PocketRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

class PocketRemoteDataSourceImpl @Inject constructor(
    private val pocketApi: PocketApi
): DataSourceImpl(), PocketRemoteDataSource {
    val Tag = PocketRemoteDataSourceImpl::class.java.name

    override suspend fun getPocketInfo(limit: Int, offset: Int): ResultWrapper<ResponsePocket> {
        return safeApiCall(Dispatchers.IO) {
            pocketApi.getPocketInfo(limit, offset)
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class PocketRemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindPocketRemoteDataSource(pocketRemoteDataSourceImpl: PocketRemoteDataSourceImpl): PocketRemoteDataSource

}
