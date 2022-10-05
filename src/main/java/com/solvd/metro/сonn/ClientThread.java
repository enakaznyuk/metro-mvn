package com.solvd.metro.сonn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientThread extends Thread{

    private static final Logger LOGGER = LogManager.getLogger(ClientThread.class);

    @Override
    public void run(){
        Connection connection = ConnectionPool.getInstance().getConnection();
        if (connection != null){
            connection.startWork();
        }else {
            LOGGER.info("No thread for you, sorry!");
        }
    }
}
