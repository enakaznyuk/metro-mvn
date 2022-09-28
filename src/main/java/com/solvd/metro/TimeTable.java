package com.solvd.metro;

import com.solvd.metro.xml.LocalTimeAdapter;

import java.time.LocalTime;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

//@XmlRootElement(namespace = "start")
@XmlType(propOrder = {"startWorking", "endWorking"})
public class TimeTable {

    private LocalTime startWorking;
    private LocalTime middleWorking;
    private LocalTime middleWorkingEnd;
    private LocalTime endWorking;
    private String partOfDay;

    @XmlJavaTypeAdapter(value = LocalTimeAdapter.class)
    @XmlElement
    public LocalTime getStartWorking() {
        return startWorking;
    }

    public void setStartWorking(LocalTime startWorking) {
        this.startWorking = startWorking;
    }

    public LocalTime getMiddleWorking() {
        return middleWorking;
    }

    @XmlTransient
    public void setMiddleWorking(LocalTime middleWorking) {
        this.middleWorking = middleWorking;
    }

    public LocalTime getMiddleWorkingEnd() {
        return middleWorkingEnd;
    }

    @XmlTransient
    public void setMiddleWorkingEnd(LocalTime middleWorkingEnd) {
        this.middleWorkingEnd = middleWorkingEnd;
    }

    @XmlJavaTypeAdapter(value = LocalTimeAdapter.class)
    @XmlElement
    public LocalTime getEndWorking() {
        return endWorking;
    }

    public void setEndWorking(LocalTime endWorking) {
        this.endWorking = endWorking;
    }

    public String getPartOfDay() {
        return partOfDay;
    }

    @XmlTransient
    public void setPartOfDay(String partOfDay) {
        this.partOfDay = partOfDay;
    }
}