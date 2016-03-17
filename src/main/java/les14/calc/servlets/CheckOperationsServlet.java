package les14.calc.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Santer on 17.03.2016.
 */
public class CheckOperationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();


        writer.println("<html>");
        writer.println("<title>Servlet  - check</title>");
        writer.println("<body>");


        try {
            HttpSession session = req.getSession(true);
            Object attr = session.getAttribute("formula");

            if (attr instanceof ArrayList) {
                ArrayList list = (ArrayList) attr;
                writer.print("<h1>Operations: </h1>");
                for (Object o : list) {
                    writer.println("<h3>" + o + "</h3>");
                }
            }
        } finally {
            writer.println("</body>");
            writer.println("</html>");
        }

    }
}

