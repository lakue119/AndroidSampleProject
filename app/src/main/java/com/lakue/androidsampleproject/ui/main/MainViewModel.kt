package com.lakue.androidsampleproject.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lakue.androidsampleproject.base.BaseViewModel
import com.lakue.androidsampleproject.remote.model.ResultPocket
import com.lakue.androidsampleproject.repository.remote.PocketRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pocketRepository: PocketRemoteDataSource
) : BaseViewModel() {

    val LIMIT_COUNT = 30

    //포켓몬 데이터
    private val _livePocketInfo = MutableLiveData<ArrayList<ResultPocket>>()
    val livePocketInfo: LiveData<ArrayList<ResultPocket>> = _livePocketInfo
    val listPocketInfo = ArrayList<ResultPocket>()
}