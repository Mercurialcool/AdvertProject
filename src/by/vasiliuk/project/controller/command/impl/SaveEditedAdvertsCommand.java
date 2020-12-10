package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.JspPath;
import by.vasiliuk.project.controller.command.NameProvider;
import by.vasiliuk.project.model.entity.Advert;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.service.impl.AdvertServiceImpl;
import by.vasiliuk.project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vasiliuk.project.controller.command.NameProvider.*;

public class SaveEditedAdvertsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        List<Advert> original = (List<Advert>)request.getSession().getAttribute(NameProvider.ORIGINAL_ADVERT_LIST);
        Map<String, String[]> changeMap = request.getParameterMap();
        List<Advert> changeList = new ArrayList<>();
        String[] title = changeMap.get(ADVERT_TITLE);
        String[] text = changeMap.get(ADVERT_TEXT);
        for(int i = 0; i < original.size(); i++){
            Advert temp = original.get(i);
          if(!text[i].equals(temp.getText()) ||
            !title[i].equals(temp.getTitle())){
              temp.setText(text[i]);
              temp.setTitle(title[i]);
              changeList.add(temp);
          }
        }
        AdvertServiceImpl advertServiceImpl = AdvertServiceImpl.getInstance();
        try {
         int result = advertServiceImpl.updateAdvertsByUser(changeList);
         if (result > 0) {
             request.setAttribute(ADVERT_LIST, changeList);
             request.setAttribute(CHANGE_MESSAGE,CHANGES_DONE  + result);
         } else {
             request.setAttribute(ADVERT_LIST, original);
             request.setAttribute(CHANGE_MESSAGE, NO_CHANGES);
         }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return JspPath.EDIT_ADVERT;
    }
}
