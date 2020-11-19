package servlets;

import classes.Person;
import classes.Storage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/homePage")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Person person;
        if (Storage.checkLoginAndPassword(req.getParameter("login"), req.getParameter("password"))) {
            person = Storage.findPersonInStorageByLogin((req.getParameter("login")));
            session.setAttribute("currentUser", person);
            getServletContext().getRequestDispatcher("/mainPage.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/loginPageAfterWrongParametrs.jsp").forward(req, resp);
        }
    }
}
