package by.vasiliuk.project.controller.servlet;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/controller")
public class MainServlet extends HttpServlet {
   static Logger logger = LogManager.getLogger();
    public static final String COMMAND = "command";

    public MainServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;
        String commandStr = request.getParameter(COMMAND);
        Command command = CommandProvider.getCommand(commandStr);
        try {
            page = command.execute(request);
        } catch (CommandException e) {
            logger.error("invalid......" + command.getClass() + " " + commandStr, e);
            response.sendRedirect("/error/error.jsp");
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
