package handlers;

import classes.StorageOfUsers;
import java.util.regex.Pattern;

public class Validator {
    private final int minLengthLogin = 4;
    private final int minLengthPassword = 5;

    public String checkLoginForValid(String login) {
        if (login.isEmpty()) {
            return "Логин не может быть пустым";
        } else {
            if (login.length() < minLengthLogin) {
                return ("Длинна логина должна быть не менее " + minLengthLogin + " символов.");
            } else {
                String punktMarks = "[\\da-zA-Zа-яёА-ЯЁ_]+";
                if (Pattern.matches(punktMarks, login) == false) {
                    return "Логин должен содержать только: буквы, цифры и нижнее подчёркивание";
                } else if (StorageOfUsers.getUserStorage().stream().anyMatch(s -> s.getLogin().equals(login))) {
                    return "Такой логин уже существует";
                } else {
                    return "ok";
                }
            }
        }
    }

    public String checkPasswordForValid(String pas) {
        if (pas.isEmpty()) {
            return "Пароль не может быть пустым";
        } else {
            if (pas.length() < minLengthPassword) {
                return ("Длинна пароля должна быть не менее " + minLengthPassword + " символов.");
            } else return "ok";
        }
    }

    public String checkAgeForValid(String pas) {
        try {
            Integer.parseInt(pas);
            return "ok";
        } catch (NumberFormatException e) {
            return "Введите возраст цифрой.";
        }
    }
}
