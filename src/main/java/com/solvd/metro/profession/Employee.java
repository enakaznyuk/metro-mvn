package com.solvd.metro.profession;

import com.solvd.metro.exception.InvalidNameException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.metro.impl.IWork;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@XmlType(propOrder = {"profession"})
public abstract class Employee extends Human implements /*ISalary<Employee>, ISick,*/ IWork<Employee> {

    private static final Logger LOGGER = LogManager.getLogger(Employee.class);

    private String profession;
    private BigDecimal pay;
    private BigInteger vacationSickDays;
    private String holiday;
    private WeekDay weekDay;

    public Employee(String firstName, String secondName, String profession, Gender gender) {
        super(firstName, secondName, gender);
        this.profession = profession;

        if (!firstName.matches("^\\D*$")) {
            throw new InvalidNameException("name must not contain numbers");
        }
    }

    public Employee(String firstName, String secondName, String profession) {
        super(firstName, secondName);
        this.profession = profession;

        if (!firstName.matches("^\\D*$")) {
            throw new InvalidNameException("name must not contain numbers");
        }
    }

    public enum WeekDay{
        SUNDAY ("SUNDAY"),
        MONDAY ("MONDAY"),
        TUESDAY ("TUESDAY"),
        WEDNESDAY ("WEDNESDAY"),
        THURSDAY ("THURSDAY"),
        FRIDAY ("FRIDAY"),
        SATURDAY ("SATURDAY");

        private String day;

        WeekDay(String day) {
            this.day = day;
        }

        public String getDay() {
            return day;
        }
    }

    @XmlTransient
    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public abstract void getTimeWorking(Employee employee);

    public abstract void getWeekend(Employee employee);

    @XmlElement
    public String getProfession() {
        return profession;
    }

    @XmlTransient
    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    @XmlTransient
    public BigInteger getVacationSickDays() {
        return vacationSickDays;
    }

    public void setVacationSickDays(BigInteger vacationSickDays) {
        this.vacationSickDays = vacationSickDays;
    }

    @XmlTransient
    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public void getCompanyAddress(String companyAddress) {
        LOGGER.info("Company address = " + companyAddress);
    }
}
