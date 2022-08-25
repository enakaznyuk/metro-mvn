package com.solvd.metro.equip;

import com.solvd.metro.profession.Employee;

public abstract class Equip {

    private String name;
    private String place;

    public Equip(String name, String place) {
        this.name = name;
        this.place = place;
    }

    public abstract void belong(Employee employee);

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }
}
