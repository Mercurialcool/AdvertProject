package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.JspPath;
import by.vasiliuk.project.controller.command.ParameterName;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.service.impl.AdvertServiceImpl;
import by.vasiliuk.project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class AddAdvertisementCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String advertTitle = request.getParameter(ParameterName.ADVERT_TITLE);
        String advertPlot = request.getParameter(ParameterName.ADVERT_PLOT);
        String page = JspPath.AD_LIST;


        AdvertServiceImpl advertServiceImpl = AdvertServiceImpl.getInstance();
        try {
            advertServiceImpl.saveAdvert(advertTitle, advertPlot);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
