package com.solvd.metro;

import com.solvd.metro.equip.Equip;
import com.solvd.metro.impl.ISumm;
import com.solvd.metro.profession.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.metro.station.Station;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class PassengerFlowCalculation {

    private static final Logger LOGGER = LogManager.getLogger(PassengerFlowCalculation.class);

    public static void isEmployeeWorking(Integer idPassport, List<Station> stations) {
        for (Station station : stations) {
            boolean work = false;
            for(Map.Entry<Integer, Employee> entry : station.getEmployees().entrySet()){
                if (idPassport.equals(entry.getKey())) {
                    LOGGER.info(station.getEmployees().get(idPassport) + " works at " + station.getName());
                    work = true;
                    break;
                }
            }
            if(!work){
                LOGGER.info(idPassport + " does not working at " + station.getName());
            }
        }
        boolean work = false;
    }

    public static void getInformationAboutTrain(Machinist machinist) {
        String model = machinist.getTrain().getModelTrain();
        LOGGER.info(machinist.getFirstName() + '\t' + machinist.getTrain().getModelTrain() + '\t' + model);
    }

    public static boolean checkAndRefreshMetroStatus(TimeTable timeTable) {
        boolean work = false;

        LocalTime now = LocalTime.now();
        LocalTime start = timeTable.getStartWorking();
        LocalTime middleStart = timeTable.getMiddleWorking();
        LocalTime middleEnd = timeTable.getMiddleWorkingEnd();
        LocalTime end = timeTable.getEndWorking();

        if (now.isAfter(start) && now.isBefore(end)) {
            if (now.isBefore(middleStart)) {
                timeTable.setPartOfDay("Morning");
            } else if (now.isAfter(middleStart) && now.isBefore(middleEnd)) {
                timeTable.setPartOfDay("Day");
            } else if (now.isAfter(middleEnd)) {
                timeTable.setPartOfDay("Evening");
            }
            work = true;
        }
        return work;
    }

    public static void flowDivision(TimeTable timeTable, ArrayList<Passenger> passengers) {
        boolean work = PassengerFlowCalculation.checkAndRefreshMetroStatus(timeTable);
        int numberOfPassangers = passengers.size();

        Predicate<Boolean> isPositive = b -> !b;
        if (isPositive.test(work)) {
            LOGGER.info("underground is not working");
            return;
        }

        if ("Morning".equals(timeTable.getPartOfDay())) {
            LOGGER.info("you need 3 trains for " + numberOfPassangers / 5 + " passangers");
        } else if ("Day".equals(timeTable.getPartOfDay())) {
            LOGGER.info("you need 5 trains for " + numberOfPassangers / 10 + " passangers");
        } else if ("Evening".equals(timeTable.getPartOfDay())) {
            LOGGER.info("you need 20 trains for " + numberOfPassangers + " passangers");
        }
    }

    public static void toCompareEngineer(Engineer engineerfirst, Engineer engineersecond) {
        if (engineerfirst.equals(engineersecond)) {
            LOGGER.info("it's the same person");
        } else {
            LOGGER.info("it's the different person");
        }
    }

    public static void toCompare(Machinist machinistfirst, Machinist machinistsecond) {
        LOGGER.info("Hashcode machinist 1 = ");
        LOGGER.info(machinistfirst.hashCode() + "_______" + machinistsecond.hashCode());
    }

    public static void useEquip(Cleaner cleaner) {
        for (int i = 0; i < cleaner.getEquips().size(); i++) {
            Equip equip = cleaner.getEquips().get(i);
            equip.belong(cleaner);
        }

        cleaner.getEquips().forEach((Equip e) -> e.belong(cleaner));
        //stream.forEach((Equip e) -> e.belong(cleaner));
    }


    public static void getFirstAndLastName(Human human) {
        LOGGER.info("First Name = " + human.getFirstName());
        LOGGER.info("Last Name =  " + human.getLastName());
    }

    /*public static void retired(Employee employee){
        switch (employee.getGender()) {
            case MALE -> LOGGER.info("He can retire at 65 years.");
            case FEMALE -> LOGGER.info("She can retire at 60 years.");
            default -> {
            }
        }
    }

    public static void weekend(Employee employee){
        switch (employee.getWeekDay()) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> LOGGER.info(employee.getFirstName() + " work on " + employee.getWeekDay().getDay());
            case SATURDAY, SUNDAY -> LOGGER.info(employee.getFirstName() + " weekend on " + employee.getWeekDay().getDay());
        }
    }

    public static void stationType(Station station){
        switch (station.getLocation()){
            case GROUND -> LOGGER.info("Ground station");
            case UNDERGROUND -> LOGGER.info("Underground station");
        }
    }*/

    public static void methodWithParameter(int x, int y, ISumm<Integer, Integer> function){
        function.summ(x, y);
    }
}