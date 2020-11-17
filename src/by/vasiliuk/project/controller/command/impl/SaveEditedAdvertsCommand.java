package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.NameProvider;
import by.vasiliuk.project.model.entity.Advert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class SaveEditedAdvertsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Advert> original = (List<Advert>)request.getSession().getAttribute(NameProvider.ORIGINAL_ADVERT_LIST);
        System.out.println(original);
        Map<String, String[]> changeMap = request.getParameterMap();
        System.out.println(changeMap);

//        String[] title = changeMap.get("advert_title");
//        String[] text = changeMap.get("advert_text");
//        for(int i = 1,){
//            Advert temp = original.get(i);
//            text[i].equals(temp.getText());
//            title[i].equals(temp.getText());
//        }

        return null;
    }
}
