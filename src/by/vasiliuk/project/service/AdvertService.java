package by.vasiliuk.project.service;

import by.vasiliuk.project.model.dao.DaoException;
import by.vasiliuk.project.model.entity.Advert;

import java.util.List;
import java.util.Optional;

public interface AdvertService {
     List<Advert> findAllAds() throws ServiceException;
     Optional<Advert> findAdById(long id) throws ServiceException;
     List<Advert> findBySectionId(long id) throws ServiceException;
     void saveAdvert(String title, String text, long id, String section) throws ServiceException;
     List<Advert> editAdvert(String advertTitle, String advertText) throws ServiceException;
     List<Advert> findUsersAdvertById(int userId) throws ServiceException;
}
