package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageOfUsers {
    private static List<Person> userStorage;

    public StorageOfUsers() {
        userStorage = new ArrayList<>();
        //userStorage.add(new Person("Dmitriy", 25, "Tim04", "123456", "admin"));
        userStorage.add(new Person("Dmitriy", 25, "1", "1", "admin"));
    }

    public static void addUser(String name, int age, String login, String password) {
        userStorage.add(new Person(name, age, login, password, "user"));
    }

    public static boolean checkLoginAndPassword(String login, String password) {
        return userStorage.stream().anyMatch(s -> s.getLogin().equals(login) && s.getPassword().equals(password));
    }

    public static Person findPersonInStorageByLogin(String login) {
        if (userStorage.stream().anyMatch(s -> s.getLogin().equals(login))) {
            return userStorage.stream().filter(s -> s.getLogin().equals(login)).collect(Collectors.toList()).get(0);
        } else {
            return null;
        }
    }

    public static List<Person> getUserStorage() {
        return userStorage;
    }
}
