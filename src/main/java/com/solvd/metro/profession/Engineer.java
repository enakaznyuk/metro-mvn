package com.solvd.metro.profession;

import com.solvd.metro.equip.Equip;
import com.solvd.metro.exception.InvalidSalaryException;
import com.solvd.metro.impl.IWork;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Engineer extends Employee {

    private static final Logger LOGGER = LogManager.getLogger(Engineer.class);

    private String company;
    private String companyAddress;
    private List<Equip> equips;

    public Engineer(String firstName, String lastName, String profession) {
        super(firstName, lastName, profession);
    }

    public String toString() {
        String fullName = getFirstName() + " " + getLastName();
        return "Engineer{ "
                + "Full Name = " + fullName
                + " }";
    }

    @Override
    public void getSalary(Employee employee) throws InvalidSalaryException {
        BigDecimal salary = employee.getPay();
        BigDecimal zero = new BigDecimal("0");
        salary = salary.multiply(BigDecimal.valueOf(6));
        LOGGER.info(employee.getFirstName() + " salary per month = " + salary + "$");
        if (salary.compareTo(zero) <= 0) {
            throw new InvalidSalaryException("salary must be > 0");
        }
    }

    @Override
    public void getSocialPackage(Employee employee) {
        BigDecimal socialPay = employee.getPay();
        socialPay = socialPay.multiply(BigDecimal.valueOf(0.5));
        LOGGER.info(employee.getFirstName() + " has " + socialPay + "$ for sick");
        LOGGER.info(employee.getFirstName() + " has " + employee.getVacationSickDays() + " days of sick leave per year");
    }

    @Override
    public void getTimeWorking(Employee employee) {
        LOGGER.info(getFirstName() + " is working from 8:00 to 17:00");
    }

    @Override
    public void getWeekend(Employee employee) {
        LOGGER.info(getFirstName() + " has " + getHoliday());
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public void getCompany(String company) {
        LOGGER.info("Company = " + getCompany());
    }

    public void getCompanyAddress(String companyAddress) {
        super.getCompanyAddress(getCompanyAddress());
    }

    public List<Equip> getEquips() {
        return equips;
    }

    public void setEquips(List<Equip> equips) {
        this.equips = equips;
    }
}
