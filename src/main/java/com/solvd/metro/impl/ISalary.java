package com.solvd.metro.impl;

import com.solvd.metro.exception.InvalidSalaryException;
import com.solvd.metro.profession.Employee;

public interface ISalary {

    void getSalary(Employee employee) throws InvalidSalaryException;

}
