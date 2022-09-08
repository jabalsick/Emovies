package com.blaja.core.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blaja.core.domain.Resource

open class BaseViewModel : ViewModel() {

    val mutableLiveData = MutableLiveData<Any?>()
    // for network
    val resultLiveData = MutableLiveData<Resource<Any?>?>()

    fun setValue(o: Any?) {
        mutableLiveData.value = o
    }

    fun postValue(o: Any?) {
        mutableLiveData.postValue(o)
    }

    fun setResult(o: Resource<Any?>?) {
        resultLiveData.value = o
    }

    fun postResult(o: Resource<Any?>?) {
        resultLiveData.postValue(o)
    }

}