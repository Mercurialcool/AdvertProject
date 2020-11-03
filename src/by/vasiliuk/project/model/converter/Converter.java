package by.vasiliuk.project.model.converter;

import by.vasiliuk.project.service.ServiceException;

public interface Converter<T, U> {
    T convert(U u) throws ServiceException;
}
