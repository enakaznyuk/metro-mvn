package com.solvd.metro;

import com.solvd.metro.profession.Employee;
import com.solvd.metro.profession.Passenger;
import com.solvd.metro.station.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Metro {

    private Map<Integer, Employee> employees;
    private List<Station> stations;
    private List<Passenger> passengers;
    private TimeTable timeTable;

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<Integer, Employee> employees) {
        this.employees = employees;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }
}
