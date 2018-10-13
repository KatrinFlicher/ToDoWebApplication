package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.impl.FileManager;
import by.gsu.epamlab.interfaces.BaseController;
import by.gsu.epamlab.interfaces.ITaskDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class EditTaskController extends BaseController {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.KEY_USER);
        String section = (String) session.getAttribute(Constants.KEY_SECTION);
        String editFunction = request.getParameter(Constants.KEY_EDIT_FUNCTION);
        String[] listId = request.getParameter("markedTask").trim().split(" ");
            try {
                if (listId == null && !editFunction.equals("CLEAN")){
                    throw new DaoException(Constants.ERROR_CHECKBOX_EMPTY);
                }
                ITaskDAO iTaskDAO = TaskFactory.getTaskDAO();
                if (editFunction.equals("CLEAN") || editFunction.equals("REMOVE")){
                    if(iTaskDAO.deleteTask(listId, user, editFunction)){
                        request.setAttribute(Constants.KEY_SECTION, section);
                        forward(Constants.TASK_CONTR,request,response);
                    }
                    else throw new DaoException(Constants.ERROR_EDIT_TASK);
                }
                else if (iTaskDAO.editTask(listId,editFunction)){
                    request.setAttribute(Constants.KEY_SECTION, section);
                    forward(Constants.TASK_CONTR,request,response);
                }
                else throw new DaoException(Constants.ERROR_EDIT_TASK);
            } catch (DaoException e) {
               forwardError(Constants.MAIN_PAGE, e.getMessage(),request,response);
            }
    }
}
