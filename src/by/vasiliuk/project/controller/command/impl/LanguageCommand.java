package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import javax.servlet.http.HttpServletRequest;

import static by.vasiliuk.project.controller.command.NameProvider.*;

public class LanguageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter(LANGUAGE);
        if(RUSSIAN.equalsIgnoreCase(language)){
            request.getSession().setAttribute(LOCALE, RUSSIAN);
        } else {
            request.getSession().setAttribute(LOCALE, ENGLISH);
        }
        String path = (String)request.getSession().getAttribute(REFERER);
        return JSP_TEMP + path;
    }
}
