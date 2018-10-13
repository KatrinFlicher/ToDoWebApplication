package by.gsu.epamlab.impl;

import by.gsu.epamlab.beans.Role;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.constants.ConnectionManager;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.SQLQuery;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.IUserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDBImpl implements IUserDAO{
    @Override
    public User getUser(String login, String password) throws DaoException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectionManager.createConnection();
            String sqlQuery = SQLQuery.SELECT_FOUND_USER_BY_LOGIN+SQLQuery.BY_PASSWORD;
            pst = cn.prepareStatement(sqlQuery);
            pst.setString(SQLQuery.LOGIN_INDEX, login);
            pst.setString(SQLQuery.PASSWORD_INDEX, password);
            rs = pst.executeQuery();
            if (rs.next()){
                User user = new User(login, Role.valueOf(rs.getString(SQLQuery.ROLE_INDEX)),
                        rs.getString(SQLQuery.NAME_INDEX), rs.getString(SQLQuery.EMAIL_INDEX));
                System.out.println(user);
                return user;
            }
            else throw new DaoException(Constants.ERROR_LOGIN);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            ConnectionManager.closeConnection(cn);
            ConnectionManager.closeStatement(pst);
            ConnectionManager.closeResultSet(rs);
        }
    }



    @Override
    public boolean addUser(User user, String password) {
        synchronized (UserDBImpl.class){
            if (!isFoundLogin(user)){
                Connection cn = null;
                PreparedStatement pst = null;
                try {
                    cn = ConnectionManager.createConnection();
                    pst = cn.prepareStatement(SQLQuery.INSERT_USER);
                    pst.setString(SQLQuery.LOGIN_INDEX, user.getLogin());
                    pst.setString(SQLQuery.PASSWORD_INDEX, password);
                    pst.setString(SQLQuery.NAME_INDEX, user.getName());
                    pst.setString(SQLQuery.EMAIL_INDEX, user.getEmail());
                    pst.setString(SQLQuery.ROLE_INDEX, user.getRole().toString());
                    pst.executeUpdate();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                finally {
                    ConnectionManager.closeConnection(cn);
                    ConnectionManager.closeStatement(pst);
                }
            }
            return false;
        }
    }

    @Override
    public boolean isFoundLogin(User user) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean res = false;
        try {
            cn = ConnectionManager.createConnection();
            pst = cn.prepareStatement(SQLQuery.SELECT_FOUND_USER_BY_LOGIN);
            pst.setString(SQLQuery.LOGIN_INDEX, user.getLogin());
            rs = pst.executeQuery();
            if (rs.next()){
                res = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionManager.closeConnection(cn);
            ConnectionManager.closeStatement(pst);
            ConnectionManager.closeResultSet(rs);
        }
        return res;
    }


}
