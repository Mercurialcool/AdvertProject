package by.vasiliuk.project.model.pool;

public interface Pool<T> {
    T getConnection();
    boolean releaseConnection(T connection);
    void closePool();
}
