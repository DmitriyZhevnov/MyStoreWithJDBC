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
        String resultEnteringAge = validator.checkAgeForValid(req.getParameter("age"));
        String resultEnteringLogin = validator.checkLoginForValid(req.getParameter("login"));
        String resultEnteringPassword = validator.checkPasswordForValid(req.getParameter("password"));

        if (resultEnteringAge.equals("ok")) {
            Storage.findPersonInStorageByLogin(person.getLogin()).setAge(Integer.parseInt(req.getParameter("age")));
        } else {
            correctValues = false;
            req.getSession().setAttribute("messageError", resultEnteringAge);
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

//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Person person =(Person) req.getSession().getAttribute("currentUser");
//        Storage.findPersonInStorageByLogin(person.getLogin()).setAge(Integer.parseInt(req.getParameter("age")));
//        Storage.findPersonInStorageByLogin(person.getLogin()).setName(req.getParameter("name"));
//        Storage.findPersonInStorageByLogin(person.getLogin()).setLogin(req.getParameter("login"));
//        System.out.println(Storage.findPersonInStorageByLogin(req.getParameter("login")));
//        if (req.getParameter("password") != ""){
//            Storage.findPersonInStorageByLogin(person.getLogin()).setPassword(req.getParameter("password"));
//        }
//        req.getSession().setAttribute("currentUser", Storage.findPersonInStorageByLogin(req.getParameter("login")));
//        doGet(req, resp);
//    }
