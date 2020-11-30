package database;

import classes.Person;
import classes.Product;
import classes.StorageOfProducts;
import classes.StorageOfProducts2;
import com.mysql.cj.result.SqlDateValueFactory;
import org.apache.log4j.Logger;

import java.sql.*;

public class DataBase {

    final static Logger logger = Logger.getLogger(DataBase.class);


    public static void addProductInStorage(Product product){
            try{
                String sqlQuery = "Select * from product where serial_number = ?";
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sqlQuery);
                preparedStatement.setInt(1, product.getSerialNumber());
                if(preparedStatement.execute()){
                    String sqlQueryAdd = "update product set count = count + ? where serial_number = ?";
                    PreparedStatement preparedStatement2 = DatabaseConnection.getConnection().prepareStatement(sqlQueryAdd);
                    preparedStatement2.setInt(1, product.getCount());
                    preparedStatement2.setInt(2, product.getSerialNumber());
                    preparedStatement2.execute();
                } else {

                }
            }catch (SQLException e){

            }
    }

    public static StorageOfProducts2 getProductStorage() {
        StorageOfProducts2 storageOfProducts = new StorageOfProducts2();
        try {
            Statement statement = DatabaseConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from product;");
            while (resultSet.next()) {
                storageOfProducts.addProduct(new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4)), resultSet.getInt(5));
            }
        } catch (SQLException e) {
            //обработка
        } finally {
            return storageOfProducts;
        }
    }

    public static boolean checkLoginInDB(String login) {
        boolean exist = false;
        try {
            String sqlQuery = "Select * from person where login = ?";
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setString(1, login);
            exist = preparedStatement.execute();
        } catch (SQLException e) {
            //обработка
        } finally {
            return exist;
        }
    }

    public static void registration(String name, int age, String login, String password) {
        String sqlQuery = "INSERT into person ( name, age, login, password, status) " +
                "VALUES (?,?,?,?, 'user')";
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //обработка ошибки
        }
    }

    public static Person login(String login, String password) {
        Person currentPerson = null;
        try {
            Statement statement = DatabaseConnection.getConnection().createStatement();
            ResultSet resultSet = null;
            resultSet = statement.executeQuery("SELECT * FROM person");
            while (resultSet.next()) {
                if (resultSet.getString("login").equals(login) && resultSet.getString("password").equals(password)) {
                    currentPerson = new Person(resultSet.getString("name"), resultSet.getInt("age"),
                            resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("status"));
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (currentPerson == null) {
                return null;
            } else {
                return currentPerson;
            }
        }
    }
}
