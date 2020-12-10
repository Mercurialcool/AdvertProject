package by.vasiliuk.project.model.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static by.vasiliuk.project.model.dao.DaoProvider.RESULT_SET_NOT_CLOSED;
import static by.vasiliuk.project.model.dao.DaoProvider.STATEMENT_DOESNT_CLOSED;

public interface BaseDao {
    Logger logger = LogManager.getLogger();
    default void close(ResultSet set) {
        if(set != null){
            try {
                set.close();
            } catch (SQLException e) {
                logger.error(RESULT_SET_NOT_CLOSED, e);
            }
        }
    }
    default void close(Statement statement){
        if(statement != null){
            try{
                statement.close();
            } catch (SQLException e) {
                logger.error(STATEMENT_DOESNT_CLOSED, e);
            }
        }
    }
}
