package servlets;

import classes.Person;
import classes.Storage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminPanelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = (Person) req.getSession().getAttribute("currentUser");
        if (person != null && person.getStatus().equals("admin")) {
            getServletContext().getRequestDispatcher("/adminPanel.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/loginPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ((req.getParameter("operation")).equals("changeStatus")) {
            Person person = Storage.findPersonInStorageByLogin(req.getParameter("login"));
            if (person == null) {
                getServletContext().getRequestDispatcher("/adminPanelChangeStatusWrongLogin.jsp").forward(req, resp);
            } else {
                if (req.getParameter("option").equals("makeAdmin")) {
                    person.setStatus("admin");
                } else if (req.getParameter("option").equals("deleteAdmin")) {
                    person.setStatus("user");
                }
                doGet(req, resp);
            }
        } else if ((req.getParameter("operation")).equals("deleteUser")) {
            req.getParameter("login");
            Person person = Storage.findPersonInStorageByLogin(req.getParameter("login"));
            if (person == null) {
                getServletContext().getRequestDispatcher("/adminPanelDeleteUserWrongLogin.jsp").forward(req, resp);
            } else {
                Storage.getUserStorage().remove(Storage.findPersonInStorageByLogin(req.getParameter("login")));
                doGet(req, resp);
            }
        }
    }
}
