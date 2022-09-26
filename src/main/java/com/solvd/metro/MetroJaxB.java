package com.solvd.metro;

import com.solvd.metro.profession.Employee;
import com.solvd.metro.profession.Passenger;
import com.solvd.metro.station.Station;
import jakarta.xml.bind.annotation.XmlTransient;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "Metro")
@XmlType(propOrder = {"Employees", "Stations", "TimeTable"})

public class MetroJaxB {

    private Map<Integer, Employee> employees;
    private List<Station> stations;
    private List<Passenger> passengers;
    private TimeTable timeTable;

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    @XmlElementWrapper(name = "Employees")
    @XmlElement(name = "Employee")
    public void setEmployees(Map<Integer, Employee> employees) {
        this.employees = employees;
    }

    public List<Station> getStations() {
        return stations;
    }

    @XmlElementWrapper(name = "Stations")
    @XmlElement(name = "Station")
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    @XmlElement
    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    @XmlTransient
    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }
}
