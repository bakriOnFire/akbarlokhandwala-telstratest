package com.example.newstest

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.countryinfo.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
@LargeTest
class CountryInfoDataLoadTest {
    /**
     * To create and launch the activity under test before each test,
     * and close it after each test
     */
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    @Throws(Exception::class)
    fun pullToRefresh_shouldPass() {
        onView(withId(R.id.swipeContainer)).perform(swipeDown())
    }

    @Test
    @Throws(Exception::class)
    fun scroll_companyinfo_list() {
        var activity: Activity? = null
        activityScenarioRule.scenario.onActivity {
            activity = it
        }

        val nullableInt: Int? = activity?.findViewById<RecyclerView>(R.id.rv_country_info_list)?.adapter?.itemCount?.minus(1)
        if(nullableInt != null)
        {
            val position : Int = nullableInt
            onView(withId(R.id.rv_country_info_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
        }

    }
}