package com.lakue.androidsampleproject.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lakue.androidsampleproject.base.BaseViewModel
import com.lakue.androidsampleproject.remote.model.ResultPocket
import com.lakue.androidsampleproject.repository.remote.PocketRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.max

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pocketRepository: PocketRemoteDataSource
) : BaseViewModel() {

    val LIMIT_COUNT = 30

    //포켓몬 데이터
    private val _livePocketInfo = MutableLiveData<List<ResultPocket>>()
    val livePocketInfo: LiveData<List<ResultPocket>> = _livePocketInfo
    val listPocketInfo = ArrayList<ResultPocket>()

    fun setPocketData(item: ResultPocket){
        listPocketInfo.add(item)
        _livePocketInfo.value = listPocketInfo
    }

    fun setPocketData(items: List<ResultPocket>){
        listPocketInfo.addAll(items)
        _livePocketInfo.value = listPocketInfo
    }

    fun fetchPocketList(limit:Int = LIMIT_COUNT, offset:Int){
        viewModelScope.launch {
            val response = pocketRepository.getPocketInfo(limit, offset)
            responseValidation(response)
        }
    }
}