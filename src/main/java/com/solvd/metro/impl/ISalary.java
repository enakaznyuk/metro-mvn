package com.solvd.metro.impl;

import com.solvd.metro.exception.InvalidSalaryException;
import com.solvd.metro.profession.Employee;

@FunctionalInterface
public interface ISalary<Employee> {

    void getSalary(Employee employee) throws InvalidSalaryException;

}
