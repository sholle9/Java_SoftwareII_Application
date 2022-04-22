package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
/**This class holds the methods for the database preparedStatements*/
public class DBQuery {
    private static PreparedStatement statement; // Statement reference

    /**This is the setPreparedStatement method.
     This method will set the prepared statement for the database.
     @param conn this is the connection
     @param sqlStatement this is the sql statement to be sent to the database
     */
    //Create PreparedStatement
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        statement = conn.prepareStatement(sqlStatement);
    }

    /**This is the getPreparedStatement method.
     This method will get the prepared statement for the database.
     @return the statement created in the setPreparedStatement.
     */
    //Return statement object
    public static PreparedStatement getPreparedStatement(){

        return statement;
    }
}
