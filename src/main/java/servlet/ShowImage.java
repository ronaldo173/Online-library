package servlet;

import beans.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Santer on 26.03.2016.
 */
@WebServlet("/ShowImage")
public class ShowImage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("image/jpeg");
        try (OutputStream outputStream = resp.getOutputStream())

        {
            int index = Integer.valueOf(req.getParameter("index"));

            List<Book> list = (List<Book>) req.getSession(false).getAttribute("currentBookList");
            Book book = list.get(index);

            int length = book.getImage().length;
            resp.setContentLength(length);
            outputStream.write(book.getImage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
