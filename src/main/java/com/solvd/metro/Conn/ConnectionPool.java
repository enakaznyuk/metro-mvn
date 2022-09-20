package com.solvd.metro.Conn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private int POOL_SIZE;
    private Queue<Connection> freeConnections;
    private Queue<Connection> engageConnections;

    private volatile static ConnectionPool instance;

    private ConnectionPool() {
        freeConnections = new ConcurrentLinkedQueue<>();
        engageConnections = new ConcurrentLinkedQueue<>();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection connection;
        connection = freeConnections.poll();
        if (connection != null){
            engageConnections.add(connection);
        }
        return connection;
    }

    public synchronized boolean returnConnection(Connection conn) {
        if (conn == null || !engageConnections.contains(conn))
            return false;
        engageConnections.remove(conn);
        freeConnections.add(conn);
        return true;
    }

    public int getPOOL_SIZE() {
        return POOL_SIZE;
    }

    public void setPOOL_SIZE(int POOL_SIZE) {
        this.POOL_SIZE = POOL_SIZE;
    }

    public void setFreeConnections() {
        for(int i = 0; i < getPOOL_SIZE(); i++){
            freeConnections.add(new Connection());
        }
    }
}
