package com.solvd.metro.profession;

import com.solvd.metro.equip.Equip;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Engineer extends Employee {

    private static final Logger LOGGER = LogManager.getLogger(Engineer.class);

    private String company;
    private String companyAddress;
    private List<Equip> equips;

    public Engineer(String firstName, String lastName, String profession, Gender gender) {
        super(firstName, lastName, profession, gender);
    }

    public Engineer(String firstName, String lastName, String profession){
        super(firstName, lastName, profession);
    }

    public String toString() {
        String fullName = getFirstName() + " " + getLastName();
        return "Engineer{ "
                + "Full Name = " + fullName
                + " }";
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
