package com.victorku.musiccloud.web.model;

import org.joda.time.LocalDate;

public class DateDTO {
    private Integer year;
    private Integer month;
    private Integer day;

    public DateDTO() {
    }

    public DateDTO(LocalDate date) {
        year = date.getYear();
        month = date.getMonthOfYear();
        day = date.getDayOfMonth();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
