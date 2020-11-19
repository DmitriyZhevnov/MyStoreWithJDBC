package servlets;

import classes.Person;
import classes.Storage;
import handlers.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myPage")
public class PersonalPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = (Person) req.getSession().getAttribute("currentUser");
        if (person != null) {
            if (person.getStatus().equals("user")) {
                getServletContext().getRequestDispatcher("/personalPage.jsp").forward(req, resp);
            } else if (person.getStatus().equals("admin")) {
                getServletContext().getRequestDispatcher("/personalPageForAdmin.jsp").forward(req, resp);
            }
        } else {
            getServletContext().getRequestDispatcher("/loginPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean correctValues = true;
        Validator validator = new Validator();
        Person person = (Person) req.getSession().getAttribute("currentUser");
        String resultEnteringLogin = validator.checkLoginForValid(req.getParameter("login"));
        String resultEnteringPassword = validator.checkPasswordForValid(req.getParameter("password"));
        try {
            Storage.findPersonInStorageByLogin(person.getLogin()).setAge(Integer.parseInt(req.getParameter("age")));
        } catch (NumberFormatException e) {
            correctValues = false;
            req.getSession().setAttribute("messageError", "Введите возраст цифрой.");
        }
        if (!req.getParameter("password").equals("")) {
            if (resultEnteringPassword.equals("ok")) {
                Storage.findPersonInStorageByLogin(person.getLogin()).setPassword(req.getParameter("password"));
            } else {
                correctValues = false;
                req.getSession().setAttribute("messageError", resultEnteringPassword);
            }
        }
        if (!person.getLogin().equals(req.getParameter("login"))) {
            if (resultEnteringLogin.equals("ok")) {
                Storage.findPersonInStorageByLogin(person.getLogin()).setLogin(req.getParameter("login"));
            } else {
                correctValues = false;
                req.getSession().setAttribute("messageError", resultEnteringLogin);
            }
        }
        Storage.findPersonInStorageByLogin(person.getLogin()).setName(req.getParameter("name"));
        if (correctValues) {
            doGet(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/personalPageForChangeParametersWrongParameters.jsp").forward(req, resp);
        }
    }
}
