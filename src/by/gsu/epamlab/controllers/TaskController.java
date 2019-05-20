package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.interfaces.BaseController;
import by.gsu.epamlab.interfaces.ITaskDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TaskController extends BaseController {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.KEY_USER);
        String section;
        String sectionFromForm = request.getParameter(Constants.KEY_SECTION);
        String sectionFromServ = (String) request.getAttribute(Constants.KEY_SECTION);
        try {
            if (user == null)
                throw new DaoException(Constants.ERROR_NULL);
            section = check(sectionFromForm, sectionFromServ);
            ITaskDAO taskDAO = TaskFactory.getTaskDAO();
            List<Task> tasks = taskDAO.getTasks(user, section);
            request.setAttribute(Constants.KEY_TASKS, tasks);
            session.setAttribute(Constants.KEY_SECTION, section);
            forward(Constants.MAIN_PAGE, request, response);
        } catch (DaoException e) {
            forwardError(Constants.INDEX_PAGE, e.getMessage(), request, response);
        }
    }

    private String check(String sectionFromForm, String sectionFromServ) {
        final String SECTION_TODAY = "TODAY";
        //final String SECTION_TODAY = TaskDBImpl.Sections.TODAY.toString().toUpperCase();
        if (sectionFromForm == null) {
            if (sectionFromServ == null)
                return SECTION_TODAY;
            else return sectionFromServ;

        } else return sectionFromForm;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
