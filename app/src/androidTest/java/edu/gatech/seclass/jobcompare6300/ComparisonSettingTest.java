package edu.gatech.seclass.jobcompare6300;

import androidx.test.espresso.Espresso;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
public class ComparisonSettingTest{

    @Rule
    public ActivityScenarioRule<AdjustComparisonSettingsActivity> AdjustComparisonSettingsActivityRule = new ActivityScenarioRule<>(AdjustComparisonSettingsActivity.class);

    @Test
    public void testOnCreate() {
        onView(withId(R.id.yearlySalaryWeightID)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.yearlyBonusWeightID)).perform(clearText(), replaceText("3"));
        onView(withId(R.id.leaveTimeWeightID)).perform(clearText(), replaceText("3"));
        onView(withId(R.id.sharesOfferedWeightID)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.houseBuyingProgramWeightID)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.wellnessFundWeightID)).perform(clearText(), replaceText("2"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveComparisonSettingButtonID)).perform(click());
    }

    @Test
    public void testSaveComparisonSetting() {
        onView(withId(R.id.yearlySalaryWeightID)).check(matches(withText("2")));
        onView(withId(R.id.yearlyBonusWeightID)).check(matches(withText("3")));
        onView(withId(R.id.leaveTimeWeightID)).check(matches(withText("3")));
        onView(withId(R.id.sharesOfferedWeightID)).check(matches(withText("1")));
        onView(withId(R.id.houseBuyingProgramWeightID)).check(matches(withText("1")));
        onView(withId(R.id.wellnessFundWeightID)).check(matches(withText("2")));

    }

    @Test
    public void testinvalidvalue() {
        onView(withId(R.id.yearlySalaryWeightID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.yearlyBonusWeightID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.leaveTimeWeightID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.sharesOfferedWeightID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.houseBuyingProgramWeightID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.wellnessFundWeightID)).perform(clearText(), replaceText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveComparisonSettingButtonID)).perform(click());
        onView(withId(R.id.yearlySalaryWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.yearlyBonusWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.leaveTimeWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.sharesOfferedWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.houseBuyingProgramWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.wellnessFundWeightID)).check(matches(hasErrorText("Invalid value")));
    }

    @Test
    public void testinvalidvalue2() {
        onView(withId(R.id.yearlySalaryWeightID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.yearlyBonusWeightID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.leaveTimeWeightID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.sharesOfferedWeightID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.houseBuyingProgramWeightID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.wellnessFundWeightID)).perform(clearText(), replaceText("-1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveComparisonSettingButtonID)).perform(click());
        onView(withId(R.id.yearlySalaryWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.yearlyBonusWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.leaveTimeWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.sharesOfferedWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.houseBuyingProgramWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.wellnessFundWeightID)).check(matches(hasErrorText("Invalid value")));
    }

    @Test
    public void CancelComparisonSetup() {
        onView(withId(R.id.yearlySalaryWeightID)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.yearlyBonusWeightID)).perform(clearText(), replaceText("3"));
        onView(withId(R.id.leaveTimeWeightID)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.sharesOfferedWeightID)).perform(clearText(), replaceText("3"));
        onView(withId(R.id.houseBuyingProgramWeightID)).perform(clearText(), replaceText("4"));
        onView(withId(R.id.wellnessFundWeightID)).perform(clearText(), replaceText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.cancelComparisonSettingsChangesButtonID)).perform(click());
    }

    @Test
    public void ZeroComparisonSetting() {
        onView(withId(R.id.yearlySalaryWeightID)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.yearlyBonusWeightID)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.leaveTimeWeightID)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.sharesOfferedWeightID)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.houseBuyingProgramWeightID)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.wellnessFundWeightID)).perform(clearText(), replaceText("0"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveComparisonSettingButtonID)).perform(click());
        onView(withId(R.id.yearlySalaryWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.yearlyBonusWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.leaveTimeWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.sharesOfferedWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.houseBuyingProgramWeightID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.wellnessFundWeightID)).check(matches(hasErrorText("Invalid value")));
    }
}