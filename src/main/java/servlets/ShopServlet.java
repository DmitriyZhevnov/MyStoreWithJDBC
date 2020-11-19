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
    ///удалить потом
    @Override
    public void init() throws ServletException {
        StorageOfProducts storageOfProducts = new StorageOfProducts();
    }
    //////

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/shop.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = (Person) req.getSession().getAttribute("currentUser");
        try {
            Product thisProduct = StorageOfProducts.getProductInStorageById(Integer
                    .parseInt(req.getParameter("idProduct")));
            int countOfThisProduct = thisProduct.getCount();
            if (countOfThisProduct < Integer.parseInt(req.getParameter("count"))){
                req.getSession().setAttribute("shopMessage", "Приносим свои извинения. На складе осталось "
                        + countOfThisProduct + " единиц. Товар не был добавлен в корзину.");
            } else {
                person.getBasket().addProductToBasket(thisProduct, Integer.parseInt(req.getParameter("count")));
                req.getSession().setAttribute("shopMessage", "добавлено в корзину");
            }
            getServletContext().getRequestDispatcher("/shopWithMessage.jsp").forward(req, resp);
        } catch (NumberFormatException e){
            req.getSession().setAttribute("shopMessage", "Количество было введено не корректно.");
            getServletContext().getRequestDispatcher("/shopWithMessage.jsp").forward(req, resp);
        }
        System.out.println(person.getBasket().toString());
    }
}
