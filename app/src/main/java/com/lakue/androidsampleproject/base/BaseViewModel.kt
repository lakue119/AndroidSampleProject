package com.lakue.androidsampleproject.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lakue.androidsampleproject.R
import com.lakue.androidsampleproject.remote.network.ResultWrapper
import com.lakue.androidsampleproject.utils.BaseUtils.context
import com.lakue.androidsampleproject.utils.EventLiveData

open class BaseViewModel : ViewModel() {

    private val _liveLoading = MutableLiveData(false)
    val liveLoading: LiveData<Boolean> get() = _liveLoading

    protected val _liveNewWorkErrorDialog = MutableLiveData("")
    val liveNewWorkErrorDialog: LiveData<String> get() = _liveNewWorkErrorDialog

    protected val _liveSuccess = MutableLiveData<Any>()
    val liveSuccess: LiveData<Any> get() = _liveSuccess

    protected val _liveError = MutableLiveData<Any?>()
    val liveError: LiveData<Any?> get() = _liveError

    val liveToast = EventLiveData<CharSequence>()

    fun showLoading() {
        _liveLoading.value = true
    }

    fun hideLoading() {
        _liveLoading.value = false
    }

    fun showToast(message: CharSequence) {
        liveToast.setEventValue(message)
    }

    fun resetValue(){
        _liveLoading.postValue(null)
        _liveNewWorkErrorDialog.postValue(null)
        _liveSuccess.postValue(null)
        _liveError.postValue(null)
    }

    fun responseValidation(response: Any): Boolean{
        when(response){
            is ResultWrapper.NetworkError -> {
                _liveNewWorkErrorDialog.postValue(context.getString(R.string.dialog_network_error))
            }
            is ResultWrapper.Success<*> -> {
                _liveSuccess.postValue(response.value)
                return true
            }
            is ResultWrapper.TimeOutError -> {
                _liveNewWorkErrorDialog.postValue(context.getString(R.string.dialog_timeout_error))
            }
            is ResultWrapper.ServerError -> {
                _liveNewWorkErrorDialog.postValue(context.getString(R.string.dialog_server_error))
            }
            is ResultWrapper.GenericError -> {
                if (response.error == null) {
                    _liveNewWorkErrorDialog.postValue(context.getString(R.string.dialog_server_error))
                } else {
                    _liveError.postValue(response.error)
                }
            }
        }

        return false
    }

}