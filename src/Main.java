import helper.DBQuery;
import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginMenu.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws SQLException {

        Connection conn = JDBC.getConnection();

        DBQuery.setStatement(conn);//Create statement object
        Statement statement = DBQuery.getStatement();// Get Statement Reference

        /*Raw SQL INSERT Statement
        String insertStatement = "INSERT INTO countries (Country, Create_Date, Created_By, Last_Updated_By) VALUES('US', '2021-12-13 00:00:00', 'admin', 'admin')";
         */

        //Variable SQL INSERT Statement
        String countryName = "Canada";
        String createDate = "2021-12-13 00:00:00";
        String createdBy = "admin";
        String createUpdateBy = "admin";

        String insertStatement = "INSERT INTO countries (Country, Create_Date, Created_By, Last_Updated_By)" +
                "VALUES(" +
                "'" + countryName + "', '" + createDate + "', '" + createdBy + "', '" + createUpdateBy + "')";



        //Execute SQL Statement
        statement.execute(insertStatement);

        //Confrim rows affected
        if(statement.getUpdateCount() > 0){
            System.out.println(statement.getUpdateCount() + " row(s) affected!");
        }
        else{
            System.out.println("No Change!");
        }

        launch(args);
    }
}
