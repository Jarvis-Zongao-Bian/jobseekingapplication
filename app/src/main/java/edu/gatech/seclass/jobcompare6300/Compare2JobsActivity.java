package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.seclass.jobcompare6300.dto.JobDTO;
import edu.gatech.seclass.jobcompare6300.utils.DBManager;

public class Compare2JobsActivity extends AppCompatActivity {
    private DBManager dbManager;
    private Button anotherCompare;
    private Button returnToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare2_jobs);
        int[] jobIDs = getIntent().getIntArrayExtra("2Jobs");

        dbManager = new DBManager(this);
        dbManager.open();
        JobDTO job_1 = dbManager.fetchJobOffer(String.valueOf(jobIDs[0]));
        TextView job1Title = findViewById(R.id.JobTitleID_1);
        TextView job1Company = findViewById(R.id.JobCompanyID_1);
        TextView job1City = findViewById(R.id.JobCityID_1);
        TextView job1State = findViewById(R.id.JobStateID_1);
        TextView job1AYS = findViewById(R.id.JobAYSID_1);
        TextView job1AYB = findViewById(R.id.JobAYBID_1);
        TextView job1LT = findViewById(R.id.JobLTID_1);
        TextView job1CSO = findViewById(R.id.JobCSOID_1);
        TextView job1HBP = findViewById(R.id.JobHBPID_1);
        TextView job1WF = findViewById(R.id.JobWFID_1);

        job1Title.setText(job_1.getTitle());
        job1Company.setText(job_1.getCompany());
        job1City.setText(job_1.getCity());
        job1State.setText(job_1.getState());
        job1AYS.setText(String.valueOf(job_1.getYearlySalary()));
        job1AYB.setText(String.valueOf(job_1.getYearlyBonus()));
        job1LT.setText(String.valueOf(job_1.getLeaveTime()));
        job1CSO.setText(String.valueOf(job_1.getNumberOfStock()));
        job1HBP.setText(String.valueOf(job_1.getHomeBuyingFund()));
        job1WF.setText(String.valueOf(job_1.getWellnessFund()));

        JobDTO job_2 = dbManager.fetchJobOffer(String.valueOf(jobIDs[1]));
        TextView job2Title = findViewById(R.id.JobTitleID_2);
        TextView job2Company = findViewById(R.id.JobCompanyID_2);
        TextView job2City = findViewById(R.id.JobCityID_2);
        TextView job2State = findViewById(R.id.JobStateID_2);
        TextView job2AYS = findViewById(R.id.JobAYSID_2);
        TextView job2AYB = findViewById(R.id.JobAYBID_2);
        TextView job2LT = findViewById(R.id.JobLTID_2);
        TextView job2CSO = findViewById(R.id.JobCSOID_2);
        TextView job2HBP = findViewById(R.id.JobHBPID_2);
        TextView job2WF = findViewById(R.id.JobWFID_2);

        job2Title.setText(job_2.getTitle());
        job2Company.setText(job_2.getCompany());
        job2City.setText(job_2.getCity());
        job2State.setText(job_2.getState());
        job2AYS.setText(String.valueOf(job_2.getYearlySalary()));
        job2AYB.setText(String.valueOf(job_2.getYearlyBonus()));
        job2LT.setText(String.valueOf(job_2.getLeaveTime()));
        job2CSO.setText(String.valueOf(job_2.getNumberOfStock()));
        job2HBP.setText(String.valueOf(job_2.getHomeBuyingFund()));
        job2WF.setText(String.valueOf(job_2.getWellnessFund()));

        anotherCompare = findViewById(R.id.anotherCompareButtonID);
        returnToMain = findViewById(R.id.compareTableReturnToMainID);

        anotherCompare.setOnClickListener(view -> {
            Intent gotoJobRanks = new Intent(Compare2JobsActivity.this, JobRankActivity.class);
            startActivity(gotoJobRanks);
        });

        returnToMain.setOnClickListener(view -> {
            Intent gotoMain = new Intent(Compare2JobsActivity.this, MainActivity.class);
            startActivity(gotoMain);
        });


    }
}