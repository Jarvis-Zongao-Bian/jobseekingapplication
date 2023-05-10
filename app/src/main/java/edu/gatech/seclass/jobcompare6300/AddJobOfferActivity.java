package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.dto.JobDTO;
import edu.gatech.seclass.jobcompare6300.utils.DBManager;
import edu.gatech.seclass.jobcompare6300.utils.JobOfferTextWatcher;

public class AddJobOfferActivity extends AppCompatActivity {

    private EditText titleID;
    private EditText companyID;
    //private EditText locationID;
    private EditText cityID;
    private EditText stateID;
    private EditText yearlySalaryID;
    private EditText yearlyBonusID;
    private EditText leaveTimeID;
    private EditText stockOptionSharesID;
    private EditText homeBuyingProgramFundID;
    private EditText wellnessFundID;

    public static Button saveOfferButtonID;
    protected static Button cancelOfferButtonID;

    public static boolean hasChanges = false;
    private static boolean inputErrors = false;

    private AlertDialog.Builder saveDialogBuilder;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job_offer);

        dbManager = new DBManager(this);
        dbManager.open();

        int currentJobID = dbManager.fetchCurrentJobID();

        titleID = (EditText) findViewById(R.id.titleID);
        companyID = (EditText) findViewById(R.id.companyID);
        //locationID = (EditText) findViewById(R.id.locationID);
        cityID = (EditText) findViewById(R.id.cityID);
        stateID = (EditText) findViewById(R.id.stateID);
        yearlySalaryID = (EditText) findViewById(R.id.yearlySalaryID);
        yearlyBonusID = (EditText) findViewById(R.id.yearlyBonusID);
        leaveTimeID = (EditText) findViewById(R.id.leaveTimeID);
        stockOptionSharesID = (EditText) findViewById(R.id.stockOptionSharesID);
        homeBuyingProgramFundID = (EditText) findViewById(R.id.homeBuyingProgramFundID);
        wellnessFundID = (EditText) findViewById(R.id.wellnessFundID);

        saveOfferButtonID = (Button) findViewById(R.id.saveOfferButtonID);
        cancelOfferButtonID = (Button) findViewById(R.id.cancelOfferButtonID);
        saveDialogBuilder = new AlertDialog.Builder(this);

        saveOfferButtonID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset input errors on each click
                inputErrors = false;
                validateBasicTextInput(titleID);
                validateBasicTextInput(companyID);
                //validateLocationInput(locationID);
                validateCityInput(cityID);
                validateStateInput(stateID);
                validateIntegerInput(yearlySalaryID);
                validateIntegerInput(yearlyBonusID);
                validateLeaveTimeInput(leaveTimeID);
                validateIntegerInput(stockOptionSharesID);
                validateHomeBuyingProgramFundInput(homeBuyingProgramFundID, yearlySalaryID);
                validateWellnessFundInput(wellnessFundID);

                if (inputErrors) {
                    saveOfferButtonID.setError("Fix Input errors first");
                } else {
                    saveOfferButtonID.setError(null);
                    // create DTO
                    JobDTO jobOffer = new JobDTO();
                    jobOffer.setCurrentJob(false);
                    jobOffer.setTitle(titleID.getText().toString());
                    jobOffer.setCompany(companyID.getText().toString());
                    //jobOffer.setLocation(locationID.getText().toString());
                    jobOffer.setCity(cityID.getText().toString());
                    jobOffer.setState(stateID.getText().toString());
                    jobOffer.setYearlySalary(Integer.parseInt(yearlySalaryID.getText().toString()));
                    jobOffer.setYearlyBonus(Integer.parseInt(yearlyBonusID.getText().toString()));
                    jobOffer.setLeaveTime(Integer.parseInt(leaveTimeID.getText().toString()));
                    jobOffer.setNumberOfStock(Integer.parseInt(stockOptionSharesID.getText().toString()));
                    jobOffer.setHomeBuyingFund(Integer.parseInt(homeBuyingProgramFundID.getText().toString()));
                    jobOffer.setWellnessFund(Integer.parseInt(wellnessFundID.getText().toString()));
                    // call to DB to save new job offer
                    long thisOfferrowID = dbManager.addJobOffer(jobOffer);
                    if (thisOfferrowID != -1) {
                        Toast.makeText(getApplicationContext(), "Successfully added new Job offer!", Toast.LENGTH_SHORT).show();
                        saveDialogBuilder.setMessage("Job saved successfully! Next Step..");
                        saveDialogBuilder.setMessage("Next Step...")
                                .setCancelable(false)
                                .setPositiveButton("Enter Another Offer", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // Reset UI for next job offer entry
                                        hasChanges = false;
                                        inputErrors = false;
                                        saveOfferButtonID.setEnabled(false);
                                        titleID.setText("");
                                        companyID.setText("");
                                        //locationID.setText("");
                                        cityID.setText("");
                                        stateID.setText("");
                                        yearlySalaryID.setText("");
                                        yearlyBonusID.setText("");
                                        leaveTimeID.setText("");
                                        stockOptionSharesID.setText("");
                                        homeBuyingProgramFundID.setText("");
                                        wellnessFundID.setText("");

                                        Toast.makeText(getApplicationContext(), "You can enter another job offer now..", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Return to Main Menu", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        returnToMainMenu();
                                    }
                                })
                                .setNeutralButton("Compare with Current Job Details", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (currentJobID == -1) {
                                            Toast.makeText(getApplicationContext(), "No current job entered yet!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Intent gotoJobRanks = new Intent(AddJobOfferActivity.this, Compare2JobsActivity.class);
                                            gotoJobRanks.putExtra("2Jobs", new int[] {currentJobID, (int)thisOfferrowID});
                                            startActivity(gotoJobRanks);
                                        }

                                    }
                                });

                        // Create dialog box
                        AlertDialog alert = saveDialogBuilder.create();
                        alert.setTitle("What do want to do next?");
                        alert.show();
                        Button compareWithCurrentJobBtn = alert.getButton(AlertDialog.BUTTON_NEUTRAL);
                        //compareWithCurrentJob is conditional - only when current job exists
                        if (currentJobID == -1) {
                            compareWithCurrentJobBtn.setEnabled(false);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Add listeners to all input fields to track whether job offer inputs are changed or not
        titleID.addTextChangedListener(new JobOfferTextWatcher(titleID));
        companyID.addTextChangedListener(new JobOfferTextWatcher(companyID));
        //locationID.addTextChangedListener(new JobOfferTextWatcher(locationID));
        cityID.addTextChangedListener(new JobOfferTextWatcher(cityID));
        stateID.addTextChangedListener(new JobOfferTextWatcher(stateID));
        yearlySalaryID.addTextChangedListener(new JobOfferTextWatcher(yearlySalaryID));
        yearlyBonusID.addTextChangedListener(new JobOfferTextWatcher(yearlyBonusID));
        leaveTimeID.addTextChangedListener(new JobOfferTextWatcher(leaveTimeID));
        stockOptionSharesID.addTextChangedListener(new JobOfferTextWatcher(stockOptionSharesID));
        homeBuyingProgramFundID.addTextChangedListener(new JobOfferTextWatcher(homeBuyingProgramFundID));
        wellnessFundID.addTextChangedListener(new JobOfferTextWatcher(wellnessFundID));

        // When there is no changes to Job offer screen, disable save button
        saveOfferButtonID.setEnabled(hasChanges);
    }

    public void cancelOfferHandler(View view) {
        if (view.getId() == R.id.cancelOfferButtonID) {
            // Redirect to main Menu
            this.returnToMainMenu();
        }
    }

    private void returnToMainMenu() {
        Intent intent = new Intent(AddJobOfferActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void validateBasicTextInput(EditText inputEditText) {
        String inputArg = inputEditText.getText().toString();
        if (inputArg.matches("")) {
            inputEditText.setError("Invalid value");
            inputErrors = true;
        }
    }

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