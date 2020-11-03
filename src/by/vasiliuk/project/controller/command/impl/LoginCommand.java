package by.vasiliuk.project.controller.command.impl;


import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.NameProvider;
import by.vasiliuk.project.model.entity.Advert;
import by.vasiliuk.project.model.entity.User;
import by.vasiliuk.project.service.impl.AdvertServiceImpl;
import by.vasiliuk.project.service.impl.UserServiceImpl;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.util.HashUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import  by.vasiliuk.project.controller.command.JspPath;

import java.util.List;

import static by.vasiliuk.project.controller.command.ParameterName.*;

public class LoginCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
//todo create localization at jsp
         String page;
         String username = request.getParameter(NAME);
         String pass = request.getParameter(PASSWORD);

        UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
        try {
            User user = userServiceImpl.logInUser(username);
            String passFromDb = user.getPassword();
            int status = user.getStatus();
            int role = user.getRole();
            boolean flag = HashUtil.check(pass, passFromDb);
            if(flag) {
                if(status == 1) {
                    page = JspPath.BLOCKED_USER;
                } else {
                    if (role == 0) {
                        AdvertServiceImpl advertServiceImpl = AdvertServiceImpl.getInstance();
                        List<Advert> adverts = advertServiceImpl.findAllAds();
                        request.setAttribute(NameProvider.ADD_LIST, adverts);
                        request.getSession(true).setAttribute("role", role);
                        request.getSession(true).setAttribute("user", user);
                        page = JspPath.AD_LIST;
                    } else  if (role == 1){
                         List<User>  users = userServiceImpl.findAllUsers();
                        request.setAttribute(NameProvider.USERS_LIST, users);
                        request.getSession(true).setAttribute("role", role);
                        page = JspPath.ADMIN_PAGE;
                    } else {
                        page = JspPath.LOGIN_PAGE;
                    }
                }
            } else {
                page = JspPath.LOGIN_PAGE;
            }
        } catch (ServiceException e) {
            logger.error("error in LoginService", e);
           page = JspPath.LOGIN_PAGE; /// other page?
        }
        return page;
    }

}