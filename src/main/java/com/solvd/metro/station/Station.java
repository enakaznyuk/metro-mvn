package com.solvd.metro.station;

import com.solvd.metro.exception.InvalidDataException;
import com.solvd.metro.impl.IMajorRenovation;
import com.solvd.metro.impl.IWork;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.metro.profession.Employee;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Station implements /*IMajorRenovation,*/ IWork<Station> {

    private static final Logger LOGGER = LogManager.getLogger(Station.class);

    private LocalDate dateBasis;
    private String name;
    private Location location;
    private Map<Integer, Employee> employees;


    public Station(String name, LocalDate dateBasis, Location location) {
        this.name = name;
        this.dateBasis = dateBasis;
        this.location = location;
        if (dateBasis.getYear() < 1980) {
            throw new InvalidDataException("Date must be > 1980!");
        }
    }

    public enum Location{
        UNDERGROUND, GROUND;
    }

    /*public void getDateMajorRenovation(Station station) {
        LocalDate renovation = getDateBasis().plusYears(50);
        int date = renovation.getYear();
        LOGGER.info("Date of major Renovation: " + date);
    }*/

    @Override
    public void getTimeWorking(Station station) {
        LOGGER.info("Station is working from 5:00 to 23:59");
    }

    @Override
    public void getWeekend(Station station) {
        LOGGER.info("Station is working everyday");
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<Integer, Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateBasis() {
        return dateBasis;
    }

    public Location getLocation() {
        return location;
    }

    public Collection<Employee> getEmployeeList(){
        return employees.values();
    }
}
