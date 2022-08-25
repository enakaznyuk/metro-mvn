package com.solvd.metro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClassForTryCatch implements AutoCloseable {

    private static final Logger LOGGER = LogManager.getLogger(ClassForTryCatch.class);

    public void doSmth() {
        LOGGER.info("doSmth in try-with-resources");
    }

    @Override
    public void close() {
        LOGGER.info("Close ClassForTryCatch!");
    }
}
