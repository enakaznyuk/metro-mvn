package com.solvd.metro.Conn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {

    private static final Logger LOGGER = LogManager.getLogger(Connection.class);

    public static void create(){
        LOGGER.info("Create DB");
    }

    public void read(){
        LOGGER.info("Read DB");
    }

    public void update(){
        LOGGER.info("Update DB");
    }

    public void delete (){
        LOGGER.info("Delete DB");
    }

    public void save(){
        LOGGER.info("Save DB");
    }

    public void startWork(){
        LOGGER.info("Input number");
        read();
        update();
        delete();
        save();
    }
}
