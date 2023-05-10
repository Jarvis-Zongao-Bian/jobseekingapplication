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
public class CurrentJobTest {

    @Rule
    public ActivityScenarioRule<CurrentJobActivity> currentJobActivityRule = new ActivityScenarioRule<>(CurrentJobActivity.class);


    @Test
    public void CorrectCurrentJob1() {
        onView(withId(R.id.currentTitleID)).perform(clearText(), replaceText("SDM"));
        onView(withId(R.id.currentCompanyID)).perform(clearText(), replaceText("Amazon"));
        onView(withId(R.id.currentStateID)).perform(clearText(), replaceText("Washington"));
        onView(withId(R.id.currentCityID)).perform(clearText(), replaceText("Seattle"));
        onView(withId(R.id.currentYearlySalaryID)).perform(clearText(), replaceText("300000"));
        onView(withId(R.id.currentYearlyBonusID)).perform(clearText(), replaceText("2000"));
        onView(withId(R.id.currentLeaveTimeID)).perform(clearText(), replaceText("7"));
        onView(withId(R.id.currentStockOptionSharesID)).perform(clearText(), replaceText("5000"));
        onView(withId(R.id.currentHomeBuyingProgramFundID)).perform(clearText(), replaceText("15000"));
        onView(withId(R.id.currentWellnessFundID)).perform(clearText(), replaceText("2000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveCurrentJobButtonID)).perform(click());
    }

    @Test
    public void CheckSavedCurrentJob1 () {
        onView(withId(R.id.currentTitleID)).perform(clearText(), replaceText("SDM"));
        onView(withId(R.id.currentCompanyID)).perform(clearText(), replaceText("Amazon"));
        onView(withId(R.id.currentStateID)).perform(clearText(), replaceText("Washington"));
        onView(withId(R.id.currentCityID)).perform(clearText(), replaceText("Seattle"));
        onView(withId(R.id.currentYearlySalaryID)).perform(clearText(), replaceText("300000"));
        onView(withId(R.id.currentYearlyBonusID)).perform(clearText(), replaceText("2000"));
        onView(withId(R.id.currentLeaveTimeID)).perform(clearText(), replaceText("7"));
        onView(withId(R.id.currentStockOptionSharesID)).perform(clearText(), replaceText("5000"));
        onView(withId(R.id.currentHomeBuyingProgramFundID)).perform(clearText(), replaceText("15000"));
        onView(withId(R.id.currentWellnessFundID)).perform(clearText(), replaceText("2000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveCurrentJobButtonID)).perform(click());
        onView(withId(R.id.currentTitleID)).check(matches(withText("SDM")));
        onView(withId(R.id.currentCompanyID)).check(matches(withText("Amazon")));
        onView(withId(R.id.currentStateID)).check(matches(withText("Washington")));
        onView(withId(R.id.currentCityID)).check(matches(withText("Seattle")));
        onView(withId(R.id.currentYearlySalaryID)).check(matches(withText("300000")));
        onView(withId(R.id.currentYearlyBonusID)).check(matches(withText("2000")));
        onView(withId(R.id.currentLeaveTimeID)).check(matches(withText("7")));
        onView(withId(R.id.currentStockOptionSharesID)).check(matches(withText("5000")));
        onView(withId(R.id.currentHomeBuyingProgramFundID)).check(matches(withText("15000")));
        onView(withId(R.id.currentWellnessFundID)).check(matches(withText("2000")));
    }

    @Test
    public void EditCurrentJob1 () {
        onView(withId(R.id.currentTitleID)).perform(clearText(), replaceText("SDM"));
        onView(withId(R.id.currentCompanyID)).perform(clearText(), replaceText("Amazon"));
        onView(withId(R.id.currentStateID)).perform(clearText(), replaceText("Washington"));
        onView(withId(R.id.currentCityID)).perform(clearText(), replaceText("Seattle"));
        onView(withId(R.id.currentYearlySalaryID)).perform(clearText(), replaceText("400000"));
        onView(withId(R.id.currentYearlyBonusID)).perform(clearText(), replaceText("2000"));
        onView(withId(R.id.currentLeaveTimeID)).perform(clearText(), replaceText("7"));
        onView(withId(R.id.currentStockOptionSharesID)).perform(clearText(), replaceText("5000"));
        onView(withId(R.id.currentHomeBuyingProgramFundID)).perform(clearText(), replaceText("15000"));
        onView(withId(R.id.currentWellnessFundID)).perform(clearText(), replaceText("2000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveCurrentJobButtonID)).perform(click());
        onView(withId(R.id.currentTitleID)).check(matches(withText("SDM")));
        onView(withId(R.id.currentCompanyID)).check(matches(withText("Amazon")));
        onView(withId(R.id.currentStateID)).check(matches(withText("Washington")));
        onView(withId(R.id.currentCityID)).check(matches(withText("Seattle")));
        onView(withId(R.id.currentYearlySalaryID)).check(matches(withText("400000")));
        onView(withId(R.id.currentYearlyBonusID)).check(matches(withText("2000")));
        onView(withId(R.id.currentLeaveTimeID)).check(matches(withText("7")));
        onView(withId(R.id.currentStockOptionSharesID)).check(matches(withText("5000")));
        onView(withId(R.id.currentHomeBuyingProgramFundID)).check(matches(withText("15000")));
        onView(withId(R.id.currentWellnessFundID)).check(matches(withText("2000")));
    }

    @Test
    public void CancelCurrentJob1 () {
        onView(withId(R.id.currentTitleID)).perform(clearText(), replaceText("SDM"));
        onView(withId(R.id.currentCompanyID)).perform(clearText(), replaceText("Amazon"));
        onView(withId(R.id.currentStateID)).perform(clearText(), replaceText("Washington"));
        onView(withId(R.id.currentCityID)).perform(clearText(), replaceText("Seattle"));
        onView(withId(R.id.currentYearlySalaryID)).perform(clearText(), replaceText("400000"));
        onView(withId(R.id.currentYearlyBonusID)).perform(clearText(), replaceText("2000"));
        onView(withId(R.id.currentLeaveTimeID)).perform(clearText(), replaceText("7"));
        onView(withId(R.id.currentStockOptionSharesID)).perform(clearText(), replaceText("5000"));
        onView(withId(R.id.currentHomeBuyingProgramFundID)).perform(clearText(), replaceText("15000"));
        onView(withId(R.id.currentWellnessFundID)).perform(clearText(), replaceText("2000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveCurrentJobButtonID)).perform(click());
        onView(withId(R.id.currentYearlySalaryID)).perform(clearText(), replaceText("500000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.cancelCurrentJobButtonID)).perform(click());
    }

    @Test
    public void IncorrectCurrentJobEntry1 () {
        onView(withId(R.id.currentTitleID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentCompanyID)).perform(clearText(), replaceText("Amazon"));
        onView(withId(R.id.currentStateID)).perform(clearText(), replaceText("Washington"));
        onView(withId(R.id.currentCityID)).perform(clearText(), replaceText("Seattle"));
        onView(withId(R.id.currentYearlySalaryID)).perform(clearText(), replaceText("300000"));
        onView(withId(R.id.currentYearlyBonusID)).perform(clearText(), replaceText("2000"));
        onView(withId(R.id.currentLeaveTimeID)).perform(clearText(), replaceText("7"));
        onView(withId(R.id.currentStockOptionSharesID)).perform(clearText(), replaceText("5000"));
        onView(withId(R.id.currentHomeBuyingProgramFundID)).perform(clearText(), replaceText("15000"));
        onView(withId(R.id.currentWellnessFundID)).perform(clearText(), replaceText("2000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveCurrentJobButtonID)).perform(click());
        onView(withId(R.id.currentTitleID)).check(matches(hasErrorText("Invalid value")));
        //onView(withId(R.id.saveCurrentJobButtonID)).check(matches(hasErrorText("Fix Input errors first")));
    }

    @Test
    public void IncorrectHPFCurrentJobEntry () {
        onView(withId(R.id.currentTitleID)).perform(clearText(), replaceText("SDM"));
        onView(withId(R.id.currentCompanyID)).perform(clearText(), replaceText("Amazon"));
        onView(withId(R.id.currentStateID)).perform(clearText(), replaceText("Washington"));
        onView(withId(R.id.currentCityID)).perform(clearText(), replaceText("Seattle"));
        onView(withId(R.id.currentYearlySalaryID)).perform(clearText(), replaceText("300000"));
        onView(withId(R.id.currentYearlyBonusID)).perform(clearText(), replaceText("2000"));
        onView(withId(R.id.currentLeaveTimeID)).perform(clearText(), replaceText("7"));
        onView(withId(R.id.currentStockOptionSharesID)).perform(clearText(), replaceText("5000"));
        onView(withId(R.id.currentHomeBuyingProgramFundID)).perform(clearText(), replaceText("46000"));
        onView(withId(R.id.currentWellnessFundID)).perform(clearText(), replaceText("2000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveCurrentJobButtonID)).perform(click());
        onView(withId(R.id.currentHomeBuyingProgramFundID)).check(matches(hasErrorText("Home Buying fund too high!")));
    }

    @Test
    public void IncorrectWFCurrentJobEntry () {
        onView(withId(R.id.currentTitleID)).perform(clearText(), replaceText("SDET"));
        onView(withId(R.id.currentCompanyID)).perform(clearText(), replaceText("Amazon"));
        onView(withId(R.id.currentStateID)).perform(clearText(), replaceText("Washington"));
        onView(withId(R.id.currentCityID)).perform(clearText(), replaceText("Seattle"));
        onView(withId(R.id.currentYearlySalaryID)).perform(clearText(), replaceText("300000"));
        onView(withId(R.id.currentYearlyBonusID)).perform(clearText(), replaceText("2000"));
        onView(withId(R.id.currentLeaveTimeID)).perform(clearText(), replaceText("7"));
        onView(withId(R.id.currentStockOptionSharesID)).perform(clearText(), replaceText("5000"));
        onView(withId(R.id.currentHomeBuyingProgramFundID)).perform(clearText(), replaceText("45000"));
        onView(withId(R.id.currentWellnessFundID)).perform(clearText(), replaceText("6000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveCurrentJobButtonID)).perform(click());
        onView(withId(R.id.currentWellnessFundID)).check(matches(hasErrorText("Either too high or too low value")));
    }

    @Test
    public void AllBlankCurrentJobEntry () {
        onView(withId(R.id.currentTitleID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentCompanyID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentStateID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentCityID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentYearlySalaryID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentYearlyBonusID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentLeaveTimeID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentStockOptionSharesID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentHomeBuyingProgramFundID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentWellnessFundID)).perform(clearText(), replaceText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveCurrentJobButtonID)).perform(click());
        onView(withId(R.id.currentTitleID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.currentCompanyID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.currentStateID)).check(matches(hasErrorText("Invalid State")));
        onView(withId(R.id.currentCityID)).check(matches(hasErrorText("Invalid City")));
        onView(withId(R.id.currentYearlySalaryID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.currentYearlyBonusID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.currentLeaveTimeID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.currentStockOptionSharesID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.currentHomeBuyingProgramFundID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.currentWellnessFundID)).check(matches(hasErrorText("Invalid value")));
    }

}
