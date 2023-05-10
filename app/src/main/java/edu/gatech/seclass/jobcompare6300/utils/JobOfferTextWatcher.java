package edu.gatech.seclass.jobcompare6300.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import edu.gatech.seclass.jobcompare6300.AddJobOfferActivity;
import edu.gatech.seclass.jobcompare6300.R;

public class JobOfferTextWatcher implements TextWatcher {
    private View view;

    public JobOfferTextWatcher(View view) {
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
                case R.id.titleID:
                case R.id.companyID:
                case R.id.cityID:
                case R.id.stateID:
                case R.id.yearlySalaryID:
                case R.id.yearlyBonusID:
                case R.id.leaveTimeID:
                case R.id.stockOptionSharesID:
                case R.id.homeBuyingProgramFundID:
                case R.id.wellnessFundID:
                    AddJobOfferActivity.hasChanges = true;
                    AddJobOfferActivity.saveOfferButtonID.setEnabled(true);
                    break;
            }
        } else {
            AddJobOfferActivity.hasChanges = false;
            AddJobOfferActivity.saveOfferButtonID.setEnabled(false);
        }
    }
}
