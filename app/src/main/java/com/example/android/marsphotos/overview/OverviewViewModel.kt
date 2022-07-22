package com.example.android.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.MarsApi
import com.example.android.marsphotos.network.MarsPhoto
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {


    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _photoUrl = MutableLiveData<String>()
    val photoUrl: LiveData<String> = _photoUrl

    var increment = 0

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getMarsPhotos() {
        viewModelScope.launch {
            shuffle()
        }
    }

    suspend fun shuffle() {

        if (increment == 22)
            increment = 0

        delay(3000)

        val listResult = MarsApi.retrofitService.getPhotos()
        _photoUrl.value = listResult[increment].imgSrcUrl
        _name.value = listResult[increment].name

        increment++
        shuffle()

    }
}