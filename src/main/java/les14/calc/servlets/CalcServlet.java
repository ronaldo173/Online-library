package les14.calc.servlets;

import les14.calc.calc.CalcOperations;
import les14.calc.calc.OperationType;
import les14.calc.calc.TestObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet("/CalcServlet")
public class CalcServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet CalcServlet</title>");
        out.println("</head>");
        out.println("<body>");

        try {

            // считывание параметров
            double one = Double.valueOf(request.getParameter("one"));
            double two = Double.valueOf(request.getParameter("two"));
            String opearation = request.getParameter("operation");

            // определение или создание сессии
            HttpSession session = request.getSession(true);

            request.getServletContext().setAttribute("obj", new TestObject("TestName"));


            // получение типа операции
            OperationType operType = OperationType.valueOf(opearation.toUpperCase());

            // калькуляция
            double result = calcRes(operType, one, two);

            ArrayList<String> listOperations;

            // для новой сессии создаем новый список
            if (session.isNew()) {
                listOperations = new ArrayList<String>();
            }
            else { // иначе получаем список из атрибутов сессии
                listOperations = (ArrayList<String>) session.getAttribute("formula");
            }

            // добавление новой операции в список и атрибут сессии
            listOperations.add(one + " " + operType.getStringValue() + " " + two + " = " + result);
            session.setAttribute("formula", listOperations);


            // вывод всех операций
            out.println("<h1>ID вашей сессии равен: " + session.getId() + "</h1>");

            out.println("<h3>Список операций (всего:" + listOperations.size() + ") </h3>");

            for (String oper : listOperations) {
                out.println("<h3>" + oper + "</h3>");
            }


        } catch (Exception ex) {
            // предупреждение пользователю в случае ошибки
            out.println("</br>");
            out.println(ex.toString());
            out.println("</br>");
            out.println("<h3 style=\"color:red;\">Возникла ошибка. Проверьте параметры</h3>");
            out.println("<h3>Имена параметров должны быть one, two, operation</h3>");
            out.println("<h3>Operation должен принимать 1 из 4 значений: add, subtract, multiply, divide</h3>");
            out.println("<h3>Значения one и two должны быть числами</h3>");
            out.println("<h1>Пример</h1>");
            out.println("<h3>?one=1&two=3&operation=add</h3>");

        } finally {
            out.println("</body>");
            out.println("</html>");
            out.close();
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
