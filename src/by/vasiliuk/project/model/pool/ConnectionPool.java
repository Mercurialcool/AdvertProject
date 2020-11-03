package by.vasiliuk.project.model.pool;


import java.lang.reflect.InvocationTargetException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool implements Pool<ConnectionWrapper>{

    private static ConnectionPool pool;

    private static final String DB_HOST = "db.host";//todo replace as protected into PoolConfigurator
    private static final String DB_LOGIN = "db.login";
    private static final String DB_PASSWORD = "db.password";
    private static final String CONNECTION_POOL_SIZE = "connection-pool-size";
    private static final String DB_DRIVER = "db.driver-name";
    private static final String CONNECTION_TIMEOUT = "wait-connection-timeout";
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean created = new AtomicBoolean(false);
    private ArrayBlockingQueue<ConnectionWrapper> availableConnections;
    private ArrayBlockingQueue<ConnectionWrapper> usedConnections;
// double check singleton --- lock, atomic  todo
    private ConnectionPool(){
        Properties properties = PoolConfigurator.getConfigurator().getProperties();
                String url = properties.getProperty(DB_HOST);
                String user = properties.getProperty(DB_LOGIN);
                String pass = properties.getProperty(DB_PASSWORD);

            availableConnections = new ArrayBlockingQueue<>(Integer.
                    parseInt(properties.getProperty(CONNECTION_POOL_SIZE)));
            usedConnections = new ArrayBlockingQueue<>(Integer.
                    parseInt(properties.getProperty(CONNECTION_POOL_SIZE)));
            try{
            int poolSize = Integer.parseInt(properties.getProperty(CONNECTION_POOL_SIZE));
            Class.forName(properties.getProperty(DB_DRIVER)).getDeclaredConstructor().newInstance();
            for(int i = 0; i < poolSize; i++){
                availableConnections.add(new ConnectionWrapper(DriverManager.getConnection(url, user, pass)));
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException | ClassNotFoundException | SQLException e) {
            //todo fatal log
            throw new RuntimeException("Connection pool not created", e);
        }
        //add shutdown hook //todo check if pool created
    }

    public static ConnectionPool getInstance(){
        if (!created.get()) {
            try {
                    lock.lock();
                    if (pool == null) {
                        pool = new ConnectionPool();
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
            e.printStackTrace();
            //todo log or throw custom exception
        }
        return connection;
    }
    @Override
    public boolean releaseConnection(ConnectionWrapper connection){
        usedConnections.remove(connection);
        try {
            availableConnections.put(connection);//todo при попытке удаления проверить наличие объекта в userconnection (проверить есть ли он и нсли да. УДАЛИТЬ, нет - exception)
        } catch (InterruptedException e) {
            e.printStackTrace();
            //throw custom exception
        }
        return true;
    }

    @Override
    public void closePool() {
//todo  close availableConnections
       //todo  deregister drivers
    }
    }

