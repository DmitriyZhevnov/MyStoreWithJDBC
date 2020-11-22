package servlets;

import classes.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

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
            Product productForAdd = StorageOfProducts.getProductInStorageById(Integer.parseInt(req.getParameter("idProductToAdd")));
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
        } else if (req.getParameter("operation").equals("sortStorage")) {
            StorageOfProducts.returnStorage().sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
            req.getSession().setAttribute("message", " Хранилище отсортировано");
            getServletContext().getRequestDispatcher("/adminEditStorageOfProductsWithMessage.jsp").forward(req, resp);
        } else if (req.getParameter("operation").equals("addNewProduct")) {
            if (req.getParameter("id").isEmpty() || req.getParameter("name").isEmpty() || req.getParameter("description").isEmpty() ||
                    req.getParameter("price").isEmpty()) {
                req.getSession().setAttribute("message", "Введите данные корректно.");
                getServletContext().getRequestDispatcher("/adminAddProductWithMessage.jsp").forward(req, resp);
            } else {
                if (StorageOfProducts.returnStorage().stream().anyMatch(s -> s.getId() == Integer.parseInt(req.getParameter("id")))) {
                    req.getSession().setAttribute("message", "Товар с этим ID уже есть. Новый товар не был добавлен.");
                    getServletContext().getRequestDispatcher("/adminAddProductWithMessage.jsp").forward(req, resp);
                } else try {
                    StorageOfProducts.addProduct(new Product(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), req.getParameter("description"), Double.parseDouble(req.getParameter("price"))), 0);
                    req.getSession().setAttribute("message", "Товар " + req.getParameter("name") + " добавлен");
                    getServletContext().getRequestDispatcher("/adminEditStorageOfProductsWithMessage.jsp").forward(req, resp);
                } catch (NumberFormatException e) {
                    req.getSession().setAttribute("message", "Введите данные корректно.");
                    getServletContext().getRequestDispatcher("/adminAddProductWithMessage.jsp").forward(req, resp);
                }
            }
        } else if (req.getParameter("operation").equals("findOrderHistoryByLogin")) {
            Person person = StorageOfUsers.findPersonInStorageByLogin(req.getParameter("login"));
            if (person != null) {
                req.getSession().setAttribute("personForFindOrderHistory", person);
                getServletContext().getRequestDispatcher("/adminOrderHistoryByLogin.jsp").forward(req, resp);
            } else {
                req.getSession().setAttribute("message", "Пользователя с таким логином нет.");
                getServletContext().getRequestDispatcher("/adminOrderHistoryPanelWithMessage.jsp").forward(req, resp);
            }
        } else if (req.getParameter("operation").equals("findOrderByNumber")) {
            try {
                System.out.println((req.getParameter("orderNumber")));
                int numberToFind = Integer.parseInt(req.getParameter("orderNumber"));
                if (StorageOfOrders.getOrderStorage().stream().anyMatch(s -> s.getNumber() == numberToFind)) {
                    req.getSession().setAttribute("orderToShow", StorageOfOrders.getOrderStorage().stream().filter(s -> s.getNumber() == numberToFind).collect(Collectors.toList()).get(0));
                    getServletContext().getRequestDispatcher("/adminOrderHistoryByNumberOfOrder.jsp").forward(req, resp);
                } else {
                    req.getSession().setAttribute("message", "Заказа с таким номером нет.");
                    getServletContext().getRequestDispatcher("/adminOrderHistoryPanelWithMessage.jsp").forward(req, resp);
                }
            } catch (NumberFormatException e) {
                req.getSession().setAttribute("message", "Введите номер заказа корректно");
                getServletContext().getRequestDispatcher("/adminOrderHistoryPanelWithMessage.jsp").forward(req, resp);

            }

        }
    }
}
