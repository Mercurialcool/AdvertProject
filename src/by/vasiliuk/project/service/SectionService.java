package by.vasiliuk.project.service;

import by.vasiliuk.project.model.entity.Section;

import java.util.List;

public interface SectionService {
     List<Section> findAll() throws ServiceException;
}
