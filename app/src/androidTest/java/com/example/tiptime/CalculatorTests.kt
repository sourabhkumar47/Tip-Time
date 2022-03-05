package com.example.tiptime

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
class CalculatorTests {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_default_tip(){
        //Here we are taking input in textField
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("59.00"))

        //Turning of the round of the  button
        onView(withId(R.id.round_up_switch))
            .perform(click())

        //Here we are clicking on calculate button
        onView((withId(R.id.calculate_button)))
            .perform(click())

        //Checking the TextView to show expected value
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("11.80"))))
    }
}