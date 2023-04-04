package com.project1.apparchitectureexercise1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val apiRetrofit = ApiRetrofit().dogApiServiceCallBack()

    private var _dogImage = MutableLiveData<Response<Data>>()

    val dogImage: LiveData<Response<Data>>
        get() = _dogImage

    fun getDogImageNetworkCall() {
        _dogImage.postValue(Response.Loading) //postvalue è =
        viewModelScope.launch {
            try {
                val response = apiRetrofit.getRandomDogImage()
                if (response.isSuccessful) {
                    val dogImage = response.body()
                    _dogImage.postValue(Response.Success(response.code(), dogImage))
                    Log.e("MainViewModel", "ok!: ${response.code()}")

                } else {
                    _dogImage.postValue(Response.Error(500, response.message()))
                    Log.e("MainViewModel", "Response not successful: ${response.code()}")
                }
            } catch (e: Exception) {
                _dogImage.postValue(Response.Error(500, "ci sono problemi"))
                Log.e("MainViewModel", "Error: ${e.message}")
            }
        }
    }
}