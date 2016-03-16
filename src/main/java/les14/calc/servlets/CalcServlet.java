package les14.calc.servlets;

import les14.calc.calc.CalcOperations;
import les14.calc.calc.OperationType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Santer on 16.03.2016.
 */
public class CalcServlet extends HttpServlet {

    private List<String> listOperations = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Servlet Calculator</title>");
        writer.println("</head>");
        writer.println("<body>");

        try {
            double one = Double.valueOf(req.getParameter("one").toString()).doubleValue();
            double two = Double.valueOf(req.getParameter("two").toString()).doubleValue();
            String operation = String.valueOf(req.getParameter("operation"));

            HttpSession session = req.getSession(true);
            OperationType operationType = OperationType.valueOf(operation.toUpperCase());

            double result = calcRes(operationType, one, two);

            if (session.isNew()) {
                listOperations.clear();
            }

            listOperations.add(one + " " + operationType.getStringValue() + " " + two + " = " + result);
            session.setAttribute("formula: ", listOperations);

            writer.println("<h1>Id session: " + session.getId() + "</h1>");
            writer.println("<h3>Operations amount: " + listOperations.size() + "</h3>");

            for (String listOperation : listOperations) {
                writer.print("<h4>" + operation + "</h4>");
            }
        } catch (Exception e) {
            System.out.println("error...");
        } finally {
            writer.println("</body>");
            writer.println("</html>");

            if (writer != null) {
                writer.close();
            }
        }
    }

    private double calcRes(OperationType operationType, double one, double two) {
        double result = 0;

        switch (operationType) {
            case ADD:
                result = CalcOperations.add(one, two);
                break;
            case SUBSTRACT:
                result = CalcOperations.substract(one, two);
                break;
            case DIVIDE:
                result = CalcOperations.divide(one, two);
                break;
            case MULTIPLY:
                result = CalcOperations.multiply(one, two);
                break;
        }
        return result;
    }
}
