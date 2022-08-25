package com.solvd.metro;

import java.time.LocalTime;

public class TimeTable {

    private LocalTime startWorking;
    private LocalTime middleWorking;
    private LocalTime middleWorkingEnd;
    private LocalTime endWorking;
    private String partOfDay;

    public LocalTime getStartWorking() {
        return startWorking;
    }

    public void setStartWorking(LocalTime startWorking) {
        this.startWorking = startWorking;
    }

    public LocalTime getMiddleWorking() {
        return middleWorking;
    }

    public void setMiddleWorking(LocalTime middleWorking) {
        this.middleWorking = middleWorking;
    }

    public LocalTime getMiddleWorkingEnd() {
        return middleWorkingEnd;
    }

    public void setMiddleWorkingEnd(LocalTime middleWorkingEnd) {
        this.middleWorkingEnd = middleWorkingEnd;
    }

    public LocalTime getEndWorking() {
        return endWorking;
    }

    public void setEndWorking(LocalTime endWorking) {
        this.endWorking = endWorking;
    }

    public String getPartOfDay() {
        return partOfDay;
    }

    public void setPartOfDay(String partOfDay) {
        this.partOfDay = partOfDay;
    }
}