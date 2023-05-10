package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.dto.JobDTO;
import edu.gatech.seclass.jobcompare6300.utils.CurrentJobTextWatcher;
import edu.gatech.seclass.jobcompare6300.utils.DBManager;

public class CurrentJobActivity extends AppCompatActivity {

    private EditText currentTitleID;
    private EditText currentCompanyID;
//    private EditText currentLocationID;
    private EditText currentStateID;
    private EditText currentCityID;
    private EditText currentYearlySalaryID;
    private EditText currentYearlyBonusID;
    private EditText currentLeaveTimeID;
    private EditText currentStockOptionSharesID;
    private EditText currentHomeBuyingProgramFundID;
    private EditText currentWellnessFundID;
    public static Button saveCurrentJobButtonID;
    public static Button cancelCurrentJobButtonID;

    public static boolean hasChanges = false;
    private static boolean inputErrors = false;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_job);
        hasChanges = false;
        dbManager = new DBManager(this);
        dbManager.open();

        currentTitleID = (EditText) findViewById(R.id.currentTitleID);
        currentCompanyID = (EditText) findViewById(R.id.currentCompanyID);
//        currentLocationID = (EditText) findViewById(R.id.currentLocationID);
        currentCityID = (EditText) findViewById(R.id.currentCityID);
        currentStateID = (EditText) findViewById(R.id.currentStateID);
        currentYearlySalaryID = (EditText) findViewById(R.id.currentYearlySalaryID);
        currentYearlyBonusID = (EditText) findViewById(R.id.currentYearlyBonusID);
        currentLeaveTimeID = (EditText) findViewById(R.id.currentLeaveTimeID);
        currentStockOptionSharesID = (EditText) findViewById(R.id.currentStockOptionSharesID);
        currentHomeBuyingProgramFundID = (EditText) findViewById(R.id.currentHomeBuyingProgramFundID);
        currentWellnessFundID = (EditText) findViewById(R.id.currentWellnessFundID);

        saveCurrentJobButtonID = (Button) findViewById(R.id.saveCurrentJobButtonID);
        cancelCurrentJobButtonID = (Button) findViewById(R.id.cancelCurrentJobButtonID);

        // Fetch current job from DB - if exists
        this.loadCurrentJobFromDB();

        // Add listeners to all input fields to track whether inputs are changed or not
        currentTitleID.addTextChangedListener(new CurrentJobTextWatcher(currentTitleID));
        currentCompanyID.addTextChangedListener(new CurrentJobTextWatcher(currentCompanyID));
