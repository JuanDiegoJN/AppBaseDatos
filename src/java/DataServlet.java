
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title></title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> " + request.getContextPath() + "</h1>");
            out.println("<h2>Lista de Usuarios</h2>");
            out.println("</body>");
            out.println("</html>");
            out.println("<tr>"); 
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Edad</th>");
            out.println("<th>Teléfono <br> </th>");
            out.println("</tr>");

            try {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DataServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                try (Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/personas", "root", "Admin$1234"); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM datos")) {

                    while (rs.next()) {
                        out.println("<tr><td>" + rs.getInt("idPersonas") + "</td>");
                        out.println("<td>" + rs.getString("Nombre") + "</td>");
                        out.println("<td>" + rs.getString("Apellido") + "</td></tr>");
                        out.println("<td>" + rs.getString("Edad") + "</td>");
                        out.println("<td>" + rs.getString("Telefono") + "<br>" + "</td>");
                       
                    }
                    
                }
            } catch (SQLException e) {
                out.println("<tr><td colspan='3'>Error: " + e.getMessage() + "</td></tr>");
            }
            out.println("</table>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
