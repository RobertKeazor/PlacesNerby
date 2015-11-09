package assignment.example.lifesafe.com.placesnerby;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;


public class NerbyPlaceTest   extends ActivityInstrumentationTestCase2 <NerbyPlace>{
    public NerbyPlaceTest() {
        super(NerbyPlace.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void ActionBarInputTest() {
        onView(ViewMatchers.withId(R.id.action_settings)).perform(typeText("Test Coordinate"));
        onView(ViewMatchers.withId(R.id.action_settings)).perform(click());


    }
}
