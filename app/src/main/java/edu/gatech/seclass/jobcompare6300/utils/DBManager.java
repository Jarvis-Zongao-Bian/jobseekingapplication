package edu.gatech.seclass.jobcompare6300.utils;

import java.util.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import edu.gatech.seclass.jobcompare6300.dto.ComparisonSettingsDTO;
import edu.gatech.seclass.jobcompare6300.dto.JobDTO;

public class DBManager {

    private Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DBManager(Context context) {
        this.context = context;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public JobDTO fetchCurrentJob() {
        String[] columns = new String [] { DatabaseHelper.title, DatabaseHelper.company, DatabaseHelper.city, DatabaseHelper.state,
                DatabaseHelper.yearlySalary, DatabaseHelper.yearlyBonus, DatabaseHelper.leaveTime, DatabaseHelper.numberOfStock, DatabaseHelper.homeBusyingFund, DatabaseHelper.wellnessFund};
        Cursor cursor = database.query(DatabaseHelper.JOBS_TABLE, columns, "CURRENT_JOB=?", new String[] {"1"}, null, null, null);
        cursor.moveToFirst();

        return populateCurrentJobDto(cursor);
    }

    public long updateCurrentJob(JobDTO currentJob) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.currentJob, "1");
        contentValues.put(DatabaseHelper.title, currentJob.getTitle());
        contentValues.put(DatabaseHelper.company, currentJob.getCompany());
        contentValues.put(DatabaseHelper.city, currentJob.getCity());
        contentValues.put(DatabaseHelper.state, currentJob.getState());
        contentValues.put(DatabaseHelper.yearlySalary, Integer.toString(currentJob.getYearlySalary()));
        contentValues.put(DatabaseHelper.yearlyBonus, Integer.toString(currentJob.getYearlyBonus()));
        contentValues.put(DatabaseHelper.leaveTime, Integer.toString(currentJob.getLeaveTime()));
        contentValues.put(DatabaseHelper.numberOfStock, Integer.toString(currentJob.getNumberOfStock()));
        contentValues.put(DatabaseHelper.homeBusyingFund, Integer.toString(currentJob.getHomeBuyingFund()));
        contentValues.put(DatabaseHelper.wellnessFund, Integer.toString(currentJob.getWellnessFund()));

