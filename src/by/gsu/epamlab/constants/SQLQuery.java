package by.gsu.epamlab.constants;

public class SQLQuery {
    public static final String SELECT_FOUND_USER_BY_LOGIN = "SELECT * from users WHERE login=?";
    public static final String BY_PASSWORD = " AND password=?";
    public static final String INSERT_USER = "INSERT INTO users(login,password,fname,email,role) VALUES(?,?,?,?,?)";
    public static final String SELECT_USER = "SELECT * from users WHERE  login=?";

    public static final String SELECT_TASKS = "SELECT * from tasks WHERE " +
                                                "idUser = (select id from users where login=?) and ";
    public static final String SELECT_TASKS_BY_ID = "SELECT * from tasks";

    public static final String WHERE_TODAY_LIST_TASK = "data <= curdate() and flagFix=0 and flagRecycle=0";
    public static final String WHERE_TOMORROW_LIST_TASK = "data = DATE_ADD(curdate(), INTERVAL 1 DAY) " +
            "and flagFix=0 and flagRecycle=0";
    public static final String WHERE_SOMEDAY_LIST_TASK = "data > DATE_ADD(curdate(), INTERVAL 1 DAY)" +
            " and flagFix=0 and flagRecycle=0";
    public static final String WHERE_FIXED_LIST_TASK = "flagFix=1 and flagRecycle=0";
    public static final String WHERE_RECYCLE_LIST_TASK = "flagRecycle=1";

    public static final String UPDATE_TASK = "UPDATE tasks ";
    public static final String INSERT_TASK = "INSERT INTO tasks(content,data,idUser,flagFix,flagRecycle) " +
                                              "VALUES(?,?,(select id from users where login=?),?,?)";
    public static final String WITH_ID = " WHERE id=?";
    public static final String BY_FIX = "SET flagFix=1";
    public static final String BY_RECYCLE = "SET flagRecycle=1";
    public static final String BY_RESTORE = "SET flagRecycle=0";
    public static final String BY_FILE = "SET fileName=?";
    public static final String BY_EDIT = "SET content=?,data=?";

    public static final String REMOVE_TASK = "DELETE FROM tasks";



    public static final String ID_TASK = "id";
    public static final String CONTENT_TASK = "content";
    public static final String DATA_TASK= "data";
    public static final String FILE_NAME_TASK= "fileName";

    public static final int CONTENT_INDEX = 1;
    public static final int DATA_INDEX = 2;
    public static final int ID_USER_INDEX = 3;
    public static final int ID_INDEX = 1;

    public static final int FLAG_RECYCLE_INDEX = 4;
    public static final int FLAG_FIX_INDEX = 5;
    public static final int FLAG_DEFAULT_VALUE = 0;

    public static final int LOGIN_INDEX = 1;
    public static final int PASSWORD_INDEX = 2;
    public static final int NAME_INDEX = 3;
    public static final int EMAIL_INDEX = 4;
    public static final int ROLE_INDEX = 5;
}
