/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.profile.dao.ProfileDAO;
import com.profile.model.ProfileBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Farzana
 */
public class ViewProfilesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String keyword = request.getParameter("keyword");
        List<ProfileBean> profiles = new ArrayList<ProfileBean>();

        try {
            ProfileDAO dao = new ProfileDAO();

            if (keyword != null && !keyword.trim().isEmpty()) {
                keyword = keyword.trim();
                profiles = dao.searchProfiles(keyword);
            } else {
                keyword = "";
                profiles = dao.getAllProfiles();
            }
        } catch (Exception e) {
            request.setAttribute("error", "Database error: " + e.getMessage());
        }

        request.setAttribute("profiles", profiles);
        request.setAttribute("keyword", keyword);

        RequestDispatcher rd = request.getRequestDispatcher("viewProfiles.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "View Profiles Servlet";
    }
}
