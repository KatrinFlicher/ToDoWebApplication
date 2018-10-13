package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.exceptions.DaoException;

import java.util.List;

public interface ITaskDAO {
    List<Task> getTasks(User user, String param);

    boolean deleteTask(String[] idTasks, User user, String editFunction);

    boolean editFileInTask(String idTask, String value);

    boolean editTask(String[] idTasks, String editFunction);

    boolean addTask(Task task, String section, String editFunction, User user) throws DaoException;
}
