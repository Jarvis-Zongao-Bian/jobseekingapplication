package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.dto.ComparisonSettingsDTO;
import edu.gatech.seclass.jobcompare6300.utils.AdjustmentComparisonTextWatcher;
import edu.gatech.seclass.jobcompare6300.utils.DBManager;

public class AdjustComparisonSettingsActivity extends AppCompatActivity {

    private EditText yearlySalaryWeightID;
    private EditText yearlyBonusWeightID;
    private EditText leaveTimeWeightID;
    private EditText sharesOfferedWeightID;
    private EditText houseBuyingProgramWeightID;
    private EditText wellnessFundWeightID;
    public static Button saveComparisonSettingButtonID;
    public static Button cancelComparisonSettingsChangesButtonID;
    public static boolean hasChanges = false;
    private static boolean inputErrors = false;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_comparison_settings);

        dbManager = new DBManager(this);
        dbManager.open();

        yearlySalaryWeightID = (EditText) findViewById(R.id.yearlySalaryWeightID);
        yearlyBonusWeightID = (EditText) findViewById(R.id.yearlyBonusWeightID);
        leaveTimeWeightID = (EditText) findViewById(R.id.leaveTimeWeightID);
        sharesOfferedWeightID = (EditText) findViewById(R.id.sharesOfferedWeightID);
        houseBuyingProgramWeightID = (EditText) findViewById(R.id.houseBuyingProgramWeightID);
        wellnessFundWeightID = (EditText) findViewById(R.id.wellnessFundWeightID);

        saveComparisonSettingButtonID = (Button) findViewById(R.id.saveComparisonSettingButtonID);
        cancelComparisonSettingsChangesButtonID = (Button) findViewById(R.id.cancelComparisonSettingsChangesButtonID);

        // Fetch values from DB - if nothing exist, DBManager will initialize the table with all equal weight (i.e. 1)
        this.loadWeightSettingsFromDB();

        // Add listeners to all input field to track whether input is changed or not
        yearlySalaryWeightID.addTextChangedListener(new AdjustmentComparisonTextWatcher(yearlySalaryWeightID));
        yearlyBonusWeightID.addTextChangedListener(new AdjustmentComparisonTextWatcher(yearlyBonusWeightID));
        leaveTimeWeightID.addTextChangedListener(new AdjustmentComparisonTextWatcher(leaveTimeWeightID));
        sharesOfferedWeightID.addTextChangedListener(new AdjustmentComparisonTextWatcher(sharesOfferedWeightID));
        houseBuyingProgramWeightID.addTextChangedListener(new AdjustmentComparisonTextWatcher(houseBuyingProgramWeightID));
        wellnessFundWeightID.addTextChangedListener(new AdjustmentComparisonTextWatcher(wellnessFundWeightID));

        // When there are no changes, disable save, cancel buttons otherwise they are enabled - enhancement
        saveComparisonSettingButtonID.setEnabled(hasChanges);
        cancelComparisonSettingsChangesButtonID.setEnabled(hasChanges);
    }

    public void saveComparisonSetting (View view) {
        if (view.getId() == R.id.saveComparisonSettingButtonID) {
            // validate inputs
            validateInput(yearlySalaryWeightID);
            validateInput(yearlyBonusWeightID);
            validateInput(leaveTimeWeightID);
            validateInput(sharesOfferedWeightID);
            validateInput(houseBuyingProgramWeightID);
            validateInput(wellnessFundWeightID);

            if (inputErrors) {
                saveComparisonSettingButtonID.setError("Fix Input errors first");
            } else {
                saveComparisonSettingButtonID.setError(null);
                // create DTO
                ComparisonSettingsDTO updatedComparisonSettings = new ComparisonSettingsDTO();
                updatedComparisonSettings.setAYS(Integer.parseInt(yearlySalaryWeightID.getText().toString()));
                updatedComparisonSettings.setAYB(Integer.parseInt(yearlyBonusWeightID.getText().toString()));
                updatedComparisonSettings.setLT(Integer.parseInt(leaveTimeWeightID.getText().toString()));
                updatedComparisonSettings.setCSO(Integer.parseInt(sharesOfferedWeightID.getText().toString()));
                updatedComparisonSettings.setHBP(Integer.parseInt(houseBuyingProgramWeightID.getText().toString()));
                updatedComparisonSettings.setWF(Integer.parseInt(wellnessFundWeightID.getText().toString()));

                // make a call to DB to save the settings
                if (dbManager.updateComparisonSettings(updatedComparisonSettings) == 1) {
                    // Disable save and cancel button after the changed info is saved to DB
                    Toast.makeText(getApplicationContext(), "Changes Saved!", Toast.LENGTH_SHORT).show();
                    this.disableSaveCancelBtn();
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void cancel (View view) {
        if (view.getId() == R.id.cancelComparisonSettingsChangesButtonID) {
            // cancel all UI changes (i.e. make a loadSettings() call to db)
            this.loadWeightSettingsFromDB();
            // Disable save and cancel button after the changed info is saved to DB
            Toast.makeText(getApplicationContext(), "All changes are deleted!", Toast.LENGTH_SHORT).show();
            this.disableSaveCancelBtn();
        }
    }

    public void returnToMainMenu (View view) {
        if (view.getId() == R.id.returnToMainMenuButtonID) {
            Intent intent = new Intent(AdjustComparisonSettingsActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void validateInput(EditText inputEditText) {
        String inputArg = inputEditText.getText().toString();
        if (inputArg.matches("") || (Integer.parseInt(inputArg) < 1)) {
            inputEditText.setError("Invalid value");
            inputErrors = true;
        } else {
            inputErrors = false;
        }
    }

    private void disableSaveCancelBtn() {
        this.saveComparisonSettingButtonID.setEnabled(false);
        this.cancelComparisonSettingsChangesButtonID.setEnabled(false);
    }

    private void loadWeightSettingsFromDB() {
        ComparisonSettingsDTO comparisonSettingsFromDB = dbManager.fetchComparisonSettings();
        yearlySalaryWeightID.setText(Integer.toString(comparisonSettingsFromDB.getAYS()));
        yearlyBonusWeightID.setText(Integer.toString(comparisonSettingsFromDB.getAYB()));
        leaveTimeWeightID.setText(Integer.toString(comparisonSettingsFromDB.getLT()));
        sharesOfferedWeightID.setText(Integer.toString(comparisonSettingsFromDB.getCSO()));
        houseBuyingProgramWeightID.setText(Integer.toString(comparisonSettingsFromDB.getHBP()));
        wellnessFundWeightID.setText(Integer.toString(comparisonSettingsFromDB.getWF()));
    }
}