package com.solvd.metro.сonn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private int POOL_SIZE = 10;
    private Queue<Connection> freeConnections;
    private Queue<Connection> engageConnections;
    private boolean poolLock = true;

    private volatile static ConnectionPool instance;

    private ConnectionPool() {
        freeConnections = new ConcurrentLinkedQueue<>();
        engageConnections = new ConcurrentLinkedQueue<>();
    }

    public static ConnectionPool getInstance(int POOL_SIZE) {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instance.POOL_SIZE = POOL_SIZE;
                    instance.setFreeConnections();
                }
            }
        }
        return instance;
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instance.setFreeConnections();
                }
            }
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        while (connection == null) {
            if(freeConnections.isEmpty()){
                Connection.pause(2);
            }else {
                connection = freeConnections.poll();
                engageConnections.add(connection);
            }
        }
        return connection;
    }

    public void returnConnection(Connection conn) {
        engageConnections.remove(conn);
        freeConnections.add(conn);
    }

    public int getPOOL_SIZE() {
        return POOL_SIZE;
    }

    public void setFreeConnections() {
        for (int i = 0; i < getPOOL_SIZE(); i++) {
            freeConnections.add(new Connection());
        }
    }
}
