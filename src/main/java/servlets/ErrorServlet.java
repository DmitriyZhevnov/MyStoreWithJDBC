package servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Error")
public class ErrorServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(ErrorServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.error("ошибка");
        getServletContext().getRequestDispatcher("/errorPage.jsp").forward(req, resp);
    }
}
