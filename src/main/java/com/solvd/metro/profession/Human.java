package com.solvd.metro.profession;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"firstName", "lastName"})
public abstract class Human {

    private String firstName;
    private String lastName;
    private Integer idPassport;
    private Gender gender;

    public Human(String firstName, String lastName, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Human() {

    }

    public enum Gender {
        MALE, FEMALE;
    }

    @XmlTransient
    public Gender getGender() {
        return gender;
    }

    @XmlTransient
    public int getIdPassport() {
        return idPassport;
    }

    public void setIdPassport(int idPassport) {
        this.idPassport = idPassport;
    }

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }
}
