package by.vasiliuk.project.service;

import by.vasiliuk.project.model.entity.User;

import java.security.Provider;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User logInUser(String username) throws ServiceException;
    long findUserId(String username) throws ServiceException;
     void registerUser(String nickname, String email, String pass, String averageRating) throws ServiceException;
     Optional<User> findUser(long id) throws ServiceException;
     Optional<String> findUsernameById(long id) throws ServiceException;
     List<User> findAllUsers() throws ServiceException;
     boolean updateUser(String oldNickname, String nickName, String email) throws ServiceException;
     boolean deleteUser(String deleteUserName) throws ServiceException;
}
