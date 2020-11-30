package servlets;

import classes.Person;
import classes.Product;
import classes.StorageOfProducts;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = (Person) req.getSession().getAttribute("currentUser");
        if (person.getBasket().getBasket().size() == 0) {
            getServletContext().getRequestDispatcher("/basketEmpty.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/basket.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = (Person) req.getSession().getAttribute("currentUser");
        if (req.getParameter("operationInBasket").equals("modifyProduct")) {
            try {
                Product thisProductInBasket = person.getBasket().getProductByIdFromBasket(Integer
                        .parseInt(req.getParameter("idProduct")));
                int countInRequest = Integer.parseInt(req.getParameter("count"));
                //int countOfThisProductInStorage = StorageOfProducts.getProductInStorage(thisProductInBasket).getCount();
                if (countInRequest == 0) {
                    req.getSession().setAttribute("shopMessage", "Значение 0 - недопустимо. Просто удалите заказ.");
                    getServletContext().getRequestDispatcher("/basketWithMessage.jsp").forward(req, resp);
//                } else if (countInRequest >= 1) {
//                    if (countInRequest > countOfThisProductInStorage) {
//                        req.getSession().setAttribute("shopMessage", "Приносим свои извинения. На складе осталось "
//                                + countOfThisProductInStorage + " единиц(а). Изменения не вступили в силу.");
                    } else {
                        thisProductInBasket.setCount(countInRequest);
                        req.getSession().setAttribute("shopMessage", ("Количество " + thisProductInBasket.getName() + " изменено"));
                    }
                    getServletContext().getRequestDispatcher("/basketWithMessage.jsp").forward(req, resp);
//                } else {
//                    throw new NumberFormatException();
//                }
            } catch (NumberFormatException e) {
                req.getSession().setAttribute("shopMessage", "Количество было введено не корректно.");
                getServletContext().getRequestDispatcher("/basketWithMessage.jsp").forward(req, resp);
            }
        } else if (req.getParameter("operationInBasket").equals("deleteProduct")) {
            Product thisProductInBasket = person.getBasket().getProductByIdFromBasket(Integer
                    .parseInt(req.getParameter("idProduct")));
            person.getBasket().removeProductFromBasket(thisProductInBasket);
            req.getSession().setAttribute("shopMessage", thisProductInBasket.getName() + " был удален из корзины");
            getServletContext().getRequestDispatcher("/basketWithMessage.jsp").forward(req, resp);
        } else if (req.getParameter("operationInBasket").equals("makeOrder")) {
            if (req.getParameter("address").isEmpty() || req.getParameter("phoneNumber").isEmpty()) {
                req.getSession().setAttribute("message", "Обязательно введите Адрес и Номер телефона");
                getServletContext().getRequestDispatcher("/makeOrderWithMessage.jsp").forward(req, resp);
            } else {
                person.setAddress(req.getParameter("address"));
                person.setPhoneNumber(req.getParameter("phoneNumber"));
                person.buyBasket();
                getServletContext().getRequestDispatcher("/successfulOrder.jsp").forward(req, resp);
            }
        }
    }
}

