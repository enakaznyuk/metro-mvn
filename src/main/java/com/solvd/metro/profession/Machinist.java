package com.solvd.metro.profession;

import com.solvd.metro.exception.InvalidSalaryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.metro.train.Train;

import java.math.BigDecimal;
import java.util.Objects;

public class Machinist extends Employee {

    private static final Logger LOGGER = LogManager.getLogger(Machinist.class);

    private String company;
    private String companyAddress;
    private Train<?> train;

    public Machinist(String firstName, String lastName, String profession, Gender gender) {
        super(firstName, lastName, profession, gender);
    }

    public String toString() {
        String fullName = getFirstName() + " " + getLastName();
        return "Machinist{ "
                + "Full Name = " + fullName
                + " }";
    }

    @Override
    public boolean equals(Object obj) {
        Machinist other = (Machinist) obj;
        if (this == other) {
            LOGGER.info("Same");
            return true;
        } else if (other == null) {
            return false;
        } else if (!Objects.equals(getClass(), other.getClass())) {
            return false;
        }

        if (!Objects.equals(getFirstName(), other.getFirstName())) {
            LOGGER.info("Different");
            return false;
        }
        return !Objects.equals(getCompany(), other.getCompany()) && !Objects.equals(getCompanyAddress(), other.getCompanyAddress());
    }

    @Override
    public int hashCode() {
        int result = getFirstName() == null ? 0 : getFirstName().hashCode();
        result = 31 * result + getCompany().hashCode();
        result = 31 * result + getCompanyAddress().hashCode();
        return result;
    }

    @Override
    public void getSalary(Employee employee) throws InvalidSalaryException {
        BigDecimal salary = employee.getPay();
        salary = salary.multiply(BigDecimal.valueOf(5.5));
        BigDecimal zero = new BigDecimal("0");
        LOGGER.info(employee.getFirstName() + " salary per month = " + salary + "$");
        if (salary.compareTo(zero) <= 0) {
            throw new InvalidSalaryException("salary must be > 0");
        }
    }

    @Override
    public void getSocialPackage(Employee employee) {
        BigDecimal socialPay = employee.getPay();
        socialPay = socialPay.multiply(BigDecimal.valueOf(0.4));
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

    public Train<?> getTrain() {
        return train;
    }

    public void setTrain(Train<?> train) {
        this.train = train;
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
}
