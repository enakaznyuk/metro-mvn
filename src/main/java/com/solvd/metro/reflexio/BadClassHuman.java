package com.solvd.metro.reflexio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BadClassHuman {

    private static final Logger LOGGER = LogManager.getLogger(BadClassHuman.class);

    private String firstName;
    private int numberOfPassport;

    public BadClassHuman(){}

    public BadClassHuman(String firstName, int numberOfPassport){
        this.firstName = firstName;
        this.numberOfPassport = numberOfPassport;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getNumberOfPassport() {
        return numberOfPassport;
    }

    private void information(String firstName, int numberOfPassport){
        LOGGER.info("in class BadClasshuman " + firstName + " = " + numberOfPassport);
    }

    private void sayHello(){
        LOGGER.info("We are say Hello!");
    }
}
