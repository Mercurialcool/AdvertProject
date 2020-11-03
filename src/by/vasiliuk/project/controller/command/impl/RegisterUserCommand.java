package by.vasiliuk.project.controller.command.impl;


import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.ParameterName;
import by.vasiliuk.project.service.impl.UserServiceImpl;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.util.HashUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import  by.vasiliuk.project.controller.command.JspPath;


public class RegisterUserCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

    String page;
String mail = request.getParameter(ParameterName.EMAIL);
        String nickname = request.getParameter(ParameterName.NICKNAME);
String regPassword = request.getParameter(ParameterName.REG_PASSWORD);
        String regPasswordRepeat = request.getParameter(ParameterName.REG_PASSWORD_REPEAT);
        if(!regPassword.equals(regPasswordRepeat)) {
            request.setAttribute("notEqualsPass", true);
            return JspPath.REGISTER_PAGE;
        }
try{
        UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
        regPassword = HashUtil.hash(regPassword);
        userServiceImpl.registerUser(nickname, mail, regPassword, "1");
        page = JspPath.LOGIN_PAGE;
      } catch (ServiceException e) {
           logger.error("error in register Service", e);
          throw new CommandException(e);
        }
        return page;
    }
}