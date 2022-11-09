package com.golden.spacextest.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.golden.spacextest.model.remote.CompanyResponse
import com.golden.spacextest.model.remote.LaunchRocket
import com.golden.spacextest.model.remote.SpacexNetwork
import kotlinx.coroutines.*

class SpacexViewModel: ViewModel() {
    private val _launches = MutableLiveData<List<LaunchRocket>>()
    val launches: LiveData<List<LaunchRocket>>
        get() = _launches

    private val _company = MutableLiveData<CompanyResponse>()
    val company: LiveData<CompanyResponse>
        get() = _company

    /**
     * Suspend function (async/non-blocking). Co-routine scope: container of co-routines, entry poimt fopr suspend functions;
     * Defines co-routine builders
     * A co-routing will be  suspended and resumed at co-routine scope
     * Scopes: Global, lifecycle, co-routine, viewmodel
     */

    //Coroutine context is an indexed set of threads used by the coroutines

    private val exHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    }

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Main + exHandler)

    /**
    Dispatchers control access to a threadpool; part of the Coroutine context
    Default: small ThreadPool to do backend operations (CPU-intensive)
    IO-Medium-to-large threadpool for input/output ops.
    Unconfined: Don't use in Android; it will jump between available threads
     **/

    private val currentDispatcher = Dispatchers.IO

    init {
        coroutineScope.launch {
            val companyResponse = SpacexNetwork.spacexAPI.getCompanyInfo()

            Log.d(TAG, "COMPANY: $companyResponse")
            
            if (companyResponse.isSuccessful)
                companyResponse.body()?.let {
                    _company.value = it
                }
            //async{}
        }

        coroutineScope.launch {
            val launches = async {
                val allLaunches = SpacexNetwork.spacexAPI.getAllLaunches()
                Log.d(TAG, "LAUNCHES: $allLaunches")
                allLaunches.docs.map { launchItem ->
                    val rocketInfo = SpacexNetwork.spacexAPI.getRocketInfo(
                        launchItem.rocketID
                    )
                    LaunchRocket(launchItem, rocketInfo)
                }
            }
            launches.await().also {
                Log.d(TAG, "Also:: $it")
                _launches.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel("ViewModel cleared")
    }
}