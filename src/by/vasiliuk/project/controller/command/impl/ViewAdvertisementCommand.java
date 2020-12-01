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
import java.util.Optional;

import static by.vasiliuk.project.controller.command.NameProvider.ID;

public class ViewAdvertisementCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String id = request.getParameter(ID);
        int advertId = Integer.parseInt(id);

        AdvertServiceImpl advertServiceImpl = AdvertServiceImpl.getInstance();
        Optional<Advert> advert;
        try {
            advert = advertServiceImpl.findAdById(advertId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(NameProvider.ADVERT, advert.orElse(new Advert()));
        return JspPath.AD_VIEW;
    }
}
