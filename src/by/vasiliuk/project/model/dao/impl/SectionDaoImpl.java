package by.vasiliuk.project.model.dao.impl;

import by.vasiliuk.project.model.dao.DaoException;
import by.vasiliuk.project.model.dao.SectionDao;
import by.vasiliuk.project.model.entity.Section;
import by.vasiliuk.project.model.pool.ConnectionPool;
import by.vasiliuk.project.model.pool.ConnectionWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.vasiliuk.project.model.dao.DaoProvider.*;
import static by.vasiliuk.project.model.dao.SqlProvider.*;

public class SectionDaoImpl implements SectionDao {
    private static final SectionDaoImpl INSTANCE = new SectionDaoImpl();
    public static SectionDaoImpl getInstance(){
        return INSTANCE;
    }
}