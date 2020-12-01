package by.vasiliuk.project.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

import static by.vasiliuk.project.controller.command.NameProvider.*;

@WebFilter(dispatcherTypes = DispatcherType.INCLUDE,filterName = REFERER_FILTER, urlPatterns = ALL_JSP)
public class RefererFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        StringBuffer url = httpRequest.getRequestURL();
        String page = url.substring(url.lastIndexOf("/"));
        HttpSession session = httpRequest.getSession(true);
        session.setAttribute( CURRENT_REFERER, page);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
