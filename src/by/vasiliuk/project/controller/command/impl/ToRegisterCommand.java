package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;

import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.JspPath;

import javax.servlet.http.HttpServletRequest;

public class ToRegisterCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String registerPage = JspPath.REGISTER_PAGE;

        return registerPage;
    }
}