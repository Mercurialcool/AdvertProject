package by.vasiliuk.project.model.pool;

import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.vasiliuk.project.model.pool.PoolConfigurator.*;

public class ConnectionPool implements Pool<ConnectionWrapper>{
    static Logger logger = LogManager.getLogger();
    private static ConnectionPool pool;
    private final int CONNECTION_POOL_SIZE;
    public static final String POOL_DESTROYED = "Connection pool has been destroyed";
    public static final String DRIVERS_DEREGISTERED = "Drivers have been deregistered";
    public static final String POOL_CREATED = "Connection pool created";
    public static final String CONNECTION_POLL_DOESNT_CREATED = "Connection pool not created";
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean created = new AtomicBoolean(false);
    private ArrayBlockingQueue<ConnectionWrapper> availableConnections;
    private ArrayBlockingQueue<ConnectionWrapper> usedConnections;
    private ConnectionPool(){
        Properties properties = PoolConfigurator.getConfigurator().getProperties();
                String url = properties.getProperty(DB_HOST);
                String user = properties.getProperty(DB_LOGIN);
                String pass = properties.getProperty(DB_PASSWORD);

                CONNECTION_POOL_SIZE = Integer.parseInt(properties.getProperty("connection-pool-size"));
            availableConnections = new ArrayBlockingQueue<>(CONNECTION_POOL_SIZE);
            usedConnections = new ArrayBlockingQueue<>(CONNECTION_POOL_SIZE);
            try{

            Class.forName(properties.getProperty(DB_DRIVER)).getDeclaredConstructor().newInstance();
            for(int i = 0; i < CONNECTION_POOL_SIZE; i++){
                availableConnections.add(new ConnectionWrapper(DriverManager.getConnection(url, user, pass)));
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException | ClassNotFoundException | SQLException e) {
                logger.log(Level.FATAL, e);
            throw new RuntimeException(CONNECTION_POLL_DOESNT_CREATED, e);
        }
    }

    public static ConnectionPool getInstance(){
        if (!created.get()) {
            try {
                    lock.lock();
                    if (pool == null) {
                        pool = new ConnectionPool();
                        logger.log(Level.INFO,POOL_CREATED);
                        created.set(true);
                    }
                } finally{
                    lock.unlock();
                }
        }
        return pool;
    }
    @Override
    public ConnectionWrapper getConnection(){
        ConnectionWrapper connection = null;
        try{
            connection = availableConnections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
        return connection;
    }
    @Override
    public boolean releaseConnection(ConnectionWrapper connection){
        usedConnections.remove(connection);
        try {
            availableConnections.put(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
        return true;
    }

    public void closePool()  {
        try {
            for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
                ConnectionWrapper proxyConnection = availableConnections.take();
                proxyConnection.reallyClose();
            }
            logger.log(Level.INFO, POOL_DESTROYED);
        } catch (InterruptedException | SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            deregisterDrivers();
        }
    }

    private void deregisterDrivers()  {
        try {
            while (DriverManager.getDrivers().hasMoreElements()) {
                Driver driver = DriverManager.getDrivers().nextElement();
                DriverManager.deregisterDriver(driver);
            }
            logger.log(Level.INFO, DRIVERS_DEREGISTERED);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
    }
}

