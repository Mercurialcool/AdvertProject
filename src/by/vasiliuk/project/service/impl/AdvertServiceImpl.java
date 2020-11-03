package by.vasiliuk.project.service.impl;

import by.vasiliuk.project.model.dao.UserDao;
import by.vasiliuk.project.model.dao.impl.AdvertDaoImpl;
import by.vasiliuk.project.model.dao.AdvertDao;
import by.vasiliuk.project.model.dao.DaoException;
import by.vasiliuk.project.model.dao.impl.UserDaoImpl;
import by.vasiliuk.project.model.entity.Advert;
import by.vasiliuk.project.service.AdvertService;
import by.vasiliuk.project.service.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdvertServiceImpl implements AdvertService {

    private static final AdvertServiceImpl INSTANCE = new AdvertServiceImpl();

    public static AdvertServiceImpl getInstance() {
        return INSTANCE;
    }

    public List<Advert> findAllAds() throws ServiceException {
        AdvertDao advertDao;
        try {
            advertDao = AdvertDaoImpl.getInstance();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        try {
            return advertDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    public List<Advert> findUsersAdvertById(int userId) throws ServiceException{
        List<Advert> advert = new ArrayList<>();
        try {
            AdvertDao advertDao;
            advertDao = AdvertDaoImpl.getInstance();
            advert = advertDao.findUsersAdvertById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return advert;
    }


    public Optional<Advert> findAdById(long id)throws ServiceException{
        AdvertDao advertDao = null;
        try {
            advertDao = AdvertDaoImpl.getInstance();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        Optional<Advert> advert= null;
        try {
            advert= advertDao.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return advert;
    }

    public List<Advert> findBySectionId(long id)throws ServiceException{
        AdvertDao advertDao;
        try {
            advertDao = AdvertDaoImpl.getInstance();
            return advertDao.findBySectionId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
//add=save
    public void saveAdvert(String title, String text) throws ServiceException{
        AdvertDao advertDao;
        try {
            advertDao = AdvertDaoImpl.getInstance();
            advertDao.save(title, text);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Advert> editAdvert(String advertTitle, String advertText) throws ServiceException {
        return null;
    }
}