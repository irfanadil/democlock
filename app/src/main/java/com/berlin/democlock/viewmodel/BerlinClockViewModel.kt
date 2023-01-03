package com.berlin.democlock.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berlin.democlock.BerlinClock
import com.berlin.democlock.model.BerlinClockData

class BerlinClockViewModel(private val berlinClock: BerlinClock) : ViewModel() {

    private var _berlinClockTime = MutableLiveData<BerlinClockData>()
    val berlinClockTime: LiveData<BerlinClockData> = _berlinClockTime

    fun updateBerlinClockInitialState() {
        _berlinClockTime.postValue(BerlinClockData.initial())
    }

    fun updateBerlinClock(time: String) {
        val result = berlinClock.getBerlinClock(time)
        _berlinClockTime.postValue(result)
    }
}