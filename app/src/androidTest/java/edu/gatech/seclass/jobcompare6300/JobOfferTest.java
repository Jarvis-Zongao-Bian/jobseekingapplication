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
public class JobOfferTest{

    @Rule
    public ActivityScenarioRule<AddJobOfferActivity> JobAOfferctivityRule = new ActivityScenarioRule<>(AddJobOfferActivity.class);


    @Test
    public void CreateJobOffer() {
        onView(withId(R.id.titleID)).perform(clearText(), replaceText("Help desk"));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("IBM"));
        onView(withId(R.id.stateID)).perform(clearText(), replaceText("NC"));
        onView(withId(R.id.cityID)).perform(clearText(), replaceText("Durham"));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("45000"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("2000"));
        onView(withId(R.id.leaveTimeID)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.stockOptionSharesID)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.homeBuyingProgramFundID)).perform(clearText(), replaceText("1000"));
        onView(withId(R.id.wellnessFundID)).perform(clearText(), replaceText("200"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveOfferButtonID)).perform(click());
    }


    @Test
    public void IncorrectJobEntry1 () {
        onView(withId(R.id.titleID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("IBM"));
        onView(withId(R.id.stateID)).perform(clearText(), replaceText("NC"));
        onView(withId(R.id.cityID)).perform(clearText(), replaceText("Durham"));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("45000"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("2000"));
        onView(withId(R.id.leaveTimeID)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.stockOptionSharesID)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.homeBuyingProgramFundID)).perform(clearText(), replaceText("1000"));
        onView(withId(R.id.wellnessFundID)).perform(clearText(), replaceText("200"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveOfferButtonID)).perform(click());
        onView(withId(R.id.titleID)).check(matches(hasErrorText("Invalid value")));

    }

    @Test
    public void IncorrectHPFJobEntry () {
        onView(withId(R.id.titleID)).perform(clearText(), replaceText("Help desk"));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("IBM"));
        onView(withId(R.id.stateID)).perform(clearText(), replaceText("NC"));
        onView(withId(R.id.cityID)).perform(clearText(), replaceText("Durham"));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("45000"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("2000"));
        onView(withId(R.id.leaveTimeID)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.stockOptionSharesID)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.homeBuyingProgramFundID)).perform(clearText(), replaceText("7000"));
        onView(withId(R.id.wellnessFundID)).perform(clearText(), replaceText("200"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveOfferButtonID)).perform(click());
        onView(withId(R.id.homeBuyingProgramFundID)).check(matches(hasErrorText("Home Buying fund too high!")));
    }

    @Test
    public void IncorrectWFJobEntry () {
        onView(withId(R.id.titleID)).perform(clearText(), replaceText("Help desk"));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("IBM"));
        onView(withId(R.id.stateID)).perform(clearText(), replaceText("NC"));
        onView(withId(R.id.cityID)).perform(clearText(), replaceText("Durham"));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("45000"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("2000"));
        onView(withId(R.id.leaveTimeID)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.stockOptionSharesID)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.homeBuyingProgramFundID)).perform(clearText(), replaceText("7000"));
        onView(withId(R.id.wellnessFundID)).perform(clearText(), replaceText("6000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveOfferButtonID)).perform(click());
        onView(withId(R.id.wellnessFundID)).check(matches(hasErrorText("Either too high or too low value")));
    }

    @Test
    public void AllBlankJobEntry () {
        onView(withId(R.id.titleID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.stateID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.cityID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.leaveTimeID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.stockOptionSharesID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.homeBuyingProgramFundID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.wellnessFundID)).perform(clearText(), replaceText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveOfferButtonID)).perform(click());
        onView(withId(R.id.titleID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.companyID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.stateID)).check(matches(hasErrorText("Invalid State")));
        onView(withId(R.id.cityID)).check(matches(hasErrorText("Invalid City")));
        onView(withId(R.id.yearlySalaryID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.yearlyBonusID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.leaveTimeID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.stockOptionSharesID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.homeBuyingProgramFundID)).check(matches(hasErrorText("Invalid value")));
        onView(withId(R.id.wellnessFundID)).check(matches(hasErrorText("Invalid value")));
    }

    @Test
    public void CancelJobOffer() {
        onView(withId(R.id.titleID)).perform(clearText(), replaceText("Contractor"));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("IBM"));
        onView(withId(R.id.stateID)).perform(clearText(), replaceText("NY"));
        onView(withId(R.id.cityID)).perform(clearText(), replaceText("New York"));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("75000"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("3000"));
        onView(withId(R.id.leaveTimeID)).perform(clearText(), replaceText("10"));
        onView(withId(R.id.stockOptionSharesID)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.homeBuyingProgramFundID)).perform(clearText(), replaceText("5000"));
        onView(withId(R.id.wellnessFundID)).perform(clearText(), replaceText("500"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.cancelOfferButtonID)).perform(click());
    }

}
