import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/result")
public class Result extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        pw.println("<html>");
        String forCur = request.getParameter("forCurrency");
        String toCur = request.getParameter("toCurrency");
        int value = Integer.parseInt(request.getParameter("value"));
        try {
            pw.println(Converter.convert(value,forCur,toCur));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        pw.println("</html>");
    }
}