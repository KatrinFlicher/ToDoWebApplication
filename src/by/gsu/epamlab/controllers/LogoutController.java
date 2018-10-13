package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.interfaces.BaseController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet("/logout")
public class LogoutController extends BaseController {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(Constants.INDEX_PAGE);
    }
}
