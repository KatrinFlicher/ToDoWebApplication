package by.gsu.epamlab.factories;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.impl.UserDBImpl;
import by.gsu.epamlab.interfaces.IUserDAO;

public class UserFactory {
    private static IUserDAO userDAO;

    public static void setGlobals(String strDAO){
        userDAO = Constants.FORMAT_SQL.equals(strDAO)? new UserDBImpl():null;
    }

    public static IUserDAO getUserDAO(){
        return userDAO;
    }
}
