package by.vasiliuk.project.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter(dispatcherTypes = DispatcherType.INCLUDE,filterName = "RefererFilter", urlPatterns = "*.jsp")
public class RefererFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        StringBuffer url = httpRequest.getRequestURL();
        String page = url.substring(url.lastIndexOf("/"));
        HttpSession session = httpRequest.getSession(true);
        session.setAttribute( "current_referer", page);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
