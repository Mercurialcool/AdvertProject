package by.vasiliuk.project.controller.command.impl;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.JspPath;
import by.vasiliuk.project.model.entity.Advert;
import by.vasiliuk.project.model.entity.Section;
import by.vasiliuk.project.service.ServiceException;
import by.vasiliuk.project.service.impl.AdvertServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.vasiliuk.project.controller.command.NameProvider.*;

public class SectionListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String section = request.getParameter(SECTION);
        request.setAttribute(SECTION_SELECTED, section);
        Section sectionType = Section.valueOf(section);
        int sectionId = sectionType.getId();
        AdvertServiceImpl advertServiceImpl = AdvertServiceImpl.getInstance();
        try {
            List<Advert> advertList = advertServiceImpl.findAdvertBySection(sectionId);
            request.setAttribute(SECTION_ADVERTS, advertList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return JspPath.SECTION_ADVERT;
    }
}
