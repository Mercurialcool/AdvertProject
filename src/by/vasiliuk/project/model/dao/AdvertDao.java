package by.vasiliuk.project.model.dao;

import by.vasiliuk.project.model.entity.Advert;

import java.util.List;
import java.util.Optional;

public interface AdvertDao extends BaseDao {
    List<Advert> findAll() throws DaoException;
    Optional<Advert> findById(long id) throws DaoException;
    void save(String advertTitle, String advertText) throws DaoException;
    List<Advert> findBySectionId(long id) throws DaoException;
    String editAdvert(String advertTitle, String advertText) throws DaoException;
    List<Advert> findUsersAdvertById(int userId) throws DaoException;
}