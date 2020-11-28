package database;

import classes.Person;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.concurrent.ExecutionException;

public class DataBase {
    final static String url = "jdbc:mysql://localhost/mystore?serverTimezone=UTC";
    final static String userName = "root";
    final static String userPassword = "950621";
    final static Logger logger = Logger.getLogger(DataBase.class);

    public static void registration(String name, int age, String login, String password){
        String sqlQuery = "INSERT into person ( name, age, login, password, status) " +
                "VALUES (?,?,?,?, 'user')";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, userName, userPassword)){
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setString(3, login);
                preparedStatement.setString(4, password);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e){
            //обработка ошибки
        }
    }

    public static Person login(String login, String password) {
        Person currentPerson = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, userName, userPassword)) {
                logger.info("Connection to DB successful!");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM person");
                while (resultSet.next()) {
                    if (resultSet.getString("login").equals(login) && resultSet.getString("password").equals(password)) {
                        currentPerson = new Person(resultSet.getString("name"), resultSet.getInt("age"),
                                resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("status"));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.info("Connection failed.");
        } finally {
            if (currentPerson == null) {
                return null;
            } else {
                return currentPerson;
            }
        }
    }
}
