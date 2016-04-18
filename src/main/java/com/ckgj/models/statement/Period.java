package com.ckgj.models.statement;

import java.util.*;

/**
 * Created by calvin on 4/18/16.
 */
public class Period {
    private int year;
    private int month;

    public Period(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public Period(String sqlStr) {
        String[] strs = sqlStr.split("-");
        this.year = Integer.parseInt(strs[0]);
        this.month = Integer.parseInt(strs[1]);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d", year, month);
    }

    public String getSqlStr() {
        return this.toString();
    }
}
