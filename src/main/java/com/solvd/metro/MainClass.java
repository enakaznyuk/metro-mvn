package com.solvd.metro;

import com.solvd.metro.equip.Equip;
import com.solvd.metro.equip.EquipForCleaner;
import com.solvd.metro.equip.EquipForEngineer;
import com.solvd.metro.exception.InvalidSalaryException;
import com.solvd.metro.file.WorkWithFile;
import com.solvd.metro.profession.*;
import com.solvd.metro.station.Station;
import com.solvd.metro.train.Train;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainClass {

    private static final Logger LOGGER = LogManager.getLogger(MainClass.class);

    public static void main(String[] args) throws IOException {

        System.out.println("Hello");

        Metro metro = new Metro();
        BigDecimal bigDecimal = new BigDecimal(150);
        BigInteger bigInteger = new BigInteger("14");

        LOGGER.info("Hello world");
        Machinist ivan = new Machinist("Ivan", "Drozd", "Machinist", Human.Gender.MALE);
        ivan.setCompany("Metro.job");
        ivan.setCompanyAddress("Pushkin street, house 1");
        ivan.setPay(bigDecimal);
        ivan.setHoliday("14");
        ivan.setVacationSickDays(bigInteger);
        ivan.setIdPassport(123);
        ivan.setWeekDay(Employee.WeekDay.MONDAY);

        Machinist sergey = new Machinist("Sergey", "Sinica", "Machinist", Human.Gender.MALE);
        sergey.setCompany("Metro.job");
        sergey.setCompanyAddress("Lenin street, house 2");
        sergey.setIdPassport(456);
        List<Machinist> machinists = new ArrayList<>();
        machinists.add(ivan);
        machinists.add(sergey);

        Engineer dmitriy = new Engineer("Dmitriy", "Kukushka", "Engineer", Human.Gender.MALE);
        dmitriy.setIdPassport(789);
        List<Engineer> engineers = new ArrayList<>();
        engineers.add(dmitriy);

        Cleaner nikolay = new Cleaner("Nikolay", "Vorobey", "Cleaner", Human.Gender.MALE);
        nikolay.setIdPassport(123456789);
        List<Cleaner> cleaners = new ArrayList<>();
        cleaners.add(nikolay);

        Map<Integer, Employee> stationNemigaEmloyees = new HashMap<>();
        stationNemigaEmloyees.put(ivan.getIdPassport(), ivan);
        stationNemigaEmloyees.put(sergey.getIdPassport(), sergey);
        Map<Integer, Employee> stationProletarskayaEmloyees = new HashMap<>();
        stationProletarskayaEmloyees.put(dmitriy.getIdPassport(), dmitriy);
        stationProletarskayaEmloyees.put(nikolay.getIdPassport(), nikolay);
        Map<Integer, Employee> employees = new HashMap<>();
        employees.putAll(stationProletarskayaEmloyees);
        employees.putAll(stationNemigaEmloyees);

        Station nemiga = new Station("Nemiga", LocalDate.of(1985, 7, 21), Station.Location.UNDERGROUND);
        Station proletarskaya = new Station("Proletarskaya", LocalDate.of(1987, 3, 15), Station.Location.UNDERGROUND);
        nemiga.setEmployees(stationNemigaEmloyees);
        proletarskaya.setEmployees(stationProletarskayaEmloyees);
        List<Station> stations = new ArrayList<>(Arrays.asList(nemiga, proletarskaya));

        EquipForCleaner mop = new EquipForCleaner("Mop", "Cleaner Room");
        EquipForEngineer overalls = new EquipForEngineer("Overalls", "Cleaner Room");
        EquipForEngineer setOfTools = new EquipForEngineer("Set of tools", "Engineer Room");
        List<Equip> cleanerEquips = new ArrayList<>(Arrays.asList(mop, overalls));
        List<Equip> engineerEquips = new ArrayList<>(List.of(setOfTools));
        nikolay.setEquips(cleanerEquips);
        dmitriy.setEquips(engineerEquips);

        Train<Integer> train = new Train<>(1, LocalDate.of(2015, 9, 16), "Shtadler");
        List<Train<?>> trains = new ArrayList<>(List.of(train));
        ivan.setTrain(trains.get(0));

        TimeTable timeTable = new TimeTable();
        timeTable.setStartWorking(LocalTime.of(4, 0));
        timeTable.setMiddleWorking(LocalTime.of(10, 0));
        timeTable.setMiddleWorkingEnd(LocalTime.of(16, 0));
        timeTable.setEndWorking(LocalTime.of(23, 59));

        ArrayList<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            passengers.add(new Passenger());
        }

        metro.setStations(stations);
        metro.setEmployees(employees);
        metro.setTimeTable(timeTable);
        metro.setPassengers(passengers);

        PassengerFlowCalculation.retired(ivan);
        PassengerFlowCalculation.isEmployeeWorking(123, stations);
        PassengerFlowCalculation.getInformationAboutTrain(ivan);
        PassengerFlowCalculation.flowDivision(timeTable, passengers);
        PassengerFlowCalculation.useEquip(nikolay);
        PassengerFlowCalculation.toCompare(ivan, sergey);
        PassengerFlowCalculation.getFirstAndLastName(ivan);
        PassengerFlowCalculation.weekend(ivan);
        PassengerFlowCalculation.stationType(nemiga);

        try {
            ivan.getSalary(ivan);
        } catch (InvalidSalaryException e) {
            LOGGER.info("error caught here!");
        } finally {
            LOGGER.info("Operation end!");
        }

        try (ClassForTryCatch classForTryCatch = new ClassForTryCatch()) {
            classForTryCatch.doSmth();
        }

        String str = "src/main/resources/article-for-java.txt";
        WorkWithFile.readFile(str);
    }
}