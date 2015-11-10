package assignment.example.lifesafe.com.placesnerby;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by rob2cool on 11/13/15.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {
@Rule

    public  ActivityTestRule<NerbyPlace> mActivity = new ActivityTestRule(NerbyPlace.class);
    @Test
    public void listGoesOverTheFold() {

        onView(withId(R.id.action_settings)).perform(typeText("New York"));
        onView(withId(R.id.action_settings)).perform(click());


        onView(withText("Loading from Google Webserice...")).check(matches(isDisplayed()));
    }
}
