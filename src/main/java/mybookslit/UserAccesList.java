package mybookslit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Csongor on 3/26/2017.
 */
public class UserAccesList {

    public UserAccesList(){}

    public int checkUserCredentials(String username, String password){
        int iduser = -1;

        try {

            Class.forName("org.postgresql.Driver");


            // 1. define connection parameters to db
            final String URL = "jdbc:postgresql://54.93.65.5:5432/5csongor";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            // 2. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 3. create a query statement
            String query = "SELECT user_id FROM userbooklist where username = ? and password = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            // 4. execute a query
            ResultSet rs = statement.executeQuery();

            // 5. iterate the result set and print the value
            while (rs.next()){
                iduser = rs.getInt("user_id");
            }

            // 6. close the objects
            rs.close();
            statement.close();
            conn.close();

        }

        catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println("comes out with id:" + iduser);
        return iduser;
    }

    public void insertUser(String username, String password){

        try {

            Class.forName("org.postgresql.Driver");

            // 1. define connection paramenters for db
            final String URL = "jdbc:postgresql://54.93.65.5:5432/5csongor";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            // 2. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 3. create a query statement
            String query = "INSERT INTO userbooklist (username, password) values (?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            // 4. execute rhe query
            statement.executeUpdate();

            // 6. close connections
            statement.close();
            conn.close();
        }

        catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println("Comes out from Register");
    }

    public  void passwordChange(String username, String password){

        try {


            Class.forName("org.postgresql.Driver");


            // 1. define connection parameters to db
            final String URL = "jdbc:postgresql://54.93.65.5:5432/5csongor";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            // 2. obtain connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 3. create a query statement
            String query = "UPDATE userbooklist SET password=? WHERE username=?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, password);
            statement.setString(2, username);

            // 4. execute the query
            statement.executeUpdate();

            // 5. close connections:
            statement.close();
            conn.close();
        }

        catch (Exception ex){
            ex.printStackTrace();
        }
    }

 /*   public void checkDuplicateBook(String username, String password){

 try {


            Class.forName("org.postgresql.Driver");


            // 1. define connection parameters to db
            final String URL = "jdbc:postgresql://54.93.65.5:5432/5csongor";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            // 2. obtain connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 3. create a query statement
            String query = "SELECT * FROM userbooklist WHERE booktitle=?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, password);
            statement.setString(2, username);

            // 4. execute the query
            statement.executeUpdate();

            // 5. close connections:
            statement.close();
            conn.close();
        }

        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
  */
}
