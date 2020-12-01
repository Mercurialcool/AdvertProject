package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.service.impl.AdvertServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static by.vasiliuk.project.controller.command.JspPath.RETURN_PAGE;
import static by.vasiliuk.project.controller.command.NameProvider.SECTION_SELECTED;
import static by.vasiliuk.project.controller.command.NameProvider.USER_ID;
import static by.vasiliuk.project.controller.command.ParameterName.*;

public class NewAdvertCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String title = request.getParameter(ADVERT_TITLE);
        String text = request.getParameter(ADVERT_TEXT);
        long userId = (Long) request.getSession().getAttribute(USER_ID);
        String section = request.getParameter(SECTION_SELECTED);//todo constants
        AdvertServiceImpl advertServiceImpl = AdvertServiceImpl.getInstance();
        try {
            advertServiceImpl.saveAdvert(title, text, userId, section);
        } catch (ServiceException e) {
            e.printStackTrace();//todo logs
        }
        return RETURN_PAGE;
    }
}