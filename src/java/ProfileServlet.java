import com.profile.dao.ProfileDAO;
import com.profile.model.ProfileBean;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Farzana
 */
public class ProfileServlet extends HttpServlet {

    /**
     * Processes requests for HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String studentId = request.getParameter("studentId");
        String name = request.getParameter("name");
        String programme = request.getParameter("programme");
        String email = request.getParameter("email");
        String hobbies = request.getParameter("hobbies");
        String intro = request.getParameter("intro");

        ProfileBean profile = new ProfileBean();
        profile.setStudentID(studentId);
        profile.setName(name);
        profile.setProgramme(programme);
        profile.setEmail(email);
        profile.setHobbies(hobbies);
        profile.setIntroduction(intro);

        try {
            if (studentId == null || studentId.trim().isEmpty() || name == null || name.trim().isEmpty()) {
                request.setAttribute("error", "Student ID and Full Name are required.");
            } else {
                ProfileDAO dao = new ProfileDAO();
                dao.insertProfile(profile);

                ProfileBean savedProfile = dao.getProfileById(studentId);
                request.setAttribute("profile", savedProfile);
            }
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                request.setAttribute("error", "Student ID " + studentId + " already exists. Please use another Student ID.");
            } else {
                request.setAttribute("error", "Database error: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            request.setAttribute("error", "Database driver not found: " + e.getMessage());
        }

        RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * The form must be submitted using POST, so GET goes back to the form.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Profile Servlet";
    }
}