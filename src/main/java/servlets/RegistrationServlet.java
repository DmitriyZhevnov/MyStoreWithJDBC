package servlets;

import classes.Storage;
import handlers.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registrationPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Validator validator = new Validator();
        String resultEnteringLogin = validator.checkLoginForValid(req.getParameter("login"));
        String resultEnteringPassword = validator.checkPasswordForValid(req.getParameter("password"));
        String resultEnteringAge = validator.checkAgeForValid(req.getParameter("age"));
        if (resultEnteringLogin.equals("ok")) {
            if (resultEnteringPassword.equals("ok")) {
                if (resultEnteringAge.equals("ok")) {
                    Storage.addUser(req.getParameter("name"), Integer.parseInt(req.getParameter("age")), req.getParameter("login"), req.getParameter("password"));
                    getServletContext().getRequestDispatcher("/loginPage.jsp").forward(req, resp);
                } else {
                    req.getSession().setAttribute("messageError", resultEnteringAge);
                    getServletContext().getRequestDispatcher("/registrationPageWrongParameters.jsp").forward(req, resp);
                }
            } else {
                req.getSession().setAttribute("messageError", resultEnteringPassword);
                getServletContext().getRequestDispatcher("/registrationPageWrongParameters.jsp").forward(req, resp);
            }
        } else {
            req.getSession().setAttribute("messageError", resultEnteringLogin);
            getServletContext().getRequestDispatcher("/registrationPageWrongParameters.jsp").forward(req, resp);
        }
    }
}
