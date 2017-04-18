package mybookslit;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Csongor on 3/26/2017.
 */
public class AccessMybooksList {

    public List getBooksList( int iduser){

        System.out.println( "iduser" + iduser);

        List listOfBooks = new ArrayList<BookListBean>();
        try {

            UserAccesList userAcces = new UserAccesList();

            Class.forName("org.postgresql.Driver");


            // 1. define connection parameters to db
            final String URL = "jdbc:postgresql://54.93.65.5:5432/5csongor";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            // 2. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 3. create the query statement
            String query = "SELECT * FROM booklist JOIN usersbooklist ON usersbooklist.user_id=booklist.fkuser WHERE user_id=?";

            // 4. execute the query
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, iduser);
            ResultSet rs = st.executeQuery();

            // 5. iterate the result set and print the values
            while ( rs.next()){
                int id = rs.getInt("book_id");
                String name = rs.getString("booktitle");

                BookListBean currentbook = new BookListBean(id, name);

                listOfBooks.add(currentbook);

            }

            // 6. close the objects
            rs.close();
            st.close();
            conn.close();
        }

        catch (Exception ex){
            ex.printStackTrace();
        }

        return listOfBooks;
    }

    public void insertBook (String titleOfBook, int iduser){

        System.out.println("here i am!!!");

        // 1. define connection parameters to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/5csongor";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 2. obtain a connection
        try {

            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            UserAccesList userAccess = new UserAccesList();

            // 3. create a query statement
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO booklist (booktitle, fkuser) VALUES (?, ?)");

            pSt.setString(1, titleOfBook);
            pSt.setInt(2, iduser);

            // 4. execute the query
            int rowsInserted = pSt.executeUpdate();

            // 5. close the object
            pSt.close();
            conn.close();
        }

        catch (SQLException e){
            e.printStackTrace();
        }

        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        System.out.println("got out of here!");

    }

}
