package edu.gatech.seclass.jobcompare6300.utils;

import edu.gatech.seclass.jobcompare6300.dto.ComparisonSettingsDTO;
import edu.gatech.seclass.jobcompare6300.dto.JobDTO;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import edu.gatech.seclass.jobcompare6300.utils.DBManager;

import java.util.*;

public class RankJob {
    public RankJob () {}

    public int calculateJobWeight (JobDTO job, ComparisonSettingsDTO compareSetting) {
        int AYS = job.getYearlySalary();
        int AYB = job.getYearlyBonus();
        int LT = job.getLeaveTime();
        int CSO = job.getNumberOfStock();
        int HBP = job.getHomeBuyingFund();
        int WF = job.getWellnessFund();
        int AYS_weight= compareSetting.getAYS();
        int AYB_weight = compareSetting.getAYB();
        int LT_weight = compareSetting.getLT();
        int CSO_weight = compareSetting.getCSO();
        int HBP_weight = compareSetting.getHBP();
        int WF_weight = compareSetting.getWF();
        int totalWeight = AYB_weight + AYS_weight +LT_weight + CSO_weight + HBP_weight + WF_weight;
        int jobWeight = (AYS * AYS_weight + AYB * AYB_weight + LT_weight * (LT * AYS / 260) + CSO * CSO_weight + HBP * HBP_weight + WF * WF_weight) / totalWeight;
        return jobWeight;
    }


}
