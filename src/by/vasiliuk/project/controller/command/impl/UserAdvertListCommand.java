package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.NameProvider;
import by.vasiliuk.project.model.entity.Advert;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.service.impl.AdvertServiceImpl;
import by.vasiliuk.project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserAdvertListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String userId = request.getParameter("user_id");
        String userName = request.getParameter("userName");
        AdvertServiceImpl advertServiceImpl = AdvertServiceImpl.getInstance();
        List<Advert> adverts;
        try {
        adverts = advertServiceImpl.findUsersAdvertById(Integer.parseInt(userId));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(NameProvider.ADVERT_LIST, adverts);
        return null;//new jsp(edit users jsp);
    }
}
