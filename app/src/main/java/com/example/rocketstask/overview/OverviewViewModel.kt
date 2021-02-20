package com.example.rocketstask.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rocketstask.model.Rocket
import com.example.rocketstask.network.RocketApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class OverviewViewModel : ViewModel() {
    enum class RocketApiStatus { LOADING, ERROR,DONE }


    private val _navigateToSelectedRocket = MutableLiveData<Rocket>()
    val navigateToSelectedRocket: LiveData<Rocket>
        get() = _navigateToSelectedRocket

    private val _navigateToWikipedia = MutableLiveData<Rocket>()
    val navigateToWikipedia: LiveData<Rocket>
        get() = _navigateToWikipedia


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private val _status = MutableLiveData<RocketApiStatus>()
    val status: LiveData<RocketApiStatus>
        get() = _status

    private val _rockets = MutableLiveData<List<Rocket>>()
    val rockets: LiveData<List<Rocket>>
        get() = _rockets


    init {
        getAllRockets()
    }


    private fun getAllRockets() {
        coroutineScope.launch {

            try {
                _status.value = RocketApiStatus.LOADING
                val getAll = RocketApi.retrofitService.getAll()
                _rockets.value = getAll.body()
                _status.value = RocketApiStatus.DONE

            } catch (e: Exception) {

                _status.value = RocketApiStatus.ERROR
                _rockets.value = ArrayList()
                println("*******************************************${e.message}")
            }

        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    fun displayRocketDetailsComplete() {
        _navigateToSelectedRocket.value = null
    }
    fun displayRocketDetails(rocket: Rocket) {
        _navigateToSelectedRocket.value = rocket
    }

    fun displayWebFragment(rocket: Rocket) {
        _navigateToWikipedia.value = rocket
    }

    fun displayWebFragmentComplete() {
        _navigateToWikipedia.value = null
    }

}
