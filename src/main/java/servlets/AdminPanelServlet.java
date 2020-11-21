package servlets;

import classes.Person;
import classes.Product;
import classes.StorageOfProducts;
import classes.StorageOfUsers;

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
            Person person = StorageOfUsers.findPersonInStorageByLogin(req.getParameter("login"));
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
            Person person = StorageOfUsers.findPersonInStorageByLogin(req.getParameter("login"));
            if (person == null) {
                getServletContext().getRequestDispatcher("/adminPanelDeleteUserWrongLogin.jsp").forward(req, resp);
            } else {
                StorageOfUsers.getUserStorage().remove(StorageOfUsers.findPersonInStorageByLogin(req.getParameter("login")));
                doGet(req, resp);
            }
        } else if ((req.getParameter("operation")).equals("modifyProduct")) {
            req.getSession().setAttribute("productToModify", StorageOfProducts.getProductInStorageById(Integer.parseInt(req.getParameter("idProductToModify"))));
            getServletContext().getRequestDispatcher("/adminEditStorageEditProduct.jsp").forward(req, resp);
        } else if ((req.getParameter("operation")).equals("modifyProductWithValues")) {
            Product productToModify = StorageOfProducts.getProductInStorage((Product) req.getSession().getAttribute("productToModify"));
            if (productToModify.getId() != Integer.parseInt(req.getParameter("id")) &&
                    StorageOfProducts.returnStorage().stream().anyMatch(s -> s.getId() == Integer.parseInt(req.getParameter("id")))) {
                req.getSession().setAttribute("message", "Такой ID товара уже есть. Все изменения не вступили в силу.");
                getServletContext().getRequestDispatcher("/adminEditStorageEditProductWithMessage.jsp").forward(req, resp);
            } else {
                productToModify.setId(Integer.parseInt(req.getParameter("id")));
                productToModify.setName(req.getParameter("name"));
                productToModify.setDescription(req.getParameter("description"));
                productToModify.setPrice(Double.parseDouble(req.getParameter("price")));
                productToModify.setCount(Integer.parseInt(req.getParameter("count")));
                getServletContext().getRequestDispatcher("/adminEditStorageOfProducts.jsp").forward(req, resp);
            }
        }
    }
}
