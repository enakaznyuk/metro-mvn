package com.solvd.metro.profession;

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

    public Human() {
    }

    public enum Gender{
        MALE, FEMALE;
    }

    public Gender getGender() {
        return gender;
    }

    public int getIdPassport() {
        return idPassport;
    }

    public void setIdPassport(int idPassport) {
        this.idPassport = idPassport;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
