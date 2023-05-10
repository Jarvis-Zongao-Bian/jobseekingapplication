package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.gatech.seclass.jobcompare6300.utils.DBManager;

public class MainActivity extends AppCompatActivity {

    private Button currentJobDetailsID;
    private Button enterJobOfferButtonID;
    private Button adjustCompareSettingsButtonID;
    private Button compareJobOffersButtonID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBManager dbManager = new DBManager(this);
        dbManager.open();
        currentJobDetailsID = (Button) findViewById(R.id.currentJobDetailsID);
        enterJobOfferButtonID = (Button) findViewById(R.id.enterJobOfferButtonID);
        adjustCompareSettingsButtonID = (Button) findViewById(R.id.adjustCompareSettingsButtonID);
        compareJobOffersButtonID = (Button) findViewById(R.id.compareJobOffersButtonID);
        long jobNum = dbManager.rowCount();
        if (jobNum < 2) compareJobOffersButtonID.setEnabled(false);
        else compareJobOffersButtonID.setEnabled(true);
    }

    public void currentJobDetailsHandler(View view) {
        if (view.getId() == R.id.currentJobDetailsID) {
            Intent gotoCurrentJob = new Intent(MainActivity.this, CurrentJobActivity.class);
            startActivity(gotoCurrentJob);
        }
    }

    public void enterJobOfferHandler(View view) {
        if (view.getId() == R.id.enterJobOfferButtonID) {
            Intent gotoJobOfferInput = new Intent(MainActivity.this, AddJobOfferActivity.class);
            startActivity(gotoJobOfferInput);
        }
    }

    public void adjustCompareSettingsHandler(View view) {
        if (view.getId() == R.id.adjustCompareSettingsButtonID) {
            Intent gotoCompareSettings = new Intent(MainActivity.this, AdjustComparisonSettingsActivity.class);
            startActivity(gotoCompareSettings);
        }
    }

    public void compareJobOffersHandler(View view) {
        if (view.getId() == R.id.compareJobOffersButtonID) {
            Intent gotoJobRanks = new Intent(MainActivity.this, JobRankActivity.class);
            startActivity(gotoJobRanks);

        }

    }

}