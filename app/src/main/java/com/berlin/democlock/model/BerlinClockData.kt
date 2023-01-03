package com.berlin.democlock.model

import com.berlin.democlock.utils.LampColor

data class BerlinClockData(
    val secondsOnLamp: LampColor,
    val minutesOnLamps: Minutes,
    val hoursOnLamps: Hours
) {
    companion object {
        fun initial() = BerlinClockData(LampColor.OFF, Minutes(), Hours())
    }
}