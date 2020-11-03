package by.vasiliuk.project.model.dao;

import by.vasiliuk.project.model.entity.Advert;
import by.vasiliuk.project.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao {
    Optional<User> findById(long id) throws DaoException ;
    boolean save(String nickname, String email, String pass, int averageRating) throws DaoException ;
    long findIdByUsername(String username) throws DaoException ;
    User findUserByPassword(String username) throws DaoException ;
    List<User> findAll() throws DaoException;
    boolean updateUser(String oldNickname, String nickName, String email) throws DaoException;
    boolean archiveUser(String deleteUserName) throws DaoException;
}
