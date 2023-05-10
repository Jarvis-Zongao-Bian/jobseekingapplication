package edu.gatech.seclass.jobcompare6300.dto;

import java.io.Serializable;

public class ComparisonSettingsDTO implements Serializable {
    private static final long serialVersionUID = -2359791580291726734L;

    private int AYS;
    private int AYB;
    private int LT;
    private int CSO;
    private int HBP;
    private int WF;

    public ComparisonSettingsDTO() {}

    public ComparisonSettingsDTO(int AYS, int AYB, int LT, int CSO, int HBP, int WF) {
        this.AYS = AYS;
        this.AYB = AYB;
        this.LT = LT;
        this.CSO = CSO;
        this.HBP = HBP;
        this.WF = WF;
    }

    public int getAYS() {
        return AYS;
    }

    public void setAYS(int AYS) {
        this.AYS = AYS;
    }

    public int getAYB() {
        return AYB;
    }

    public void setAYB(int AYB) {
        this.AYB = AYB;
    }

    public int getLT() {
        return LT;
    }

    public void setLT(int LT) {
        this.LT = LT;
    }

    public int getCSO() {
        return CSO;
    }

    public void setCSO(int CSO) {
        this.CSO = CSO;
    }

    public int getHBP() {
        return HBP;
    }

    public void setHBP(int HBP) {
        this.HBP = HBP;
    }

    public int getWF() {
        return WF;
    }

    public void setWF(int WF) {
        this.WF = WF;
    }
}
