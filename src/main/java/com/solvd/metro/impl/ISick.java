package com.solvd.metro.impl;

import com.solvd.metro.profession.Employee;

@FunctionalInterface
public interface ISick<Employee> {

    void getSocialPackage(Employee employee);

}
