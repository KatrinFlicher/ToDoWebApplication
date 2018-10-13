package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.exceptions.DaoException;

public interface IUserDAO {
    User getUser(String login, String password) throws DaoException;

    boolean isFoundLogin(User user);

    boolean addUser(User user, String password);
}
