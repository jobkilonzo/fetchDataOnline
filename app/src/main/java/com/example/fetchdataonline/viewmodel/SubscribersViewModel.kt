package com.example.fetchdataonline.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fetchdataonline.NodejsApi
import com.example.fetchdataonline.Subscribers
import com.example.fetchdataonline.SubscribersNodejsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.await
import java.util.*

class SubscribersViewModel : ViewModel() {

    private val _properties = MutableLiveData<List<Subscribers>>()

    val properties: LiveData<List<Subscribers>>
        get() = _properties

    private var viewmModelJob = Job()
    private var coroutineScope = CoroutineScope(viewmModelJob + Dispatchers.Main)

    init {
        getSubscribers()
    }

    private fun getSubscribers() {
        coroutineScope.launch {
            val getPropertiesDeferred = NodejsApi.subscribersApi.getSubscribers()
            try {
                val listResult =  getPropertiesDeferred.await()
                _properties.value = listResult
            } catch (e: Exception) {

                _properties.value = ArrayList()
            }
        }
    }
}