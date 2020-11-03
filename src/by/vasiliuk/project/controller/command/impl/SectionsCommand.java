package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.JspPath;
import by.vasiliuk.project.model.entity.Section;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.service.impl.SectionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SectionsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String sectionsPage = JspPath.SECTIONS_PAGE;
        SectionServiceImpl service = SectionServiceImpl.getInstance();
        List<Section> list;
        try {
            list = service.findAll();
        } catch (ServiceException e) {
           throw new CommandException(e);
        }
        if (list != null) {
            request.setAttribute("sections", list);
        } else {
            request.setAttribute("sections", List.of("empty"));
        }

        return sectionsPage;
    }

}