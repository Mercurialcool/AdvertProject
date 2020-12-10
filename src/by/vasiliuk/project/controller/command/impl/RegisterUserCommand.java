package by.vasiliuk.project.controller.command.impl;


import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.ParameterName;
import by.vasiliuk.project.controller.validator.Validator;
import by.vasiliuk.project.service.impl.UserServiceImpl;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.util.HashUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import  by.vasiliuk.project.controller.command.JspPath;

import static by.vasiliuk.project.controller.command.NameProvider.*;


public class RegisterUserCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        boolean valid = true;
        String page;
        String mail = request.getParameter(ParameterName.EMAIL);
        String nickname = request.getParameter(ParameterName.NICKNAME);
        String regPassword = request.getParameter(ParameterName.REG_PASSWORD);
        if(!Validator.isValidName(nickname) ) {
            request.setAttribute(INCORRECT_NICK, INCORRECT_NICK_FORMAT + nickname);
            valid = false;
        }
        if(!Validator.isValidPassword(regPassword)) {
            request.setAttribute(INCORRECT_PASS, INCORRECT_PASS_FORMAT + regPassword);
            valid = false;
        }
        if(!Validator.isValidEmail(mail)) {
            request.setAttribute(INCORRECT_MAIL, INCORRECT_MAIL_FORMAT + mail);
            valid = false;
        }
        if (!valid) {
            return JspPath.REGISTER_PAGE;
        }
        String regPasswordRepeat = request.getParameter(ParameterName.REG_PASSWORD_REPEAT);
        if(!regPassword.equals(regPasswordRepeat)) {
            request.setAttribute(NOT_EQUAL_PASS, true);
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