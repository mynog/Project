package com.victorku.musiccloud.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.LocalDate;

public class DateDTO {

    private Integer year;
    private Integer month;
    private Integer day;

    public DateDTO() {
    }

    public DateDTO(LocalDate date) {
        if (date == null) {
            return;
        }
        year = date.getYear();
        month = date.getMonthOfYear();
        day = date.getDayOfMonth();
    }

    @JsonIgnore
    public LocalDate getLocalDateData()
    {
        try {
            return new LocalDate(year, month, day);
        }catch (IllegalArgumentException illegalArgument) {
            return new LocalDate(1970,1,1);
        }
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
