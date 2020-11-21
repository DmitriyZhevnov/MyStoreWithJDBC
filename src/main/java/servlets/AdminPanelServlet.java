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
        } else if (req.getParameter("operation").equals("modifyProduct")) {
            req.getSession().setAttribute("productToModify", StorageOfProducts.getProductInStorageById(Integer.parseInt(req.getParameter("idProductToModify"))));
            getServletContext().getRequestDispatcher("/adminEditStorageEditProduct.jsp").forward(req, resp);
        } else if (req.getParameter("operation").equals("modifyProductWithValues")) {
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
        } else if (req.getParameter("operation").equals("deleteProduct")) {
            Product productToDelete = StorageOfProducts.getProductInStorageById(Integer.parseInt(req.getParameter("idProductForDelete")));
            StorageOfProducts.returnStorage().remove(productToDelete);
            req.getSession().setAttribute("message", productToDelete.getName() + " был удален из хранилища");
            getServletContext().getRequestDispatcher("/adminEditStorageOfProductsWithMessage.jsp").forward(req, resp);
        } else if (req.getParameter("operation").equals("addProduct")) {
            System.out.println("зашел");
            System.out.println(req.getParameter("idProductToAdd"));
            Product productForAdd = StorageOfProducts.getProductInStorageById(Integer.parseInt(req.getParameter("idProductToAdd")));
            System.out.println(productForAdd);
            try {
                if (Integer.parseInt(req.getParameter("countOfProductToAdd")) > 0) {
                    StorageOfProducts.addProduct(productForAdd, Integer.parseInt(req.getParameter("countOfProductToAdd")));
                    req.getSession().setAttribute("message", req.getParameter("countOfProductToAdd") + " единиц(а) " + productForAdd.getName() + " добавлено на склад");
                    getServletContext().getRequestDispatcher("/adminEditStorageOfProductsWithMessage.jsp").forward(req, resp);
                } else {
                    req.getSession().setAttribute("message", " Введите количество корректно.");
                    getServletContext().getRequestDispatcher("/adminEditStorageOfProductsWithMessage.jsp").forward(req, resp);
                }
            } catch (NumberFormatException e) {
                req.getSession().setAttribute("message", " Введите количество корректно.");
                getServletContext().getRequestDispatcher("/adminEditStorageOfProductsWithMessage.jsp").forward(req, resp);
            }
        }
    }
}
