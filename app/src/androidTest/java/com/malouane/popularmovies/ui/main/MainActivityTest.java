package com.malouane.popularmovies.ui.main;

import android.support.test.rule.ActivityTestRule;

import com.malouane.popularmovies.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

@RunWith(MockitoJUnitRunner.class) public class MainActivityTest {

    @Rule public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Test public void launchMainActivity() throws Exception {
        mActivityTestRule.launchActivity(null);
        onView(withId(R.id.pb_loading)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerView_movies_list)).check(matches(not(isDisplayed())));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())));
        onView(withId(R.id.recyclerView_movies_list)).check(matches(isDisplayed()));
    }

}
