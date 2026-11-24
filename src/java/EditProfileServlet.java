/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.profile.dao.ProfileDAO;
import com.profile.model.ProfileBean;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Farzana
 */
public class EditProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String studentId = request.getParameter("id");

        try {
            ProfileDAO dao = new ProfileDAO();
            ProfileBean profile = dao.getProfileById(studentId);

            if (profile == null) {
                response.sendRedirect("ViewProfilesServlet?msg=notfound");
                return;
            }

            request.setAttribute("profile", profile);
            RequestDispatcher rd = request.getRequestDispatcher("editProfile.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("ViewProfilesServlet?msg=error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        ProfileBean profile = new ProfileBean();
        profile.setStudentID(request.getParameter("studentId"));
        profile.setName(request.getParameter("name"));
        profile.setProgramme(request.getParameter("programme"));
        profile.setEmail(request.getParameter("email"));
        profile.setHobbies(request.getParameter("hobbies"));
        profile.setIntroduction(request.getParameter("intro"));

        try {
            ProfileDAO dao = new ProfileDAO();
            dao.updateProfile(profile);
            response.sendRedirect("ViewProfilesServlet?msg=updated");
        } catch (Exception e) {
            response.sendRedirect("ViewProfilesServlet?msg=error");
        }
    }

    @Override
    public String getServletInfo() {
        return "Edit Profile Servlet";
    }
}