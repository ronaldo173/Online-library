package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Santer on 13.03.2016.
 */
@WebServlet("/SecondServlet")
public class SecondServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        Map<String, String[]> parameterMap = req.getParameterMap();

        try {
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Servlet Title</title>");
            writer.println("</head>");

            writer.println("<body>");
            writer.println("<h1>Second Servlet... " + req.getContextPath() + "</h1>");
            for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()) {
                writer.println("<h1>Parametrs... " + stringEntry.getKey() + " " + Arrays.toString(stringEntry.getValue()) + "</h1>");
            }

            writer.println("</body>");
            writer.println("</html>");
        } finally {
            writer.close();
        }
    }
}
