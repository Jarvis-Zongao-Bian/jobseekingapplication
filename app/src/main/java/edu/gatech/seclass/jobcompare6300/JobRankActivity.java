package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import edu.gatech.seclass.jobcompare6300.utils.DBManager;

import java.util.*;

public class JobRankActivity extends AppCompatActivity {
    private DBManager dbManager;
    private TableLayout tableLayout;

    private Button returnToMain;
    private Button compareBtn;
    private List<Integer> jobToCompare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_rank);
        setTitle("Job Rank & Compare");
        tableLayout = (TableLayout)findViewById(R.id.jobRanksTableID);
        jobToCompare = new ArrayList<>();
        dbManager = new DBManager(this);
        dbManager.open();
        //OrderMap, key: job_ID, value: List <title, company, location, AYS, AYB, LT, CSO, HBP, WF, Score>
        Map<Integer, List<String>> rankedJobMap = dbManager.fetchAndRankJobs();

        for (Integer key : rankedJobMap.keySet()) {
            View tableRow = LayoutInflater.from(this).inflate(R.layout.job_rank_item, null, false);
            CheckBox checkbox = (CheckBox) tableRow.findViewById(R.id.checkBoxID);
            TextView rankJobTableTitle = (TextView) tableRow.findViewById(R.id.rankJobTableTitleID);
            TextView rankJobTableCompany = (TextView) tableRow.findViewById(R.id.compareTableTitleID);
            TextView rankJobTableScore = (TextView) tableRow.findViewById(R.id.rankJobTableScoreID);

            checkbox.setId(key);
            List<String> jobAttributes = rankedJobMap.get(key);
            int size = jobAttributes.size();

            rankJobTableTitle.setText(jobAttributes.get(0));
            rankJobTableCompany.setText(jobAttributes.get(1));
            rankJobTableScore.setText(jobAttributes.get(size -1));

            tableLayout.addView(tableRow);

            checkbox.setOnClickListener(view -> {
                CheckBox cb = (CheckBox) view;
                if (cb.isChecked()) {
                    jobToCompare.add(cb.getId());
                }
                if (!cb.isChecked()) {
                    jobToCompare.remove(Integer.valueOf(cb.getId()));
                }
                if (jobToCompare.size() == 2) {
                    compareBtn.setEnabled(true);
                }
            });
        }
        compareBtn = findViewById(R.id.compareBtnID);
        compareBtn.setEnabled(false);
        compareBtn.setOnClickListener(view -> {
            if (jobToCompare.size() > 2) {
                Toast.makeText(getApplicationContext(), "You can ONLY choose 2 to compare!", Toast.LENGTH_SHORT).show();
            }
            if (jobToCompare.size() < 2) {
                Toast.makeText(getApplicationContext(), "You need to choose 2 to compare!", Toast.LENGTH_SHORT).show();
            }
            if (jobToCompare.size() == 2) {
                Toast.makeText(getApplicationContext(), "COMPARING!", Toast.LENGTH_SHORT).show();
                Intent gotoCompare = new Intent(JobRankActivity.this, Compare2JobsActivity.class);
                gotoCompare.putExtra("2Jobs", new int[] {jobToCompare.get(0), jobToCompare.get(1)});
                startActivity(gotoCompare);
            }
        });

        returnToMain = findViewById(R.id.jobRankReturnToMainID);
        returnToMain.setOnClickListener(view -> {
            Intent gotoMain = new Intent(JobRankActivity.this, MainActivity.class);
            startActivity(gotoMain);
        });

    }


}