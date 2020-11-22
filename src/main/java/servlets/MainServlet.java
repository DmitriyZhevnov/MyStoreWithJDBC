package servlets;

import classes.StorageOfProducts;
import classes.StorageOfUsers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class MainServlet extends HttpServlet {
    private StorageOfUsers storageOfUsers;
    private StorageOfProducts storageOfProducts;

    @Override
    public void init() throws ServletException {
        super.init();
        storageOfUsers = new StorageOfUsers();
        storageOfProducts = new StorageOfProducts();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/loginPage.jsp").forward(req, resp);
    }
}