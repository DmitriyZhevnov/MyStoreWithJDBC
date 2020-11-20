package servlets;

import classes.Person;
import classes.Product;
import classes.StorageOfProducts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/shop.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = (Person) req.getSession().getAttribute("currentUser");
        try {
            Product thisProductInStorage = StorageOfProducts.getProductInStorageById(Integer
                    .parseInt(req.getParameter("idProduct")));
            int countOfThisProductInStorage = thisProductInStorage.getCount();
            int countInRequest = Integer.parseInt(req.getParameter("count"));
            if (countOfThisProductInStorage < countInRequest) {
                req.getSession().setAttribute("shopMessage", "Приносим свои извинения. На складе осталось "
                        + countOfThisProductInStorage + " единиц. Товар не был добавлен в корзину.");
            } else if (person.getBasket().getProductFromBasket(thisProductInStorage) != null) {
                int countOfThisProductInBasket = person.getBasket().getProductFromBasket(thisProductInStorage).getCount();
                if (countOfThisProductInStorage < countInRequest + countOfThisProductInBasket) {
                    req.getSession().setAttribute("shopMessage", "Приносим свои извинения. На складе доступных осталось "
                            + (countOfThisProductInStorage - countOfThisProductInBasket) + " единиц. Товар не был добавлен в корзину.");
                } else {
                    person.getBasket().addProductToBasket(thisProductInStorage, countInRequest);
                    req.getSession().setAttribute("shopMessage", (thisProductInStorage.getName() + " добавлено в корзину."));
                }
            } else {
                person.getBasket().addProductToBasket(thisProductInStorage, countInRequest);
                req.getSession().setAttribute("shopMessage", (thisProductInStorage.getName() + " добавлено в корзину."));
            }
            getServletContext().getRequestDispatcher("/shopWithMessage.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("shopMessage", "Количество было введено не корректно.");
            getServletContext().getRequestDispatcher("/shopWithMessage.jsp").forward(req, resp);
        }
    }
}
