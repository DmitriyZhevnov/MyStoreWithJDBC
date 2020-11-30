package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
    final static String url = "jdbc:mysql://localhost/myStore?serverTimezone=UTC";
    final static String userName = "root";
    final static String userPassword = "950621";

    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, userName, userPassword);
            System.out.println("connected");
        }catch(SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}
