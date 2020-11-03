package by.vasiliuk.project.model.dao.impl;

import by.vasiliuk.project.model.dao.UserDao;
import by.vasiliuk.project.model.entity.User;
import by.vasiliuk.project.model.dao.DaoException;
import by.vasiliuk.project.model.pool.ConnectionPool;
import by.vasiliuk.project.model.pool.ConnectionWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.vasiliuk.project.model.dao.SqlProvider.*;
import static by.vasiliuk.project.model.dao.DaoProvider.*;



public class UserDaoImpl implements UserDao {

    private static final UserDaoImpl INSTANCE = new UserDaoImpl();

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<User> findById(long id) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ResultSet resultSet = null;
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection();
            PreparedStatement  preparedStatement = connectionWrapper.prepareStatement(SQL_FIND_USER_BY_ID)){
            preparedStatement.setLong(1, id);
             resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                        User user = new User(resultSet.getLong(USER_ID),
                        resultSet.getString(USER_NAME),
                        resultSet.getInt(USER_RATING));
            }
        } catch (SQLException e){
          throw new DaoException(e);
        } finally {
               close(resultSet);
        }
        return Optional.empty();
    }

    @Override
    public User findUserByPassword(String username) throws DaoException{
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ResultSet resultSet = null;
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()){
            PreparedStatement preparedStatement = connectionWrapper.prepareStatement(SQL_FIND_PASS_BY_USER_NAME);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String pass = resultSet.getString(USER_PASS);
            int role = resultSet.getInt(USER_ROLE);
            int status = resultSet.getInt(USER_STATUS);
            User user = new User();
            user.setUsername(username);
            user.setPassword(pass);
            user.setRole(role);
            user.setStatus(status);
            return user;
        } catch (SQLException e){
           throw new DaoException(e);
        } finally {
            close(resultSet);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()){

            String sql = SQL_FIND_ALL_USERS;
            //sql constant in dao
            PreparedStatement preparedStatement = connectionWrapper.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("---------0---------");
            while (resultSet.next()){
                System.out.println("-------1--------");
                User user = new User();
                user.setUsername(resultSet.getString(USER_NAME));
                user.setRating(resultSet.getFloat(USER_RATING));
                user.setRole(resultSet.getInt(USER_ROLE));
                user.setStatus(resultSet.getInt(USER_STATUS));
                users.add(user);
                //column label name in model
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("list----------"+users);
        return users;
    }

    @Override
    public boolean updateUser(String oldNickname, String nickName, String email) throws DaoException {
        return false;
    }

    @Override
    public boolean archiveUser(String deleteUserName) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        int save;
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()) {
            Connection connection = connectionWrapper.getConnection();
            String sql = SQL_USER_ARCHIVE;
            //refund status
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, deleteUserName);
            save = preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e);
        }
        return save == 1;
    }

    @Override
    public boolean save(String nickname, String email, String pass, int averageRating) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        int save;
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()) {
            Connection connection = connectionWrapper.getConnection();
            String sql = SQL_USER_SAVE;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nickname);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, pass);
            preparedStatement.setInt(4, averageRating);
            save = preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e);
        }
        return save==1;
    }

    @Override
    public long findIdByUsername(String username) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ResultSet resultSet = null;
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()){
            Connection connection = connectionWrapper.getConnection();
            String sql = SQL_FIND_BY_USER_NAME;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
           resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(USER_ID);
        }catch (SQLException e){
            throw new DaoException(e);
        } finally {
            close(resultSet);
        }
    }

    public void delete(long id) throws DaoException{
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try(ConnectionWrapper connectionWrapper = connectionPool.getConnection()){
            Connection connection = connectionWrapper.getConnection();
            String sql = SQL_USER_ARCHIVE;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }
}
