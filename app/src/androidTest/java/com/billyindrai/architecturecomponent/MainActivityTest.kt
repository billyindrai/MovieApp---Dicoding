package com.billyindrai.architecturecomponent

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyMovie = Dummy.dummyMovies()
    private val dummyTvShow = Dummy.dummyTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovie() {
        Espresso.onView(withText("MOVIES")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rvTab))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvTab)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadTvShow() {
        Espresso.onView(withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rvTab))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvTab)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withText("MOVIES")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rvTab)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.iv_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_titleDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_titleDetail))
            .check(ViewAssertions.matches(withText(dummyMovie[0].title)))
        Espresso.onView(withId(R.id.tvRatingDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvRatingDetail))
            .check(ViewAssertions.matches(withText(dummyMovie[0].rating)))
        Espresso.onView(withId(R.id.tvDateDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvDateDetail))
            .check(ViewAssertions.matches(withText(dummyMovie[0].date)))
        Espresso.onView(withId(R.id.tvDurationDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvDurationDetail))
            .check(ViewAssertions.matches(withText(dummyMovie[0].duration)))
        Espresso.onView(withId(R.id.tvGenreDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvGenreDetail))
            .check(ViewAssertions.matches(withText(dummyMovie[0].genre)))
        Espresso.onView(withId(R.id.tvDescriptionsDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvDescriptionsDetail))
            .check(ViewAssertions.matches(withText(dummyMovie[0].description)))
    }

    @Test
    fun loadDetailTvShow() {
        Espresso.onView(withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rvTab)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.iv_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_titleDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_titleDetail))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].title)))
        Espresso.onView(withId(R.id.tvRatingDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvRatingDetail))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].rating)))
        Espresso.onView(withId(R.id.tvDateDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvDateDetail))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].date)))
        Espresso.onView(withId(R.id.tvDurationDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvDurationDetail))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].duration)))
        Espresso.onView(withId(R.id.tvGenreDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvGenreDetail))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].genre)))
        Espresso.onView(withId(R.id.tvDescriptionsDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvDescriptionsDetail))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].description)))
    }
}
