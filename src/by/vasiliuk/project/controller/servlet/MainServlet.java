package by.vasiliuk.project.controller.servlet;

import by.vasiliuk.project.controller.command.Command;
import by.vasiliuk.project.controller.command.CommandException;
import by.vasiliuk.project.controller.command.CommandProvider;
import by.vasiliuk.project.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vasiliuk.project.controller.command.NameProvider.COMMAND;

@WebServlet("/controller")
public class MainServlet extends HttpServlet {
   static Logger logger = LogManager.getLogger();


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
            logger.error("invalid......{} {} {} ", command.getClass(), commandStr, e.getMessage());
            response.sendRedirect("/error/error.jsp");//todo const
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
    @Override
    public void destroy() {
        ConnectionPool.getInstance().closePool();
    }
}
