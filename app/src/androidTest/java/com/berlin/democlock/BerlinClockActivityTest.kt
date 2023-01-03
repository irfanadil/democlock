package com.berlin.democlock

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.berlin.democlock.model.BerlinClockData
import com.berlin.democlock.model.Hours
import com.berlin.democlock.model.Minutes
import com.berlin.democlock.utils.LampColor.*
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class BerlinClockActivityTest : KoinTest {

    private var activityScenarioRule: ActivityScenario<BerlinClockActivity>? = null

    @Before
    fun setUp() {

        MockKAnnotations.init(this, relaxUnitFun = true)

        val berlinClock = mockk<BerlinClock>()

        val expectedBerlinTime = expectedBerlinClockData()

        every { berlinClock.getBerlinClock(any()) } returns expectedBerlinTime

        loadKoinModules(
            listOf(
                module { single(override = true) { berlinClock } },
                viewModelModule
            )
        )
        activityScenarioRule = ActivityScenario.launch(BerlinClockActivity::class.java)
    }

    @Test
    fun check_if_all_views_are_visible() {

        onView(withId(R.id.seconds_layout)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_time)).check(matches((not(withText(" ")))))

        onView(withId(R.id.rv_minutes_of_top_lamp))
            .check(matches(hasChildCount(11)))

        onView(withId(R.id.rv_minutes_of_bottom_lamp))
            .check(matches(hasChildCount(4)))

        onView(withId(R.id.rv_hours_lamp))
            .check(matches(hasChildCount(8)))
    }

    @Test
    fun check_if_expected_lights_are_illuminated() {

        onView(withId(R.id.rv_minutes_of_top_lamp)).check(
            matches(
                hasItemAtPosition(
                    hasDescendant(
                        withText("")
                    ), 0
                )
            )
        ).check(matches(isEnabled()))

        onView(withId(R.id.rv_minutes_of_bottom_lamp)).check(
            matches(
                hasItemAtPosition(
                    hasDescendant(
                        withText("")
                    ), 1
                )
            )
        ).check(matches(isEnabled()))

        onView(withId(R.id.rv_hours_lamp)).check(
            matches(
                hasItemAtPosition(
                    hasDescendant(
                        withText("")
                    ), 4
                )
            )
        ).check(matches(isEnabled()))

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

    private fun hasItemAtPosition(matcher: Matcher<View?>, position: Int): Matcher<View?> {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                matcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                return matcher.matches(viewHolder!!.itemView)
            }
        }
    }
}