package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.Role;
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

//@WebServlet("/register")
public class RegistrateController extends BaseController {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String srtDAO = config.getInitParameter(Constants.KEY_DAO);
        if (UserFactory.getUserDAO() == null) {
            UserFactory.setGlobals(srtDAO);
        }
        String strPath = config.getServletContext().getRealPath("/WEB-INF\"");
        if (FileFactory.getFileManager() == null)
            FileFactory.create(strPath);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter(Constants.KEY_LOGIN);
        String password = request.getParameter(Constants.KEY_PASSWORD);
        String name = request.getParameter(Constants.KEY_NAME);
        String email = request.getParameter(Constants.KEY_EMAIL);
        try {
            check(login, password, name, email);
            IUserDAO userDAO = UserFactory.getUserDAO();
            User user = new User(login, Role.USER, name, email);
            if (userDAO.addUser(user, password)) {
                HttpSession session = request.getSession();
                session.setAttribute(Constants.KEY_USER, user);
                response.sendRedirect(Constants.TASK_CONTR);
            } else throw new DaoException(Constants.ERROR_USER);
        } catch (DaoException e) {
            forwardError(Constants.INDEX_PAGE, e.getMessage(), request, response);
        }
    }

    private void check(String login, String password, String name, String email) throws DaoException {
        if (login == null || password == null || name == null || email == null)
            throw new DaoException(Constants.ERROR_REG_NULL);
        login = login.trim();
        if (login.equals(Constants.VALUE_EMPTY))
            throw new DaoException(Constants.ERROR_REG_LOGIN);
        password = password.trim();
        if (password.equals(Constants.VALUE_EMPTY))
            throw new DaoException(Constants.ERROR_REG_PASS);
        name = name.trim();
        if (name.equals(Constants.VALUE_EMPTY))
            throw new DaoException(Constants.ERROR_REG_NAME);
        email = email.trim();
        if (email.equals(Constants.VALUE_EMPTY))
            throw new DaoException(Constants.ERROR_REG_EMAIL);
    }
}


