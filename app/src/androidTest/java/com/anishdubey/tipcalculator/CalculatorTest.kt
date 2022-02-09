package com.anishdubey.tipcalculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTest {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip() {
        onView(withId(R.id.textInputEditText))
            .perform(typeText("50.00"))
        onView(withId(R.id.amazing))
            .perform(click())
        onView(withId(R.id.calculate)).perform(click())
        onView(withId(R.id.finalAmount))
            .check(matches(withText(containsString("10.00"))))
    }

    @Test
    fun calculate_18_percent_tip() {
        onView(withId(R.id.textInputEditText))
            .perform(typeText("50.00"))
        onView(withId(R.id.good))
            .perform(click())
        onView(withId(R.id.calculate))
            .perform(click())
        onView(withId(R.id.finalAmount))
            .check(matches(withText(containsString("7.50"))))
    }

    @Test
    fun calculate_15_percent_tip_round_up() {
        onView(withId(R.id.textInputEditText))
            .perform(typeText("50.00"))
        onView(withId(R.id.ok))
            .perform(click())
        onView(withId(R.id.switchButton))
            .perform(click())
        onView(withId(R.id.calculate))
            .perform(click())
        onView(withId(R.id.finalAmount))
            .check(matches(withText(containsString("6.00"))))
    }

    @Test
    fun calculate_15_percent_tip_no_rounding() {
        onView(withId(R.id.textInputEditText))
            .perform(typeText("50.00"))
        onView(withId(R.id.ok))
            .perform(click())
        onView(withId(R.id.calculate))
            .perform(click())
        onView(withId(R.id.finalAmount))
            .check(matches(withText(containsString("5.00"))))
    }

    @Test
    fun calculate_tip_no_rounding() {
        onView(withId(R.id.textInputEditText))
            .perform(typeText("50.00"))
        onView(withId(R.id.calculate))
            .perform(click())
        onView(withId(R.id.finalAmount))
            .check(matches(withText(containsString("0.50"))))
    }
}