package com.berlin.democlock.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.berlin.democlock.BerlinClock
import com.berlin.democlock.model.BerlinClockData
import com.berlin.democlock.model.Hours
import com.berlin.democlock.model.Minutes
import com.berlin.democlock.utils.LampColor.*
import com.berlin.democlock.utils.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BerlinClockViewModelTest {
    private val berlinClock = BerlinClock()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: BerlinClockViewModel

    @Before
    fun setUp() {
        viewModel = BerlinClockViewModel(berlinClock)
    }

    @Test
    fun livedata_should_return_initial_berlin_clock_data() {
        viewModel.updateBerlinClockInitialState()

        val value = viewModel.berlinClockTime.getOrAwaitValue()
        val expectedBerlinTime = BerlinClockData.initial()
        assertThat(value).isEqualTo(expectedBerlinTime)
    }

    @Test
    fun return_value_from_livedata_equals_expected_berlin_clock_data() {

        val time = "13:17:01"

        viewModel.updateBerlinClock(time)

        val value = viewModel.berlinClockTime.getOrAwaitValue()
        assertThat(value).isEqualTo(expectedBerlinClockData())
    }

    private fun expectedBerlinClockData(): BerlinClockData {
        val hoursOnTop = listOf(RED, RED, OFF, OFF)
        val hoursOnBottom = listOf(RED, RED, RED, OFF)
        val minutesOnTop = listOf(YELLOW, YELLOW, RED, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(YELLOW, YELLOW, OFF, OFF)
        return BerlinClockData(
            hoursOnLamps = Hours(topColors = hoursOnTop, bottomColors = hoursOnBottom),
            minutesOnLamps = Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom),
            secondsOnLamp = OFF
        )
    }
}