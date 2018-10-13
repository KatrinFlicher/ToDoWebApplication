package by.gsu.epamlab.impl;

import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.constants.ConnectionManager;
import by.gsu.epamlab.constants.SQLQuery;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.FileFactory;
import by.gsu.epamlab.interfaces.ITaskDAO;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskDBImpl implements ITaskDAO {
    @Override
    public List<Task> getTasks(User user, String param) {
        List<Task> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectionManager.createConnection();
            Sections section = Sections.valueOf(param.toUpperCase());
            String sqlQuery = section.getSqlQuery();
            pst = cn.prepareStatement(sqlQuery);
            pst.setString(SQLQuery.LOGIN_INDEX, user.getLogin());
            rs = pst.executeQuery();
            while (rs.next()){
                int id = rs.getInt(SQLQuery.ID_TASK);
                String content  = rs.getString(SQLQuery.CONTENT_TASK);
                Date date = rs.getDate(SQLQuery.DATA_TASK);
                String fileName = rs.getString(SQLQuery.FILE_NAME_TASK);
                list.add(new Task(id, content, date, fileName));
            }
            System.out.println(list);
            return list;
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


    public boolean editFileInTask(String idTask, String fileName){
        Function function = Function.FILE;
        String sqlQuery = function.getSqlQuery();
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectionManager.createConnection();
            pst = cn.prepareStatement(sqlQuery);
            int id = Integer.parseInt(idTask);
            pst.setString(1, fileName);
            pst.setInt(2, id);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public boolean editTask(String[] idTasks, String editFunction){
        Function function = Function.valueOf(editFunction);
        String sqlQuery = function.getSqlQuery();
        System.out.println(sqlQuery);
        System.out.println("idTasks: " + Arrays.toString(idTasks));
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectionManager.createConnection();
            pst = cn.prepareStatement(sqlQuery);
            for (String listId: idTasks){
                int id = Integer.parseInt(listId);
                pst.setInt(SQLQuery.ID_INDEX, id);
                pst.executeUpdate();
                System.out.println("1 - " + listId);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTask(String[] idTasks, User user, String editFunction){
        Function function = Function.REMOVE;
        String sqlQuery = function.getSqlQuery();
        FileManager fileManager = FileFactory.getFileManager();
        String directory = fileManager.getPath() + File.separator + user.getLogin();
        Connection cn = null;
        PreparedStatement pst = null;
        if (editFunction.equals("CLEAN")){
            List<Task> tasks = getTasks(user, "RECYCLE_BIN");
            List<String> listIdTasks = new ArrayList<>();
            for (Task task: tasks)
                listIdTasks.add(String.valueOf(task.getId()));
            idTasks = listIdTasks.toArray(new String[listIdTasks.size()]);
        }
        try {
            cn = ConnectionManager.createConnection();
            pst = cn.prepareStatement(sqlQuery);
            for (String listId: idTasks){
                int id = Integer.parseInt(listId);
                pst.setInt(SQLQuery.ID_INDEX, id);
                pst.executeUpdate();
                fileManager.DeleteFile("TASKCONTR", directory + File.separator + listId, listId);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean addTask(Task task, String section, String editFunction, User user) throws DaoException {
        Function function = Function.valueOf(editFunction);
        String sqlQuery = function.getSqlQuery();
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectionManager.createConnection();
            pst = cn.prepareStatement(sqlQuery);
            pst.setString(SQLQuery.CONTENT_INDEX, task.getContentTask());
            pst.setDate(SQLQuery.DATA_INDEX, task.getDate());
            if ( function == Function.ADD){
                pst.setString(SQLQuery.ID_USER_INDEX, user.getLogin());
                pst.setInt(SQLQuery.FLAG_FIX_INDEX,SQLQuery.FLAG_DEFAULT_VALUE);
                pst.setInt(SQLQuery.FLAG_RECYCLE_INDEX,SQLQuery.FLAG_DEFAULT_VALUE);
                }
                else throw new DaoException("");

            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    enum Function{
        ADD(SQLQuery.INSERT_TASK),
        EDIT(SQLQuery.UPDATE_TASK+SQLQuery.BY_EDIT+SQLQuery.WITH_ID),
        FIX(SQLQuery.UPDATE_TASK+SQLQuery.BY_FIX+SQLQuery.WITH_ID),
        RECYCLE(SQLQuery.UPDATE_TASK+SQLQuery.BY_RECYCLE+SQLQuery.WITH_ID),
        REMOVE(SQLQuery.REMOVE_TASK+SQLQuery.WITH_ID),
        RESTORE(SQLQuery.UPDATE_TASK+SQLQuery.BY_RESTORE+SQLQuery.WITH_ID),
        FILE(SQLQuery.UPDATE_TASK+SQLQuery.BY_FILE+SQLQuery.WITH_ID);

        private String sqlQuery;

        Function(String sqlQuery){
            this.sqlQuery = sqlQuery;
        }

        public String getSqlQuery() {
            return this.sqlQuery;
        }
    }



    public enum Sections{
        TODAY(SQLQuery.WHERE_TODAY_LIST_TASK),
        TOMORROW(SQLQuery.WHERE_TOMORROW_LIST_TASK),
        SOMEDAY(SQLQuery.WHERE_SOMEDAY_LIST_TASK),
        FIXED(SQLQuery.WHERE_FIXED_LIST_TASK),
        RECYCLE_BIN(SQLQuery.WHERE_RECYCLE_LIST_TASK);

        private String sqlQuery;

        Sections(String sqlQuery) {
            this.sqlQuery = SQLQuery.SELECT_TASKS + sqlQuery;
        }

        public String getSqlQuery(){
            return sqlQuery;
        }

    }


}
