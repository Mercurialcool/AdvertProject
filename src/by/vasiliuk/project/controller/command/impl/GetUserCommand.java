package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.model.entity.User;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static by.vasiliuk.project.controller.command.JspPath.GET_USER_BY_ID;
import static by.vasiliuk.project.controller.command.NameProvider.*;

public class GetUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String strId = request.getParameter(USER_ID_U);


        long id = Long.valueOf(strId);
        UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
        Optional<User> userOptional;
        try {
            userOptional = userServiceImpl.findUser(id);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        User user = userOptional.orElse(new User(0, NO_NAME, 0));
        request.setAttribute(USER_TO, user);
        return GET_USER_BY_ID;
    }
}
