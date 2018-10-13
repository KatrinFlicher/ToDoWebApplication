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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddTaskController extends BaseController {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.KEY_USER);
        String section = (String) session.getAttribute(Constants.KEY_SECTION);
        String editFunction = request.getParameter(Constants.KEY_EDIT_FUNCTION);
        try {
            ITaskDAO iTaskDAO = TaskFactory.getTaskDAO();
            String content = request.getParameter(Constants.KEY_CONTENT_TASK);
            String data = request.getParameter(Constants.KEY_DATE_TASK);
            Task task = check(content, data, section);
            if (iTaskDAO.addTask(task, section, editFunction, user)){
                    response.sendRedirect(Constants.TASK_CONTR);
            }
            else throw new DaoException(Constants.ERROR_EDIT_TASK);

        } catch (DaoException e) {
            forwardError(Constants.ADD_FORM, e.getMessage(),request,response);
        }

    }

    private Task check(String content, String data, String section) throws DaoException {
        if(section.equals("SOMEDAY")){
            if(data == null)
                throw new DaoException(Constants.ERROR_EDIT_TASK_FORM);
            else if (data.equals(Constants.VALUE_EMPTY))
                throw new DaoException(Constants.ERROR_EDIT_TASK_FORM);
        }

        if (content == null || content.equals(Constants.VALUE_EMPTY))
            throw new DaoException(Constants.ERROR_EDIT_TASK_FORM);
        Date dateTask = null;
        final SimpleDateFormat GET_DATE_FORMAT = new SimpleDateFormat("yyyy:MM:dd");
        try {if (data!=null){

            String tempDate = data.replace(".", ":").replaceAll("-", ":");
            dateTask =  new Date((GET_DATE_FORMAT.parse(tempDate)).getTime());
        }
        else {
            if (section.equals("TODAY")){
                dateTask = new Date(new java.util.Date().getTime());
            }
            if (section.equals("TOMORROW")){
                java.util.Date dt = new java.util.Date();
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                c.add(Calendar.DATE, 1);
                dt = c.getTime();
                dateTask = new Date(dt.getTime());
            }
        }
            return new Task(content,dateTask);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
