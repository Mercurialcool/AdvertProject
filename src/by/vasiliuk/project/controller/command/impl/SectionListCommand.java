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

public class SectionListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String section = request.getParameter("section");
        request.setAttribute("section_selected", section);
        Section sectionType = Section.valueOf(section);
        int sectionId = sectionType.getId();
        AdvertServiceImpl advertServiceImpl = AdvertServiceImpl.getInstance();
        try {
            List<Advert> advertList = advertServiceImpl.findAdvertBySection(sectionId);
            request.setAttribute("section_adverts", advertList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return JspPath.SECTION_ADVERT;
    }
}
