package edu.gatech.seclass.jobcompare6300.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import edu.gatech.seclass.jobcompare6300.CurrentJobActivity;
import edu.gatech.seclass.jobcompare6300.R;

public class CurrentJobTextWatcher implements TextWatcher {
    private View view;

    public CurrentJobTextWatcher(View view) {
        this.view = view;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        switch (view.getId()) {
            case R.id.currentTitleID:
            case R.id.currentCompanyID:
            case R.id.currentCityID:
            case R.id.currentStateID:
            case R.id.currentYearlySalaryID:
            case R.id.currentYearlyBonusID:
            case R.id.currentLeaveTimeID:
            case R.id.currentStockOptionSharesID:
            case R.id.currentHomeBuyingProgramFundID:
            case R.id.currentWellnessFundID:
                CurrentJobActivity.hasChanges = true;
                CurrentJobActivity.saveCurrentJobButtonID.setEnabled(true);
                break;
        }
    }
}
