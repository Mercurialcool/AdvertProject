package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.service.impl.UserServiceImpl;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.model.entity.Advert;
import by.vasiliuk.project.service.impl.AdvertServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.vasiliuk.project.controller.command.NameProvider.ADVERTS_BY_SECTION;

public class GetAdvertsBySectionIdCommand implements Command {

    private  long id;

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Advert> advertTos = new ArrayList<>();
        AdvertServiceImpl advertServiceImpl = AdvertServiceImpl.getInstance();
        List<Advert> adverts ;
        try {
            adverts = advertServiceImpl.findBySectionId(id);
            UserServiceImpl userServiceImpl = new UserServiceImpl();
        Optional<String> username;
        for(Advert advert : adverts){
            username = userServiceImpl.findUsernameById(advert.getId());
           String name = username.orElseThrow(CommandException::new);
           advertTos.add(new Advert(advert.getId(), advert.getTitle(),
                        advert.getText(), name));
        }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return ADVERTS_BY_SECTION;
    }
}
