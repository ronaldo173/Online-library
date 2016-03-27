package servlet;

import beans.BookList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Santer on 13.03.2016.
 */
@WebServlet("/SecondServlet")
public class SecondServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        byte[] img = new BookList().getBookList().get(0).getImage();

        try (ServletOutputStream stream = resp.getOutputStream()) {


            resp.setContentType("text/html");
            stream.println("<html><head><title>test IMAGE</title></head>");
            stream.println("<body><h1>go go go...img </h1>");

            resp.setContentType("image/jpeg");
            stream.write(img);

            stream.println("</body></html>");
        }


    }
}
