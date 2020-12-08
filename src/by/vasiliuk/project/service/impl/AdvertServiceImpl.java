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
    private AdvertServiceImpl(){}
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
        List<Advert> advert;
        try {
            AdvertDao advertDao = AdvertDaoImpl.getInstance();
            advert = advertDao.findUsersAdvertById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return advert;
    }

    public int updateAdvertsByUser(List<Advert> changeList)  throws ServiceException{
        AdvertDao advertDao;
        try {
            advertDao = AdvertDaoImpl.getInstance();
            return advertDao.editAdvert(changeList);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Advert> findAdvertBySection(int sectionId) throws ServiceException {
        AdvertDao advertDao;
        try {
            advertDao = AdvertDaoImpl.getInstance();
            return advertDao.findAdvertBySection(sectionId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    public Optional<Advert> findAdById(int advertId)throws ServiceException{
        AdvertDao advertDao;
        Optional<Advert> advert;
        try {
            advertDao = AdvertDaoImpl.getInstance();
            advert= advertDao.findById(advertId);
        } catch (DaoException e) {
            throw new ServiceException(e);
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
    public void saveAdvert(String title, String text, long id, String section) throws ServiceException{
        AdvertDao advertDao;
        try {
            advertDao = AdvertDaoImpl.getInstance();
            advertDao.save(title, text, id, section);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Advert> editAdvert(String advertTitle, String advertText) throws ServiceException {
        return null;
    }
}
