package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {
    private static PreparedStatement statement; // Statement reference

    //Create PreparedStatement
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        statement = conn.prepareStatement(sqlStatement);
    }

    //Return statement object
    public static PreparedStatement getPreparedStatement(){

        return statement;
    }
}
