package by.vasiliuk.project.service.impl;

import by.vasiliuk.project.model.dao.DaoException;
import by.vasiliuk.project.model.dao.impl.SectionDaoImpl;
import by.vasiliuk.project.model.entity.Section;
import by.vasiliuk.project.service.SectionService;
import by.vasiliuk.project.service.ServiceException;

import java.util.List;

public class SectionServiceImpl {
    private static final SectionServiceImpl INSTANCE = new SectionServiceImpl();

    public static SectionServiceImpl getInstance() {
        return INSTANCE;
    }


}