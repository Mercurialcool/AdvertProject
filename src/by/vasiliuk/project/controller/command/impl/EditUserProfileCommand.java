package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.JspPath;
import by.vasiliuk.project.controller.command.ParameterName;
import by.vasiliuk.project.model.entity.User;
import by.vasiliuk.project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class EditUserProfileCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String mail = request.getParameter(ParameterName.EMAIL);
        String nickname = request.getParameter(ParameterName.NICKNAME);
        User user = (User) request.getSession().getAttribute("user");
        String oldNickname = user.getUsername();
        UserServiceImpl service = UserServiceImpl.getInstance();
        boolean update = false;//service.updateUser(oldNickname, nickname, mail);
        request.setAttribute("update_user", update);
        if (update){
            user.setUsername(nickname);
            request.getSession(true).setAttribute("user", user);
          //  request.setAttribute("update_user", true);
        }
        return JspPath.PROFILE_PAGE;
    }
}