        // special case for the very first current job
        if (fetchCurrentJob().getYearlySalary() == -1) {
            return database.insert(DatabaseHelper.JOBS_TABLE, null, contentValues);
        }
        return database.update(DatabaseHelper.JOBS_TABLE, contentValues, DatabaseHelper.currentJob+ " = ?", new String[]{"1"});
    }

    // Note: This is there only for emergency cleanup
    public void deleteCurrentJob() {
        database.delete(DatabaseHelper.JOBS_TABLE, DatabaseHelper.currentJob + "=" + "1", null);
    }

    public long addJobOffer(JobDTO jobOffer) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.currentJob, "0");
        contentValues.put(DatabaseHelper.title, jobOffer.getTitle());
        contentValues.put(DatabaseHelper.company, jobOffer.getCompany());
        contentValues.put(DatabaseHelper.city, jobOffer.getCity());
        contentValues.put(DatabaseHelper.state, jobOffer.getState());
        contentValues.put(DatabaseHelper.yearlySalary, Integer.toString(jobOffer.getYearlySalary()));
        contentValues.put(DatabaseHelper.yearlyBonus, Integer.toString(jobOffer.getYearlyBonus()));
        contentValues.put(DatabaseHelper.leaveTime, Integer.toString(jobOffer.getLeaveTime()));
        contentValues.put(DatabaseHelper.numberOfStock, Integer.toString(jobOffer.getNumberOfStock()));
        contentValues.put(DatabaseHelper.homeBusyingFund, Integer.toString(jobOffer.getHomeBuyingFund()));
        contentValues.put(DatabaseHelper.wellnessFund, Integer.toString(jobOffer.getWellnessFund()));

        return database.insert(DatabaseHelper.JOBS_TABLE, null, contentValues);
    }

    public void initializeComparisonSettings() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ID, Integer.toString(1));
        contentValues.put(DatabaseHelper.AYS, Integer.toString(1));
        contentValues.put(DatabaseHelper.AYB, Integer.toString(1));
        contentValues.put(DatabaseHelper.LT, Integer.toString(1));
        contentValues.put(DatabaseHelper.CSO, Integer.toString(1));
        contentValues.put(DatabaseHelper.HBP, Integer.toString(1));
        contentValues.put(DatabaseHelper.WF, Integer.toString(1));
        database.insert(DatabaseHelper.COMPARISON_SETTINGS_TABLE, null, contentValues);
    }

    public ComparisonSettingsDTO fetchComparisonSettings() {
        String[] columns = new String[] { DatabaseHelper.AYS, DatabaseHelper.AYB, DatabaseHelper.LT, DatabaseHelper.CSO, DatabaseHelper.HBP, DatabaseHelper.WF };
        Cursor cursor = database.query(DatabaseHelper.COMPARISON_SETTINGS_TABLE, columns, "ID=?", new String[] {"1"}, null, null, null);
        if (cursor != null) {
            // Special case: First run, initialize comparison settings table
            if (cursor.getCount() == 0){
                initializeComparisonSettings();
                cursor = database.query(DatabaseHelper.COMPARISON_SETTINGS_TABLE, columns, "ID=?", new String[] {"1"}, null, null, null);
            }
            cursor.moveToFirst();
        }
        return populateComparisonSettingsDto(cursor);
    }

    public int updateComparisonSettings(ComparisonSettingsDTO comparisonSettings) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ID, "1");
        contentValues.put(DatabaseHelper.AYS, Integer.toString(comparisonSettings.getAYS()));
        contentValues.put(DatabaseHelper.AYB, Integer.toString(comparisonSettings.getAYB()));
        contentValues.put(DatabaseHelper.LT, Integer.toString(comparisonSettings.getLT()));
        contentValues.put(DatabaseHelper.CSO, Integer.toString(comparisonSettings.getCSO()));
        contentValues.put(DatabaseHelper.HBP, Integer.toString(comparisonSettings.getHBP()));
        contentValues.put(DatabaseHelper.WF, Integer.toString(comparisonSettings.getWF()));
        return database.update(DatabaseHelper.COMPARISON_SETTINGS_TABLE, contentValues, DatabaseHelper.ID+ " = 1", null);
    }

    // Note: This is there only for emergency cleanup
    public void deleteComparisonSettings() {
        database.delete(DatabaseHelper.COMPARISON_SETTINGS_TABLE, DatabaseHelper.ID + "=" + "1", null);
    }

    private ComparisonSettingsDTO populateComparisonSettingsDto(Cursor cursor) {
        ComparisonSettingsDTO comparisonSettings = new ComparisonSettingsDTO();
        try {
            comparisonSettings.setAYS(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.AYS)));
            comparisonSettings.setAYB(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.AYB)));
            comparisonSettings.setLT(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.LT)));
            comparisonSettings.setCSO(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.CSO)));
            comparisonSettings.setHBP(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.HBP)));
            comparisonSettings.setWF(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.WF)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comparisonSettings;
    }

    public int fetchCurrentJobID() {
        int currentJobID;
        String[] columns = new String [] { DatabaseHelper.JOB_ID};
        Cursor cursor = database.query(DatabaseHelper.JOBS_TABLE, columns, "CURRENT_JOB=?", new String[] {"1"}, null, null, null);
        cursor.moveToFirst();
        if (!(cursor.moveToFirst()) || cursor.getCount() == 0){
            return -1;
        }
        currentJobID = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.JOB_ID));
        return currentJobID;
    }
    private JobDTO populateCurrentJobDto(Cursor cursor) {
        JobDTO currentJob = new JobDTO();
        currentJob.setCurrentJob(true);
        if (!(cursor.moveToFirst()) || cursor.getCount() == 0){
            currentJob.setYearlySalary(-1);
        } else {
            try {
                currentJob.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.title)));
                currentJob.setCompany(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.company)));
                currentJob.setCity(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.city)));
                currentJob.setState(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.state)));
                currentJob.setYearlySalary(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.yearlySalary)));
                currentJob.setYearlyBonus(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.yearlyBonus)));
                currentJob.setLeaveTime(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.leaveTime)));
                currentJob.setNumberOfStock(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.numberOfStock)));
                currentJob.setHomeBuyingFund(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.homeBusyingFund)));
                currentJob.setWellnessFund(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.wellnessFund)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return currentJob;
    }
    public JobDTO fetchJobOffer (String job_ID) {
        JobDTO jobOffer = new JobDTO();
        String[] columns = new String [] { DatabaseHelper.title, DatabaseHelper.company, DatabaseHelper.city, DatabaseHelper.state,
                DatabaseHelper.yearlySalary, DatabaseHelper.yearlyBonus, DatabaseHelper.leaveTime, DatabaseHelper.numberOfStock, DatabaseHelper.homeBusyingFund, DatabaseHelper.wellnessFund, DatabaseHelper.currentJob};
        Cursor cursor = database.query(DatabaseHelper.JOBS_TABLE, columns, "ID=?", new String[]{job_ID}, null, null, null);
        cursor.moveToFirst();
        return populateJobOffer(cursor);
    }
    public JobDTO populateJobOffer (Cursor cursor) {
        JobDTO job = new JobDTO();
        try {
            job.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.title)));
            job.setCompany(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.company)));
            job.setCity(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.city)));
            job.setState(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.state)));
            job.setYearlySalary(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.yearlySalary)));
            job.setYearlyBonus(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.yearlyBonus)));
            job.setLeaveTime(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.leaveTime)));
            job.setNumberOfStock(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.numberOfStock)));
            job.setHomeBuyingFund(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.homeBusyingFund)));
            job.setWellnessFund(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.wellnessFund)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.currentJob)) == 1) {
            String currentTitle = job.getTitle();
            job.setTitle( currentTitle + " " + "(current)");
        }
        cursor.close();
        return job;
    }
    public Map<Integer, List<String>> fetchAndRankJobs () {
        String[] columns = new String [] { DatabaseHelper.JOB_ID, DatabaseHelper.title, DatabaseHelper.company, DatabaseHelper.city, DatabaseHelper.state,
                DatabaseHelper.yearlySalary, DatabaseHelper.yearlyBonus, DatabaseHelper.leaveTime, DatabaseHelper.numberOfStock, DatabaseHelper.homeBusyingFund, DatabaseHelper.wellnessFund, DatabaseHelper.currentJob};
        Cursor cursor = database.query(DatabaseHelper.JOBS_TABLE, columns, null, null, null, null, null);
        ComparisonSettingsDTO compareSetting = this.fetchComparisonSettings();
        RankJob rankJob = new RankJob();
        Map<Integer, List<String>> unRankedJobMap = new HashMap<>();
        PriorityQueue<int[]> rankJobQ = new PriorityQueue<>((a, b) -> (b[1] - a[1]));
        while (cursor.moveToNext()) {
            JobDTO job = new JobDTO();
            job.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.title)));
            job.setCompany(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.company)));
            job.setCity(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.city)));
            job.setState(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.state)));
            job.setYearlySalary(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.yearlySalary)));
            job.setYearlyBonus(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.yearlyBonus)));
            job.setLeaveTime(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.leaveTime)));
            job.setNumberOfStock(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.numberOfStock)));
            job.setHomeBuyingFund(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.homeBusyingFund)));
            job.setWellnessFund(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.wellnessFund)));
            //job_ID as identifier for sorting
            int job_ID = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.JOB_ID));
            int job_weight = rankJob.calculateJobWeight(job, compareSetting);
            rankJobQ.offer(new int[]{job_ID, job_weight});

            List<String> jobAttributes = new ArrayList<>();
            if (cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.currentJob)) == 1) {
                String currentTitle = job.getTitle();
                job.setTitle( currentTitle + " " + "(current)");
            }
            //jobAttributes.add(String.valueOf(job_ID));
            jobAttributes.add(job.getTitle());
            jobAttributes.add(job.getCompany());
            jobAttributes.add(job.getCity());
            jobAttributes.add(job.getState());
            jobAttributes.add(String.valueOf(job.getYearlySalary()));
            jobAttributes.add(String.valueOf(job.getYearlyBonus()));
            jobAttributes.add(String.valueOf(job.getLeaveTime()));
            jobAttributes.add(String.valueOf(job.getNumberOfStock()));
            jobAttributes.add(String.valueOf(job.getHomeBuyingFund()));
            jobAttributes.add(String.valueOf(job.getWellnessFund()));
            jobAttributes.add(String.valueOf(job_weight));
            unRankedJobMap.put(job_ID, jobAttributes);
        }
        cursor.close();
        Map<Integer, List<String>>  rankedJobMap = new LinkedHashMap<>();
        while (!rankJobQ.isEmpty()) {
            int[] cur = rankJobQ.poll();
            rankedJobMap.put(cur[0],unRankedJobMap.get(cur[0]));
        }
        return rankedJobMap;
    }

    public long rowCount() {
        long count = DatabaseUtils.queryNumEntries(database, DatabaseHelper.JOBS_TABLE);
        return count;
    }

}
