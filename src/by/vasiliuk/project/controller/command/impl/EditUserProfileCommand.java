package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.JspPath;
import by.vasiliuk.project.controller.command.ParameterName;
import by.vasiliuk.project.model.entity.User;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static by.vasiliuk.project.controller.command.NameProvider.UPDATE_USER;
import static by.vasiliuk.project.controller.command.NameProvider.USER;

public class EditUserProfileCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String mail = request.getParameter(ParameterName.EMAIL);
        String nickname = request.getParameter(ParameterName.NICKNAME);
        User user = (User) request.getSession().getAttribute(USER);
        String oldNickname = user.getUsername();
        UserServiceImpl service = UserServiceImpl.getInstance();
        boolean update;
        try {
            update = service.updateUser(oldNickname, nickname, mail);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(UPDATE_USER, update);
        if (update){
            user.setUsername(nickname);
            request.getSession(true).setAttribute(USER, user);
        }
        return JspPath.PROFILE_PAGE;
    }
}
