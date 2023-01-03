package com.berlin.democlock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.recyclerview.widget.GridLayoutManager
import com.berlin.democlock.adapter.FiveMinutesAdapter
import com.berlin.democlock.adapter.HoursAdapter
import com.berlin.democlock.adapter.OneMinuteAdapter
import com.berlin.democlock.databinding.ActivityBerlinClockBinding
import com.berlin.democlock.utils.LampColor
import com.berlin.democlock.utils.LampColor.*
import com.berlin.democlock.viewmodel.BerlinClockViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class BerlinClockActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBerlinClockBinding

    private val viewModel: BerlinClockViewModel by viewModel()

    private val oneMinuteAdapter: OneMinuteAdapter by lazy { OneMinuteAdapter() }
    private val fiveMinutesAdapter: FiveMinutesAdapter by lazy { FiveMinutesAdapter() }
    private val hoursAdapter: HoursAdapter by lazy { HoursAdapter() }

    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerlinClockBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()
        observeViewModel()
        startBerlinClock()
    }

    private fun observeViewModel() {
        viewModel.berlinClockTime.observe(this) {
            updateSecondsUI(it.secondsOnLamp)
            oneMinuteAdapter.setMinutesValue(it.minutesOnLamps.bottomColors)
            fiveMinutesAdapter.setMinutesValue(it.minutesOnLamps.topColors)
            hoursAdapter.setHoursValue(it.hoursOnLamps)
        }
    }

    private fun setRecyclerView() {
        binding.rvMinutesOfBottomLamp.apply {
            layoutManager = GridLayoutManager(this@BerlinClockActivity, SPAN_COUNT_FOUR)
            adapter = oneMinuteAdapter
        }
        binding.rvMinutesOfTopLamp.apply {
            layoutManager = GridLayoutManager(this@BerlinClockActivity, SPAN_COUNT_ELEVEN)
            adapter = fiveMinutesAdapter
        }
        binding.rvHoursLamp.apply {
            layoutManager = GridLayoutManager(this@BerlinClockActivity, SPAN_COUNT_FOUR)
            adapter = hoursAdapter
        }
        viewModel.updateBerlinClockInitialState()
    }

    private fun startBerlinClock() {
        timer = object : CountDownTimer(COUNT_DOWN_MILLIS, COUNT_DOWN_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) = updateBerlinClockUI()
            override fun onFinish() {
                start()
            }
        }
        timer.start()
    }

    private fun updateBerlinClockUI() {
        val currentTime: String = SimpleDateFormat(TIME_FORMAT, Locale.getDefault()).format(Date())
        binding.tvTime.text = currentTime
        viewModel.updateBerlinClock(currentTime)
    }

    private fun updateSecondsUI(secondsResult: LampColor) {
        binding.secondsLayout.isEnabled = secondsResult != OFF
    }

    companion object {
        const val SPAN_COUNT_FOUR = 4
        const val SPAN_COUNT_ELEVEN = 11
        const val COUNT_DOWN_MILLIS = 600000L
        const val COUNT_DOWN_INTERVAL = 1000L
        const val TIME_FORMAT = "HH:mm:ss"
    }
}