package com.solvd.metro.equip;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.metro.profession.Employee;

public class EquipForCleaner extends Equip {

    private static final Logger LOGGER = LogManager.getLogger(EquipForCleaner.class);

    public EquipForCleaner(String name, String place) {
        super(name, place);
    }

    @Override
    public void belong(Employee employee) {
        LOGGER.info(employee.getFirstName() + " use " + getName());
    }

}
