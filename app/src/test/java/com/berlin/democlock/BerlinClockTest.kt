package com.berlin.democlock

import com.google.common.truth.Truth.assertThat
import com.berlin.democlock.model.BerlinClockData
import com.berlin.democlock.model.Hours
import com.berlin.democlock.model.Minutes
import com.berlin.democlock.utils.LampColor
import com.berlin.democlock.utils.LampColor.*
import org.junit.Test

class BerlinClockTest {

    private val berlinClock = BerlinClock()

    @Test
    fun test_0_second_should_return_yellow_lamp_color() {
        val result = berlinClock.getBerlinClock("00:00:00")

        val expectedResult = berlinClockForSeconds(YELLOW)
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_1_second_should_return_off_lamp_color() {
        val result = berlinClock.getBerlinClock("00:00:01")

        val expectedResult = berlinClockForSeconds(OFF)
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_2_seconds_should_return_yellow_lamp_color() {
        val result = berlinClock.getBerlinClock("00:00:02")

        val expectedResult = berlinClockForSeconds(YELLOW)
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_random_even_seconds_should_return_yellow() {
        val result = berlinClock.getBerlinClock("00:00:36")

        val expectedResult = berlinClockForSeconds(YELLOW)
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_random_odd_seconds_should_return_off() {
        val result = berlinClock.getBerlinClock("00:00:39")

        val expectedResult = berlinClockForSeconds(OFF)
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_0_minute() {
        val result = berlinClock.getBerlinClock("00:00:00")

        val minutesOnTop = listOf(OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(OFF, OFF, OFF, OFF)
        val expectedResult = berlinClockForMinutes(Minutes(minutesOnTop, minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_1_minute() {
        val result = berlinClock.getBerlinClock("00:01:00")

        val minutesOnTop = listOf(OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(YELLOW, OFF, OFF, OFF)
        val expectedResult = berlinClockForMinutes(Minutes(minutesOnTop, minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_2_minutes() {
        val result = berlinClock.getBerlinClock("00:02:00")

        val minutesOnTop = listOf(OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(YELLOW, YELLOW, OFF, OFF)
        val expectedResult =
            berlinClockForMinutes(Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_3_minutes() {
        val result = berlinClock.getBerlinClock("00:03:00")

        val minutesOnTop = listOf(OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(YELLOW, YELLOW, YELLOW, OFF)
        val expectedResult =
            berlinClockForMinutes(Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_4_minutes() {
        val result = berlinClock.getBerlinClock("00:04:00")

        val minutesOnTop = listOf(OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(YELLOW, YELLOW, YELLOW, YELLOW)
        val expectedResult =
            berlinClockForMinutes(Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_5_minutes() {
        val result = berlinClock.getBerlinClock("00:05:00")

        val minutesOnTop = listOf(YELLOW, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(OFF, OFF, OFF, OFF)
        val expectedResult =
            berlinClockForMinutes(Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_6_minutes() {
        val result = berlinClock.getBerlinClock("00:06:00")

        val minutesOnTop = listOf(YELLOW, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(YELLOW, OFF, OFF, OFF)
        val expectedResult =
            berlinClockForMinutes(Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_7_minutes() {
        val result = berlinClock.getBerlinClock("00:07:00")

        val minutesOnTop = listOf(YELLOW, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(YELLOW, YELLOW, OFF, OFF)
        val expectedResult =
            berlinClockForMinutes(Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_10_minutes() {
        val result = berlinClock.getBerlinClock("00:10:00")

        val minutesOnTop = listOf(YELLOW, YELLOW, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(OFF, OFF, OFF, OFF)
        val expectedResult =
            berlinClockForMinutes(Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_11_minutes() {
        val result = berlinClock.getBerlinClock("00:11:00")

        val minutesOnTop = listOf(YELLOW, YELLOW, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(YELLOW, OFF, OFF, OFF)
        val expectedResult =
            berlinClockForMinutes(Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_15_minutes() {
        val result = berlinClock.getBerlinClock("00:15:00")

        val minutesOnTop = listOf(YELLOW, YELLOW, RED, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(OFF, OFF, OFF, OFF)
        val expectedResult =
            berlinClockForMinutes(Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_17_minutes() {
        val result = berlinClock.getBerlinClock("00:17:00")

        val minutesOnTop = listOf(YELLOW, YELLOW, RED, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(YELLOW, YELLOW, OFF, OFF)
        val expectedResult =
            berlinClockForMinutes(Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_25_minutes() {
        val result = berlinClock.getBerlinClock("00:25:00")

        val minutesOnTop = listOf(YELLOW, YELLOW, RED, YELLOW, YELLOW, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(OFF, OFF, OFF, OFF)
        val expectedResult =
            berlinClockForMinutes(Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_30_minutes() {
        val result = berlinClock.getBerlinClock("00:30:00")

        val minutesOnTop = listOf(YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(OFF, OFF, OFF, OFF)
        val expectedResult =
            berlinClockForMinutes(Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_45_minutes() {
        val result = berlinClock.getBerlinClock("00:45:00")

        val minutesOnTop =
            listOf(YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, OFF, OFF)
        val minutesOnBottom = listOf(OFF, OFF, OFF, OFF)
        val expectedResult =
            berlinClockForMinutes(Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_59_minutes() {
        val result = berlinClock.getBerlinClock("00:59:00")

        val minutesOnTop =
            listOf(YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, YELLOW, YELLOW)
        val minutesOnBottom = listOf(YELLOW, YELLOW, YELLOW, YELLOW)
        val expectedResult =
            berlinClockForMinutes(Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_0_hour() {
        val result = berlinClock.getBerlinClock("00:00:00")

        val hoursOnBottom = listOf(OFF, OFF, OFF, OFF)
        val expectedResult = berlinClockForHours(Hours(bottomColors = hoursOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_1_hour() {
        val result = berlinClock.getBerlinClock("01:00:00")

        val hoursOnBottom = listOf(RED, OFF, OFF, OFF)
        val expectedResult = berlinClockForHours(Hours(bottomColors = hoursOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_2_hours() {
        val result = berlinClock.getBerlinClock("02:00:00")

        val hoursOnBottom = listOf(RED, RED, OFF, OFF)
        val expectedResult = berlinClockForHours(Hours(bottomColors = hoursOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_3_hours() {
        val result = berlinClock.getBerlinClock("03:00:00")

        val hoursOnBottom = listOf(RED, RED, RED, OFF)
        val expectedResult = berlinClockForHours(Hours(bottomColors = hoursOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_4_hours() {
        val result = berlinClock.getBerlinClock("04:00:00")

        val hoursOnBottom = listOf(RED, RED, RED, RED)
        val expectedResult = berlinClockForHours(Hours(bottomColors = hoursOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_5_hours() {
        val result = berlinClock.getBerlinClock("05:00:00")

        val hoursOnTop = listOf(RED, OFF, OFF, OFF)
        val hoursOnBottom = listOf(OFF, OFF, OFF, OFF)
        val expectedResult =
            berlinClockForHours(Hours(topColors = hoursOnTop, bottomColors = hoursOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_6_hours() {
        val result = berlinClock.getBerlinClock("06:00:00")

        val hoursOnTop = listOf(RED, OFF, OFF, OFF)
        val hoursOnBottom = listOf(RED, OFF, OFF, OFF)
        val expectedResult =
            berlinClockForHours(Hours(topColors = hoursOnTop, bottomColors = hoursOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_10_hours() {
        val result = berlinClock.getBerlinClock("10:00:00")

        val hoursOnTop = listOf(RED, RED, OFF, OFF)
        val hoursOnBottom = listOf(OFF, OFF, OFF, OFF)
        val expectedResult =
            berlinClockForHours(Hours(topColors = hoursOnTop, bottomColors = hoursOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_15_hours() {
        val result = berlinClock.getBerlinClock("15:00:00")

        val hoursOnTop = listOf(RED, RED, RED, OFF)
        val hoursOnBottom = listOf(OFF, OFF, OFF, OFF)
        val expectedResult =
            berlinClockForHours(Hours(topColors = hoursOnTop, bottomColors = hoursOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_23_hours() {
        val result = berlinClock.getBerlinClock("23:00:00")

        val hoursOnTop = listOf(RED, RED, RED, RED)
        val hoursOnBottom = listOf(RED, RED, RED, OFF)
        val expectedResult =
            berlinClockForHours(Hours(topColors = hoursOnTop, bottomColors = hoursOnBottom))
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_berlin_time_for_01_10_24() {
        val result = berlinClock.getBerlinClock("01:10:24")

        val hoursOnTop = listOf(OFF, OFF, OFF, OFF)
        val hoursOnBottom = listOf(RED, OFF, OFF, OFF)
        val minutesOnTop = listOf(YELLOW, YELLOW, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(OFF, OFF, OFF, OFF)
        val expectedResult = BerlinClockData(
            secondsOnLamp = YELLOW,
            minutesOnLamps = Minutes(minutesOnTop, minutesOnBottom),
            hoursOnLamps = Hours(hoursOnTop, hoursOnBottom)
        )
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_get_berlin_time_13_17_01() {
        val result = berlinClock.getBerlinClock("13:17:01")

        val hoursOnTop = listOf(RED, RED, OFF, OFF)
        val hoursOnBottom = listOf(RED, RED, RED, OFF)
        val minutesOnTop = listOf(YELLOW, YELLOW, RED, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(YELLOW, YELLOW, OFF, OFF)
        val expectedResult = BerlinClockData(
            hoursOnLamps = Hours(topColors = hoursOnTop, bottomColors = hoursOnBottom),
            minutesOnLamps = Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom),
            secondsOnLamp = OFF
        )
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_get_berlin_time_23_59_59() {
        val result = berlinClock.getBerlinClock("23:59:59")

        val hoursOnTop = listOf(RED, RED, RED, RED)
        val hoursOnBottom = listOf(RED, RED, RED, OFF)
        val minutesOnTop =
            listOf(YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, YELLOW, YELLOW)
        val minutesOnBottom = listOf(YELLOW, YELLOW, YELLOW, YELLOW)
        val expectedResult = BerlinClockData(
            hoursOnLamps = Hours(topColors = hoursOnTop, bottomColors = hoursOnBottom),
            minutesOnLamps = Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom),
            secondsOnLamp = OFF
        )
        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_get_berlin_time_24_hrs() {
        val result = berlinClock.getBerlinClock("24:00:00")

        val hoursOnTop = listOf(RED, RED, RED, RED)
        val hoursOnBottom = listOf(RED, RED, RED, RED)
        val minutesOnTop = listOf(OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(OFF, OFF, OFF, OFF)
        val expectedResult = BerlinClockData(
            hoursOnLamps = Hours(topColors = hoursOnTop, bottomColors = hoursOnBottom),
            minutesOnLamps = Minutes(topColors = minutesOnTop, bottomColors = minutesOnBottom),
            secondsOnLamp = YELLOW
        )
        assertThat(expectedResult).isEqualTo(result)
    }

    private fun berlinClockForSeconds(seconds: LampColor) =
        BerlinClockData(seconds, Minutes(), Hours())

    private fun berlinClockForMinutes(minutes: Minutes) =
        BerlinClockData(
            minutesOnLamps = minutes,
            secondsOnLamp = YELLOW,
            hoursOnLamps = Hours()
        )

    private fun berlinClockForHours(hours: Hours) =
        BerlinClockData(
            secondsOnLamp = YELLOW,
            minutesOnLamps = Minutes(),
            hoursOnLamps = hours
        )
}