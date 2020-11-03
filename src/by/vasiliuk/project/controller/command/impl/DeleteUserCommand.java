package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.JspPath;
import by.vasiliuk.project.controller.command.NameProvider;
import by.vasiliuk.project.model.entity.User;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String deleteUserName = request.getParameter("del_user");

        UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
        List<User> users;
        try {
            userServiceImpl.deleteUser(deleteUserName);
            users = userServiceImpl.findAllUsers();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(NameProvider.USERS_LIST, users);
        return JspPath.ADMIN_PAGE;
    }
}
