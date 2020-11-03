package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import javax.servlet.http.HttpServletRequest;

public class LanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter("language");
        if("ru".equalsIgnoreCase(language)){
            request.getSession().setAttribute("locale", "ru");
        } else {
            request.getSession().setAttribute("locale", "en");
        }
        String path = (String)request.getSession().getAttribute("current_referer");
        return "/WEB-INF/jsp/" + path;
    }
}
