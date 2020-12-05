package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.JspPath;
import by.vasiliuk.project.controller.command.NameProvider;
import by.vasiliuk.project.model.entity.Advert;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.service.impl.AdvertServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllAdvertsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        AdvertServiceImpl advertServiceImpl = AdvertServiceImpl.getInstance();
        List<Advert> adverts;
        try {
            adverts = advertServiceImpl.findAllAds();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(NameProvider.ADD_LIST, adverts);
        return JspPath.AD_LIST;
    }
}