//        currentLocationID.addTextChangedListener(new CurrentJobTextWatcher(currentLocationID));
        currentCityID.addTextChangedListener(new CurrentJobTextWatcher(currentCityID));
        currentStateID.addTextChangedListener(new CurrentJobTextWatcher(currentStateID));
        currentYearlySalaryID.addTextChangedListener(new CurrentJobTextWatcher(currentYearlySalaryID));
        currentYearlyBonusID.addTextChangedListener(new CurrentJobTextWatcher(currentYearlyBonusID));
        currentLeaveTimeID.addTextChangedListener(new CurrentJobTextWatcher(currentLeaveTimeID));
        currentStockOptionSharesID.addTextChangedListener(new CurrentJobTextWatcher(currentStockOptionSharesID));
        currentHomeBuyingProgramFundID.addTextChangedListener(new CurrentJobTextWatcher(currentHomeBuyingProgramFundID));
        currentWellnessFundID.addTextChangedListener(new CurrentJobTextWatcher(currentWellnessFundID));

        // When there are no changes to current job, disable save button
        saveCurrentJobButtonID.setEnabled(hasChanges);
    }

    public void saveJob (View view) {
        if (view.getId() == R.id.saveCurrentJobButtonID) {
            // Validate each input
            validateBasicTextInput(currentTitleID);
            validateBasicTextInput(currentCompanyID);
            validateCityInput(currentCityID);
            validateStateInput(currentStateID);
            validateIntegerInput(currentYearlySalaryID);
            validateIntegerInput(currentYearlyBonusID);
            validateLeaveTimeInput(currentLeaveTimeID);
            validateIntegerInput(currentStockOptionSharesID);
            validateHomeBuyingProgramFundInput(currentHomeBuyingProgramFundID, currentYearlySalaryID);
            validateWellnessFundInput(currentWellnessFundID);

            if (inputErrors) {
                saveCurrentJobButtonID.setError("Fix Input errors first");
            } else {
                saveCurrentJobButtonID.setError(null);
                // create DTO
                JobDTO currentJob = new JobDTO();
                currentJob.setCurrentJob(true);
                currentJob.setTitle(currentTitleID.getText().toString());
                currentJob.setCompany(currentCompanyID.getText().toString());
//                currentJob.setLocation(currentLocationID.getText().toString());
                currentJob.setCity(currentCityID.getText().toString());
                currentJob.setState(currentStateID.getText().toString());
                currentJob.setYearlySalary(Integer.parseInt(currentYearlySalaryID.getText().toString()));
                currentJob.setYearlyBonus(Integer.parseInt(currentYearlyBonusID.getText().toString()));
                currentJob.setLeaveTime(Integer.parseInt(currentLeaveTimeID.getText().toString()));
                currentJob.setNumberOfStock(Integer.parseInt(currentStockOptionSharesID.getText().toString()));
                currentJob.setHomeBuyingFund(Integer.parseInt(currentHomeBuyingProgramFundID.getText().toString()));
                currentJob.setWellnessFund(Integer.parseInt(currentWellnessFundID.getText().toString()));

                // Make a call to DB to save current job settings
                if (dbManager.updateCurrentJob(currentJob) != -1) {
                    Toast.makeText(getApplicationContext(), "Changes Saved!", Toast.LENGTH_SHORT).show();
                    // Redirect to main menu
                    this.redirectToMainMenu();
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    public void cancel (View view) {
        if (view.getId() == R.id.cancelCurrentJobButtonID) {
             // Redirect to main menu
            this.redirectToMainMenu();
        }
    }

    private void redirectToMainMenu() {
        Intent intent = new Intent(CurrentJobActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void loadCurrentJobFromDB() {
        JobDTO currentJobFromDB = dbManager.fetchCurrentJob();
        if (currentJobFromDB.getYearlySalary() == -1) {
            // no current job is setup till now
        } else {
            currentTitleID.setText(currentJobFromDB.getTitle());
            currentCompanyID.setText(currentJobFromDB.getCompany());
            currentCityID.setText(currentJobFromDB.getCity());
            currentStateID.setText(currentJobFromDB.getState());
            currentYearlySalaryID.setText(Integer.toString(currentJobFromDB.getYearlySalary()));
            currentYearlyBonusID.setText(Integer.toString(currentJobFromDB.getYearlyBonus()));
            currentLeaveTimeID.setText(Integer.toString(currentJobFromDB.getLeaveTime()));
            currentStockOptionSharesID.setText(Integer.toString(currentJobFromDB.getNumberOfStock()));
            currentHomeBuyingProgramFundID.setText(Integer.toString(currentJobFromDB.getHomeBuyingFund()));
            currentWellnessFundID.setText(Integer.toString(currentJobFromDB.getWellnessFund()));
        }
    }

    private void validateBasicTextInput(EditText inputEditText) {
        String inputArg = inputEditText.getText().toString();
        if (inputArg.matches("")) {
            inputEditText.setError("Invalid value");
            inputErrors = true;
        }
    }

//    private void validateLocationInput(EditText inputLocationText) {
//        String inputLocationArg = inputLocationText.getText().toString();
//        if (inputLocationArg.matches("")) {
//            inputLocationText.setError("Invalid Location");
//            inputErrors = true;
//        } else if (!inputLocationArg.matches("^,$")) {
//            inputLocationText.setError("City, State missing");
//            inputErrors = true;
//        }
//    }

    private void validateCityInput(EditText inputCityText) {
        String inputCityArg = inputCityText.getText().toString();
        if (inputCityArg.matches("")) {
            inputCityText.setError("Invalid City");
            inputErrors = true;
        } else if (!inputCityArg.matches("[ a-zA-Z]+")) {
            inputCityText.setError("City missing");
            inputErrors = true;
        }
    }

    private void validateStateInput(EditText inputStateText) {
        String inputStateArg = inputStateText.getText().toString();
        if (inputStateArg.matches("")) {
            inputStateText.setError("Invalid State");
            inputErrors = true;
        } else if (!inputStateArg.matches("[ a-zA-Z]+")) {
            inputStateText.setError("State missing");
            inputErrors = true;
        }
    }

    private void validateIntegerInput(EditText inputIntText) {
        String inputIntArg = inputIntText.getText().toString();
        if (inputIntArg.matches("")) {
            inputIntText.setError("Invalid value");
            inputErrors = true;
        } else if (Integer.parseInt(inputIntArg) < 0) {
            inputIntText.setError("Value can not be less than 0");
            inputErrors = true;
        }
    }

    private void validateLeaveTimeInput(EditText inputIntText) {
        String inputIntArg = inputIntText.getText().toString();
        if (inputIntArg.matches("")) {
            inputIntText.setError("Invalid value");
            inputErrors = true;
        } else if (Integer.parseInt(inputIntArg) > 365) {
            inputIntText.setError("Leave days can not be less than 365 days");
            inputErrors = true;
        } else if (Integer.parseInt(inputIntArg) < 0) {
            inputIntText.setError("Leave days can not be less than 0 days");
            inputErrors = true;
        }
    }

    private void validateWellnessFundInput(EditText wellnessFundText) {
        String wellnessFundString = wellnessFundText.getText().toString();
        if (wellnessFundString.matches("")) {
            wellnessFundText.setError("Invalid value");
            inputErrors = true;
        } else if ((Integer.parseInt(wellnessFundString) < 0) || (Integer.parseInt(wellnessFundString) > 5000 )) {
            wellnessFundText.setError("Either too high or too low value");
            inputErrors = true;
        }
    }

    private void validateHomeBuyingProgramFundInput (EditText homeBuyingProgramFundText, EditText yearlySalaryText) {
        String yearlySalary = yearlySalaryText.getText().toString();
        String homeBuyingFund = homeBuyingProgramFundText.getText().toString();
        if (yearlySalary.matches("") || homeBuyingFund.matches("")) {
            homeBuyingProgramFundText.setError("Invalid value");
            yearlySalaryText.setError("Invalid value");
            inputErrors = true;
        } else {
            int maxAllowedHomeBuyingFund = (int) (Integer.parseInt(yearlySalary) * 0.15);
            if (maxAllowedHomeBuyingFund <= Integer.parseInt(homeBuyingFund)) {
                homeBuyingProgramFundText.setError("Home Buying fund too high!");
                inputErrors = true;
            }
        }
    }

}