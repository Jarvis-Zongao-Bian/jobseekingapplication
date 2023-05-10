package edu.gatech.seclass.jobcompare6300.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import edu.gatech.seclass.jobcompare6300.AdjustComparisonSettingsActivity;
import edu.gatech.seclass.jobcompare6300.R;

public class AdjustmentComparisonTextWatcher implements TextWatcher {
    private View view;
    public AdjustmentComparisonTextWatcher(View view) {
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
        String text = editable.toString();
        if (text != "") {
            switch (view.getId()) {
                case R.id.yearlySalaryWeightID:
                case R.id.yearlyBonusWeightID:
                case R.id.leaveTimeWeightID:
                case R.id.sharesOfferedWeightID:
                case R.id.houseBuyingProgramWeightID:
                case R.id.wellnessFundWeightID:
                    AdjustComparisonSettingsActivity.hasChanges = true;
                    AdjustComparisonSettingsActivity.saveComparisonSettingButtonID.setEnabled(true);
                    AdjustComparisonSettingsActivity.cancelComparisonSettingsChangesButtonID.setEnabled(true);
                    break;
            }

        }

    }
}
