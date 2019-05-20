package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.FileFactory;
import by.gsu.epamlab.factories.UserFactory;
import by.gsu.epamlab.interfaces.BaseController;
import by.gsu.epamlab.interfaces.IUserDAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet("/login")
public class LoginController extends BaseController {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String srtDAO = config.getInitParameter(Constants.KEY_DAO);
        if (UserFactory.getUserDAO() == null) {
            UserFactory.setGlobals(srtDAO);
        }
        String strPath = config.getServletContext().getRealPath("/WEB-INF");
        if (FileFactory.getFileManager() == null)
            FileFactory.create(strPath);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter(Constants.KEY_LOGIN);
        String password = request.getParameter(Constants.KEY_PASSWORD);
        try {
            check(login, password);
            IUserDAO userDAO = UserFactory.getUserDAO();
            User user = userDAO.getUser(login, password);
            HttpSession session = request.getSession();
            session.setAttribute(Constants.KEY_USER, user);
            forward(Constants.TASK_CONTR, request, response);
        } catch (DaoException exception) {
            forwardError(Constants.INDEX_PAGE, exception.getMessage(), request, response);
        }
    }

    private void check(String login, String password) throws DaoException {
        if (login == null || password == null)
            throw new DaoException(Constants.ERROR_NULL);
        login = login.trim();
        password = password.trim();
        if (login.equals(Constants.VALUE_EMPTY) || password.equals(Constants.VALUE_EMPTY))
            throw new DaoException(Constants.ERROR_EMPTY);
    }
}
