package by.vasiliuk.project.model.dao.impl;

import by.vasiliuk.project.model.dao.AdvertDao;
import by.vasiliuk.project.model.dao.DaoException;
import by.vasiliuk.project.model.entity.Advert;
import by.vasiliuk.project.model.entity.Section;
import by.vasiliuk.project.model.pool.ConnectionPool;
import by.vasiliuk.project.model.pool.ConnectionWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static by.vasiliuk.project.model.dao.SqlProvider.*;
import static by.vasiliuk.project.model.dao.DaoProvider.*;

public class AdvertDaoImpl implements AdvertDao {

    private static final AdvertDaoImpl INSTANCE = new AdvertDaoImpl();

    public static AdvertDaoImpl getInstance() throws DaoException {
        return INSTANCE;
    }

    @Override
    public List<Advert> findAll() throws DaoException{
        List<Advert> adverts = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        PreparedStatement preparedStatement = null;
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()){

            String sql = SQL_FIND_ALL_ADDS;
            preparedStatement = connectionWrapper.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                adverts.add(new Advert(resultSet.getInt(ADVERT_ID),
                        resultSet.getString(ADVERT_TITLE),
                        resultSet.getString(ADVERT_TEXT)));
            }

        } catch (SQLException e){
            throw new DaoException(e);
        }
        finally {
            close(preparedStatement);

        }
        return adverts;
    }
    @Override
    public Optional<Advert> findById(int advertId) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()){
            Connection connection = connectionWrapper.getConnection();
            String sql = SQL_FIND_ADVERT_BY_ID;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, advertId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Advert advert = new Advert();
                advert.setId(resultSet.getInt(ADVERT_ID));
                advert.setTitle(resultSet.getString(ADVERT_TITLE));
                advert.setText(resultSet.getString(ADVERT_TEXT));
                int sectionId = resultSet.getInt(SECTIONS_SECTION_ID);
                String sectionName = Section.getName(sectionId);
                advert.setSection(sectionName);
                int userId = resultSet.getInt(USERS_USER_ID);
                advert.setUserName(String.valueOf(userId));
                return Optional.of(advert);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e){
            throw new DaoException(e);
        }
    }

    @Override
    public void save(String advertTitle, String advertText, long id, String section) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()){
            Connection connection = connectionWrapper.getConnection();
            String sql = SQL_ADVERT_SAVE;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, advertTitle);
            preparedStatement.setString(2, advertText);
            preparedStatement.setInt(3, (int)id);
            preparedStatement.setString(4, section);
            preparedStatement.execute();
        } catch (SQLException e){
            throw new DaoException(e);
        }
    }


    public void delete(long id) throws DaoException{
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()){
            Connection connection = connectionWrapper.getConnection();
            String sql = SQL_ADVERT_DELETE;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e){
            throw new DaoException(e);
        }
    }

    public List<Advert> findUsersAdvertById(int userId) throws DaoException{
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        List<Advert> adverts = new ArrayList<>();
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()) {
        Connection connection = connectionWrapper.getConnection();
            String sql = SQL_FIND_USERS_ADVERTISEMENTS;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Advert advert = new Advert();
                advert.setId(resultSet.getInt(1));
                advert.setTitle(resultSet.getString(2));
                advert.setText(resultSet.getString(3));
                adverts.add(advert);
            }
            return adverts;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<Advert> findAdvertBySection(int sectionId) throws DaoException {
        List<Advert> adverts = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()){
            Connection connection = connectionWrapper.getConnection();
            String sql = SQL_FIND_BY_SECTION_ADVERT;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, sectionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                adverts.add(new Advert(resultSet.getInt(ADVERT_ID),
                        resultSet.getString(ADVERT_TEXT),
                        resultSet.getString(ADVERT_TITLE)));
            }
        } catch (SQLException e){
            throw new DaoException(e);
        }
        return adverts;
    }

    public int editAdvert(List<Advert> list) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        int numberUpdate = 0;
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()) {
            Connection connection = connectionWrapper.getConnection();
            String sql = SQL_ADVERTISEMENT_UPDATE;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (Advert advert: list) {
                preparedStatement.setString(1, advert.getTitle());
                preparedStatement.setString(2,advert.getText());
                preparedStatement.setInt(3, advert.getId());
                numberUpdate = preparedStatement.executeUpdate();
            }
        } catch (SQLException e){
            throw new DaoException(e);
        }

        return numberUpdate;
    }

    @Override
    public List<Advert> findBySectionId(long id) throws DaoException {
        List<Advert> adverts = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()){
            Connection connection = connectionWrapper.getConnection();
            String sql = SQL_FIND_BY_SECTION_ID;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                adverts.add(new Advert(resultSet.getInt(ADVERT_ID),
                        resultSet.getString(ADVERT_TEXT),
                        resultSet.getString(ADVERT_TITLE)));
            }
        } catch (SQLException e){
            throw new DaoException(e);
        }
        return adverts;
    }
}
