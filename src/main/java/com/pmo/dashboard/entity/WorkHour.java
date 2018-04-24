package com.pmo.dashboard.entity;

import java.math.BigDecimal;

public class WorkHour {
	protected String id;

    protected String month;

    protected String year;

    protected BigDecimal standardWorkday;

    protected BigDecimal standardWorkhour;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public BigDecimal getStandardWorkday() {
        return standardWorkday;
    }

    public void setStandardWorkday(BigDecimal standardWorkday) {
        this.standardWorkday = standardWorkday;
    }

    public BigDecimal getStandardWorkhour() {
        return standardWorkhour;
    }

    public void setStandardWorkhour(BigDecimal standardWorkhour) {
        this.standardWorkhour = standardWorkhour;
    }
}