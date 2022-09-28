package com.solvd.metro.station;

import com.solvd.metro.exception.InvalidDataException;
import com.solvd.metro.impl.IMajorRenovation;
import com.solvd.metro.impl.IWork;
import com.solvd.metro.xml.LocalDateAdapter;
import com.solvd.metro.xml.LocalTimeAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.metro.profession.Employee;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

@XmlType(propOrder = {"name", "dateBasis"})
public class Station implements /*IMajorRenovation,*/ IWork<Station> {

    private static final Logger LOGGER = LogManager.getLogger(Station.class);

    private String name;
    private LocalDate dateBasis;

    @XmlTransient
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

    public Station(String name, LocalDate dateBasis){
        this.name = name;
        this.dateBasis = dateBasis;
        if (dateBasis.getYear() < 1980) {
            throw new InvalidDataException("Date must be > 1980!");
        }
    }

    public Station(){}

    public enum Location{
        UNDERGROUND, GROUND;
    }

    public String toString() {
        String fullInfo = getName() + " " + getDateBasis();
        return "Station{ "
                + "Full Information = " + fullInfo
                + " }";
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

    @XmlTransient
    public void setEmployees(Map<Integer, Employee> employees) {
        this.employees = employees;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement
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
