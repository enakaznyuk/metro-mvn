package com.solvd.metro;

import com.solvd.metro.Conn.ClientThread;
import com.solvd.metro.Conn.Connection;
import com.solvd.metro.Conn.ConnectionPool;
import com.solvd.metro.equip.Equip;
import com.solvd.metro.equip.EquipForCleaner;
import com.solvd.metro.equip.EquipForEngineer;
import com.solvd.metro.exception.InvalidSalaryException;
import com.solvd.metro.file.WorkWithFile;
import com.solvd.metro.impl.IMajorRenovation;
import com.solvd.metro.impl.ISalary;
import com.solvd.metro.impl.ISick;
import com.solvd.metro.impl.ISumm;
import com.solvd.metro.profession.*;
import com.solvd.metro.reflexio.GetReflexio;
import com.solvd.metro.station.Station;
import com.solvd.metro.train.Train;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainClass {

    private static final Logger LOGGER = LogManager.getLogger(MainClass.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

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
        dmitriy.setPay(bigDecimal);
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

        stations.stream().flatMap(st->st.getEmployeeList().stream()).forEach(System.out::println);

        EquipForCleaner mop = new EquipForCleaner("Mop", "Cleaner Room");
        EquipForEngineer overalls = new EquipForEngineer("Overalls", "Cleaner Room");
        EquipForEngineer setOfTools = new EquipForEngineer("Set of tools", "Engineer Room");
        List<Equip> cleanerEquips = new ArrayList<>(Arrays.asList(mop, overalls));
        List<Equip> engineerEquips = new ArrayList<>(List.of(setOfTools));
        nikolay.setEquips(cleanerEquips);
        dmitriy.setEquips(engineerEquips);

        Train<Integer> train = new Train<>(1, LocalDate.of(2015, 9, 16), "Shtadler");
        List<Train<?>> trains = List.of(train);
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

        //PassengerFlowCalculation.retired(ivan);
        PassengerFlowCalculation.isEmployeeWorking(123, stations);
        PassengerFlowCalculation.isEmployeeWorking(987, stations);
        Consumer<Machinist> printer = (Machinist m) -> {
            String model = m.getTrain().getModelTrain();
            LOGGER.info(m.getFirstName() + '\t' + m.getTrain().getModelTrain() + '\t' + model);
        };
        printer.accept(ivan);
        PassengerFlowCalculation.getInformationAboutTrain(ivan);
        PassengerFlowCalculation.flowDivision(timeTable, passengers);
        PassengerFlowCalculation.useEquip(nikolay);
        PassengerFlowCalculation.toCompare(ivan, sergey);
        PassengerFlowCalculation.getFirstAndLastName(ivan);
        //PassengerFlowCalculation.weekend(ivan);
        //PassengerFlowCalculation.stationType(nemiga);
        GetReflexio.getBadClassHuman();

        try (ClassForTryCatch classForTryCatch = new ClassForTryCatch()) {
            classForTryCatch.doSmth();
        }

        String str = "src/main/resources/article-for-java.txt";
        WorkWithFile.readFile(str);

        Predicate<Integer> isEvenNumber = x -> x % 2 == 0;

        System.out.println(isEvenNumber.test(4));
        System.out.println(isEvenNumber.test(3));

        ISumm<Integer, Integer> isSumm = (a, b) -> {
            System.out.println("a + b = " + (a + b));
        };

        PassengerFlowCalculation.methodWithParameter(1, 2, isSumm);

        IMajorRenovation iMajorRenovation = (Station s) -> {
            LocalDate renovation = s.getDateBasis().plusYears(50);
            int date = renovation.getYear();
            LOGGER.info("Date of major Renovation: " + date);
        };
        iMajorRenovation.getDateMajorRenovation(nemiga);

        ISalary<Engineer> iSalary = (Engineer e) -> {
            BigDecimal salary = e.getPay();
            BigDecimal zero = new BigDecimal("0");
            salary = salary.multiply(BigDecimal.valueOf(6));
            LOGGER.info(e.getFirstName() + " salary per month = " + salary + "$");
            if (salary.compareTo(zero) <= 0) {
                throw new InvalidSalaryException("salary must be > 0");
            }
        };
        try {
            iSalary.getSalary(dmitriy);
        } catch (InvalidSalaryException e) {
            LOGGER.info("error caught here!");
        } finally {
            LOGGER.info("Operation end!");
        }

        ISick<Machinist> iSick = (Machinist m) -> {
            BigDecimal socialPay = m.getPay();
            socialPay = socialPay.multiply(BigDecimal.valueOf(0.4));
            LOGGER.info(m.getFirstName() + " has " + socialPay + "$ for sick");
            LOGGER.info(m.getFirstName() + " has " + m.getVacationSickDays() + " days of sick leave per year");
        } ;
        iSick.getSocialPackage(ivan);



        ///////////////////////////////////////////////////////////////////////////////

        List<Integer> forStream = new ArrayList<>();
        forStream.add(0);
        forStream.add(1);
        forStream.add(2);
        forStream.add(3);
        forStream.add(4);
        forStream.add(5);
        forStream.add(6);
        forStream.add(7);
        forStream.add(8);
        forStream.add(9);

        forStream.
                forEach(System.out::println);
        System.out.println("\n");
        forStream.stream().filter(x -> x > 5).
                forEach(System.out::println);
        forStream.stream().map(m -> m + 1).
                forEach(System.out::println);
        forStream.stream().
                peek(System.out::println);

        Stream<String> phoneStream = Stream.of(("Honor 5"),
                new String("Nokia 9"),
                new String("Samsung Galaxy S9"),
                new String("LG G6"));
        Set<String> phones = phoneStream
                .filter(s->s.length()<10)
                .collect(Collectors.toSet());
        phones.
                forEach((k)->System.out.println(k + " "));

        Optional<Integer> first = forStream.
                stream().
                findFirst();
        System.out.println(first);

        Optional<Integer> noNumber = forStream.
                stream().
                min(Integer::compare);
        System.out.println(noNumber.orElse(0));

        /*ArrayList<Integer> numbers = new ArrayList<Integer>();
        Optional<Integer> min = numbers.stream().min(Integer::compare);
        System.out.println(min.orElseThrow(IllegalStateException::new));*/

        ///////////////////////////////////////////////////////////////

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.setPOOL_SIZE(3);
        connectionPool.setFreeConnections();

        ClientThread ct1 = new  ClientThread();

        //ct1.start();

        new Thread(() -> {
            Connection connection = ConnectionPool.getInstance().getConnection();
            if (connection != null){
                connection.startWork();
            }else {
                LOGGER.info("No thread for you, sorry!");
            }
        }).start();

        Runnable r = () ->{
            Connection connection = ConnectionPool.getInstance().getConnection();
            if (connection != null){
                connection.startWork();
            }else {
                LOGGER.info("No thread for you, sorry!");
            }
        };
        //r.run();

        Runnable rTwo = () ->{
            Connection connection = ConnectionPool.getInstance().getConnection();
            if (connection != null){
                connection.startWork();
            }else {
                LOGGER.info("No thread for you, sorry!");
            }
        };
        //rTwo.run();

        CompletableFuture<Void> threadOne = CompletableFuture.runAsync(() ->{
            Connection connection = ConnectionPool.getInstance().getConnection();
            if (connection != null){
                connection.startWork();
            }else {
                LOGGER.info("No thread for you, sorry!");
            }
        });
        try {
            threadOne.get(1, TimeUnit.SECONDS);
        }catch (InterruptedException | ExecutionException | TimeoutException e){
            throw new RuntimeException(e);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(r, TimeUnit.SECONDS);
        executorService.submit(rTwo, TimeUnit.SECONDS);
        executorService.submit(ct1, TimeUnit.SECONDS);
        executorService.shutdown();

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(() -> 3);
        CompletableFuture<Void> cfAll = CompletableFuture.allOf(cf1, cf2, cf3);
        cfAll.join();


        CompletableFuture<String> threadTwo = CompletableFuture.supplyAsync(() ->{
            pause(2);
            return "1234";
        }).thenApplyAsync(p -> {
            return "4321";
        });
        threadTwo.join();
    }

    private static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}