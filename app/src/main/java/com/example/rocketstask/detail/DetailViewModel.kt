package com.example.rocketstask.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.rocketstask.model.Rocket
import com.example.rocketstask.network.RocketApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class DetailViewModel(@Suppress("UNUSED_PARAMETER") rocketId: String, app: Application)
    : AndroidViewModel(app) {
    enum class DetailApiStatus { LOADING, ERROR,DONE }


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _selectedRocket = MutableLiveData<Rocket>()
    val selectedRocket: LiveData<Rocket>
        get() = _selectedRocket


    private val _detailStatus = MutableLiveData<DetailApiStatus>()
    val detailStatus: LiveData<DetailApiStatus>
        get() = _detailStatus

    private val _string = MutableLiveData<String>()
    val string: LiveData<String>
        get() = _string




    init {

        getSelectedRocket(rocketId)

    }
    private fun getSelectedRocket(rocket_id:String){
        coroutineScope.launch {

            try {

                _detailStatus.value = DetailApiStatus.LOADING
                val getRocket = RocketApi.retrofitService.getRocketSelected(rocket_id)
                _selectedRocket.value = getRocket.body()
                _detailStatus.value =DetailApiStatus.DONE

                _string.value=" ${selectedRocket.value?.description}\n\n"+

                        "country: ${selectedRocket.value?.country}\n"+
                        "company: ${selectedRocket.value?.company}\n"+
                        "first flight: ${selectedRocket.value?.first_flight}\n"+
                        "rocket id: ${selectedRocket.value?.rocket_id}\n"+
                        "rocket name: ${selectedRocket.value?.rocket_name}\n"+
                        "boosters: ${selectedRocket.value?.boosters.toString()}\n"+
                        "active: ${selectedRocket.value?.active.toString()}\n"+
                        "cost per launch: ${selectedRocket.value?.cost_per_launch.toString()}\n"+
                        "stages: ${selectedRocket.value?.stages.toString()}\n"+
                        "success rate pct: ${selectedRocket.value?.success_rate_pct.toString()}\n\n"+

                        "diameter:\nfeet: ${selectedRocket.value?.diameter?.feet.toString()}"+
                        "meters:${selectedRocket.value?.diameter?.meters.toString()}"+

                        "engines:\nengine_loss_max ${selectedRocket.value?.engines?.engine_loss_max.toString()}\n"+
                        "layout: ${selectedRocket.value?.engines?.layout.toString()}\n"+
                        "number: ${selectedRocket.value?.engines?.number.toString()}\n"+
                        "propellant 1: ${selectedRocket.value?.engines?.propellant_1.toString()}\n"+
                        "propellant 2: ${selectedRocket.value?.engines?.propellant_2.toString()}\n"+
                        "thrust to weight: ${selectedRocket.value?.engines?.thrust_to_weight.toString()}\n"+
                        "type: ${selectedRocket.value?.engines?.type.toString()}\n"+
                        "version: ${selectedRocket.value?.engines?.version.toString()}\n\n"+

                        "first_stage:\nburn_time_sec: ${selectedRocket.value?.first_stage?.burn_time_sec.toString()}\n"+
                        "engines: ${selectedRocket.value?.first_stage?.engines.toString()}\n"+
                        "fuel_amount_tons: ${selectedRocket.value?.first_stage?.fuel_amount_tons.toString()}\n"+
                        "reusable: ${selectedRocket.value?.first_stage?.reusable.toString()}\n\n"+

                        "height:\n feet: ${selectedRocket.value?.height?.feet.toString()}\n"+
                        "meters: ${selectedRocket.value?.height?.meters.toString()}"





            } catch (e: Exception) {
                _detailStatus.value =DetailApiStatus.ERROR
                println("*******************************************${e.message}")
            }

        }
    }

    }

