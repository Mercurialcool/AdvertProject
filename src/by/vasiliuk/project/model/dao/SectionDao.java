package by.vasiliuk.project.model.dao;

import by.vasiliuk.project.model.entity.Section;

import java.util.List;

public interface SectionDao {
    List<Section> getAll() throws DaoException;
    void save(String sectionName) throws DaoException;
    void delete(long id) throws DaoException;
}
