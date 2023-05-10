package edu.gatech.seclass.jobcompare6300.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Information
    private static final String DB_NAME = "JOB_COMPARE_CS_6300.DB";
    private static final int DB_VERSION = 6;

    // Tables
    public static final String COMPARISON_SETTINGS_TABLE = "COMPARISON_SETTINGS";
    public static final String JOBS_TABLE = "JOBS";

    // Comparison settings table columns
    public static final String ID = "ID";
    public static final String AYS = "AYS";
    public static final String AYB = "AYB";
    public static final String LT = "LT";
    public static final String CSO = "CSO";
    public static final String HBP = "HBP";
    public static final String WF = "WF";

    // Jobs table columns
    public static final String JOB_ID = "ID";
    public static final String title = "TITLE";
    public static final String company = "COMPANY";
    public static final String city = "CITY";
    public static final String state = "STATE";
    public static final String yearlySalary = "YEARLY_SALARY";
    public static final String yearlyBonus = "YEARLY_BONUS";
    public static final String leaveTime = "LEAVE_TIME";
    public static final String numberOfStock = "NUMBER_OF_STOCK";
    public static final String homeBusyingFund = "HOME_BUYING_FUND";
    public static final String wellnessFund = "WELLNESS_FUND";
    public static final String currentJob = "CURRENT_JOB";


    private static final String CREATE_COMPARISON_SETTINGS_TABLE = "CREATE TABLE " + COMPARISON_SETTINGS_TABLE + "(" +
            ID + " INTEGER PRIMARY KEY, " +
            AYS + " INTEGER NOT NULL, " +
            AYB + " INTEGER NOT NULL, " +
            LT + " INTEGER NOT NULL, " +
            CSO + " INTEGER NOT NULL, " +
            HBP + " INTEGER NOT NULL, " +
            WF + " INTEGER NOT NULL " + ")";

    private static final String CREATE_JOBS_TABLE = "CREATE TABLE " + JOBS_TABLE + "(" +
            JOB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            title + " TEXT, " +
            company + " TEXT, " +
            city + " TEXT, " +
            state + " TEXT, " +
            yearlySalary + " INTEGER, " +
            yearlyBonus + " INTEGER, " +
            leaveTime + " INTEGER, " +
            numberOfStock + " INTEGER, " +
            homeBusyingFund + " INTEGER, " +
            wellnessFund + " INTEGER, " +
            currentJob + " INTEGER DEFAULT 0 " + ")";

    public DatabaseHelper (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create tables, if they do not exist
        sqLiteDatabase.execSQL(CREATE_COMPARISON_SETTINGS_TABLE);
        sqLiteDatabase.execSQL(CREATE_JOBS_TABLE);
    }

    @Override
    //public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + COMPARISON_SETTINGS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + JOBS_TABLE);
        onCreate(sqLiteDatabase);
    }
}
