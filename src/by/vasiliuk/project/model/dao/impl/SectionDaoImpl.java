package by.vasiliuk.project.model.dao.impl;

import by.vasiliuk.project.model.dao.DaoException;
import by.vasiliuk.project.model.dao.SectionDao;
import by.vasiliuk.project.model.entity.Section;
import by.vasiliuk.project.model.pool.ConnectionPool;
import by.vasiliuk.project.model.pool.ConnectionWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.vasiliuk.project.model.dao.DaoProvider.*;
import static by.vasiliuk.project.model.dao.SqlProvider.*;

public class SectionDaoImpl implements SectionDao {
    private static final SectionDaoImpl INSTANCE = new SectionDaoImpl();

    public static SectionDaoImpl getInstance(){
        return INSTANCE;
    }

    public List<Section> getAll() throws DaoException {
        List<Section> sections = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()){
            Connection connection = connectionWrapper.getConnection();
            String sql = SQL_GET_SECTIONS;
            //sql constant in dao
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                sections.add(new Section(resultSet.getInt(SECTION_ID), resultSet.getString(SECTION_TITLE)));
            }
        } catch (SQLException e){
          throw new DaoException(e);
        }
        return sections;
    }

    public void save(String sectionName) throws DaoException{
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()){
            Connection connection = connectionWrapper.getConnection();
            String sql = SQL_CREATE_SECTIONS;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sectionName);
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(long id) throws DaoException{
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()){
            Connection connection = connectionWrapper.getConnection();
            String sql = SQL_REMOVE_SECTIONS;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}