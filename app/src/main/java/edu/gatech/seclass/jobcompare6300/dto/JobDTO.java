package edu.gatech.seclass.jobcompare6300.dto;

import java.io.Serializable;

public class JobDTO implements Serializable {
    private static final long serialVersionUID = -2359791580291726734L;

    private String title;
    private String company;
    private String city;
    private String state;
    private int yearlySalary;
    private int yearlyBonus;
    private int leaveTime;
    private int numberOfStock;
    private int homeBuyingFund;
    private int wellnessFund;
    private boolean currentJob;

    public JobDTO() {}

    public JobDTO(String title, String company, String city, String state, int yearlySalary, int yearlyBonus, int leaveTime, int numberOfStock, int homeBuyingFund, int wellnessFund, boolean currentJob) {
        this.title = title;
        this.company = company;
        this.city = city;
        this.state = state;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.leaveTime = leaveTime;
        this.numberOfStock = numberOfStock;
        this.homeBuyingFund = homeBuyingFund;
        this.wellnessFund = wellnessFund;
        this.currentJob = currentJob;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public int getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(int yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public int getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(int yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public int getNumberOfStock() {
        return numberOfStock;
    }

    public void setNumberOfStock(int numberOfStock) {
        this.numberOfStock = numberOfStock;
    }

    public int getHomeBuyingFund() {
        return homeBuyingFund;
    }

    public void setHomeBuyingFund(int homeBuyingFund) {
        this.homeBuyingFund = homeBuyingFund;
    }

    public int getWellnessFund() {
        return wellnessFund;
    }

    public void setWellnessFund(int wellnessFund) {
        this.wellnessFund = wellnessFund;
    }

    public boolean isCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        this.currentJob = currentJob;
    }
}
