package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.JspPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ToCreateAdvertisementCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String createNewAdvert = JspPath.CREATE_ADVERTISEMENT;

        return createNewAdvert;
    }
}
