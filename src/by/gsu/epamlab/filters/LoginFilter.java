package by.gsu.epamlab.filters;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.constants.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter{
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute(Constants.KEY_USER);
        if (user == null) {
            session.invalidate();
            HttpServletResponse httpResponse =
                    (HttpServletResponse) response;
            httpResponse.sendRedirect(Constants.LOGIN_PAGE);
            return;
        }
        chain.doFilter(request, response);
    }
}

