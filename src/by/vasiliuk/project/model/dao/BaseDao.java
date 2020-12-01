package by.vasiliuk.project.model.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface BaseDao {
    Logger logger = LogManager.getLogger();
    default void close(ResultSet set) {
        if(set != null){
            try {
                set.close();
            } catch (SQLException e) {
                logger.error("result set is not closed", e);//todo const?
            }
        }
    }
    default void close(Statement statement){
        if(statement != null){
            try{
                statement.close();
            } catch (SQLException e) {
                logger.error("Statement is not closed", e);
            }
        }
    }
}
