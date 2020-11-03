package by.vasiliuk.project.service.impl;

import by.vasiliuk.project.model.dao.DaoException;
import by.vasiliuk.project.model.dao.impl.SectionDaoImpl;
import by.vasiliuk.project.model.entity.Section;
import by.vasiliuk.project.service.SectionService;
import by.vasiliuk.project.service.ServiceException;

import java.util.List;

public class SectionServiceImpl implements SectionService {
    private static final SectionServiceImpl INSTANCE = new SectionServiceImpl();

    public static SectionServiceImpl getInstance() {
        return INSTANCE;
    }

    public List<Section> findAll() throws ServiceException {
        SectionDaoImpl sectionDaoImpl = SectionDaoImpl.getInstance();
        List<Section> sections;
        try {
            sections = sectionDaoImpl.getAll();
        } catch (DaoException e) {
           throw  new ServiceException(e);
        }
        return sections;
    }
}