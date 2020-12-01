package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.JspPath;
import by.vasiliuk.project.controller.command.NameProvider;
import by.vasiliuk.project.model.entity.Advert;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.vasiliuk.project.controller.command.NameProvider.ADVERT_TEXT;
import static by.vasiliuk.project.controller.command.NameProvider.ADVERT_TITLE;

public class SaveEditedAdvertsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        List<Advert> original = (List<Advert>)request.getSession().getAttribute(NameProvider.ORIGINAL_ADVERT_LIST);
        System.out.println(original);
        Map<String, String[]> changeMap = request.getParameterMap();
        System.out.println(changeMap);
        List<Advert> changeList = new ArrayList<>();
        String[] title = changeMap.get(ADVERT_TITLE);
        String[] text = changeMap.get(ADVERT_TEXT);//todo const
        for(int i = 0; i < original.size(); i++){
            Advert temp = original.get(i);
          if(!text[i].equals(temp.getText()) ||
            !title[i].equals(temp.getTitle())){
              changeList.add(temp);
          }
        }
        //todo call previous service, and changeMsg

        return JspPath.ADMIN_PAGE;
    }
}
