package by.vasiliuk.project.service.impl;

import by.vasiliuk.project.controller.validator.Validator;
import by.vasiliuk.project.model.dao.UserDao;
import by.vasiliuk.project.model.entity.User;
import by.vasiliuk.project.model.dao.DaoException;
import by.vasiliuk.project.model.dao.impl.UserDaoImpl;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    public User logInUser(String username) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        User user;
        try {
            user = userDao.findUserByPassword(username);
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public long findUserId(String username) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.findIdByUsername(username);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void registerUser(String nickname, String email, String pass, String averageRating) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            userDao.save(nickname, email, pass, Integer.parseInt(averageRating));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<User> findUser(long id) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try {
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<String> findUsernameById(long id) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        Optional<User> user;
        try {
            user = userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user.map(User::getUsername);
    }

    public boolean updateUser(String oldNickname, String nickName, String email) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        boolean updateFlag = false;
        if(nickName != null && Validator.isValidName(nickName)) {
            if(email != null && Validator.isValidEmail(email))
            try {
                updateFlag = userDao.updateUser(oldNickname, nickName, email);
            } catch (DaoException throwables) {
                throw new ServiceException(throwables);
            }
        }
        return updateFlag;

    }

    @Override
    public boolean deleteUser(String deleteUserName) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean result;
        try {
            result = userDao.archiveUser(deleteUserName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    public List<User> findAllUsers() throws ServiceException {
        UserDao userDao;
        try {
            userDao = UserDaoImpl.getInstance();
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
