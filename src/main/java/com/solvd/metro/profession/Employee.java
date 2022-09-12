package com.solvd.metro.profession;

import com.solvd.metro.exception.InvalidNameException;
import com.solvd.metro.exception.InvalidSalaryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.metro.impl.ISalary;
import com.solvd.metro.impl.ISick;
import com.solvd.metro.impl.IWork;

import java.math.BigDecimal;
import java.math.BigInteger;

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

    public enum WeekDay{
        SUNDAY ("Воскресенье"),
        MONDAY ("Понедельник"),
        TUESDAY ("Вторник"),
        WEDNESDAY ("Среда"),
        THURSDAY ("Четверг"),
        FRIDAY ("Пятница"),
        SATURDAY ("Суббота");

        private String day;

        WeekDay(String day) {
            this.day = day;
        }

        public String getDay() {
            return day;
        }
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    //public abstract void getSalary(Employee employee) throws InvalidSalaryException;

    //public abstract void getSocialPackage(Employee employee);

    public abstract void getTimeWorking(Employee employee);

    public abstract void getWeekend(Employee employee);

    public String getProfession() {
        return profession;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public BigInteger getVacationSickDays() {
        return vacationSickDays;
    }

    public void setVacationSickDays(BigInteger vacationSickDays) {
        this.vacationSickDays = vacationSickDays;
    }

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
