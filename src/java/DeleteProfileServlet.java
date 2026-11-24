/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.profile.dao.ProfileDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Farzana
 */
public class DeleteProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("ViewProfilesServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentId = request.getParameter("id");

        try {
            ProfileDAO dao = new ProfileDAO();
            dao.deleteProfile(studentId);
            response.sendRedirect("ViewProfilesServlet?msg=deleted");
        } catch (Exception e) {
            response.sendRedirect("ViewProfilesServlet?msg=error");
        }
    }

    @Override
    public String getServletInfo() {
        return "Delete Profile Servlet";
    }
}
