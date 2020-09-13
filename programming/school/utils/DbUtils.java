package programming.school.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/programming_school?serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "coderslab2020";

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
        return con;

    }
}
