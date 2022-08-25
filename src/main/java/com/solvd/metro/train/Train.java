package com.solvd.metro.train;

import com.solvd.metro.impl.IService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class Train<T> implements IService {

    private static final Logger LOGGER = LogManager.getLogger(Train.class);

    private T trainNumber;
    private LocalDate constructionDate;
    public String modelTrain;

    public Train(T trainNumber, LocalDate constructionDate, String modelTrain) {
        this.trainNumber = trainNumber;
        this.constructionDate = constructionDate;
        this.modelTrain = modelTrain;
    }

    public T getTrainNumber() {
        return trainNumber;
    }

    public LocalDate getConstructionDate() {
        return constructionDate;
    }

    public void getTimeService(Train train) {
        LocalDate service = getConstructionDate().plusYears(50);
        int date = service.getYear();
        LOGGER.info("Time of service: " + date);
    }

    public void getGuarantee(Train train) {
        LocalDate guarantee = getConstructionDate().plusYears(20);
        int date = guarantee.getYear();
        LOGGER.info("Time of guarantee: " + date);
    }

    public String getModelTrain() {
        return modelTrain;
    }
}
