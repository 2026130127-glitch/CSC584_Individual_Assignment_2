<%-- 
    Document   : editProfile
    Created on : Nov 24, 2026, 1:09:29 AM
    Author     : User
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.profile.model.ProfileBean"%>
<%!
    private String safe(String text)
    {
        if (text == null)
        {
            return "";
        }
        return text.replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Personal Profile | Edit Profile</title>
        <link href="https://fonts.googleapis.com/css2?family=Cormorant+Garamond:wght@500;600;700&family=Jost:wght@400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <%
            ProfileBean profile = (ProfileBean) request.getAttribute("profile");

            if (profile == null)
            {
                response.sendRedirect("ViewProfilesServlet");
                return;
            }
        %>

        <div class="hero hero-small">
            <h1>Edit Profile</h1>
            <p class="nav-links">
                <a href="index.html">Add Profile</a>
                <a href="ViewProfilesServlet">View All Profiles</a>
            </p>
        </div>

        <div class="container">
            <form action="EditProfileServlet" method="post" class="card">
                <h2 class="card-title">Update Information</h2>
                <p class="card-text">Student ID cannot be changed.</p>

                <label for="studentId">Student ID</label>
                <input type="text" id="studentId" name="studentId" value="<%= safe(profile.getStudentID()) %>" class="readonly" readonly>

                <label for="name">Full Name</label>
                <input type="text" id="name" name="name" maxlength="100" value="<%= safe(profile.getName()) %>" required>

                <label for="programme">Programme</label>
                <input type="text" id="programme" name="programme" maxlength="100" value="<%= safe(profile.getProgramme()) %>" required>

                <label for="email">Email Address</label>
                <input type="email" id="email" name="email" maxlength="100" value="<%= safe(profile.getEmail()) %>" required>

                <label for="hobbies">Hobbies</label>
                <input type="text" id="hobbies" name="hobbies" maxlength="255" value="<%= safe(profile.getHobbies()) %>" required>
                <p class="hint">Separate each hobby with a comma.</p>

                <label for="intro">Short Self-Introduction</label>
                <textarea id="intro" name="intro" rows="5" maxlength="500" required><%= safe(profile.getIntroduction()) %></textarea>

                <div class="submit-row">
                    <a href="ViewProfilesServlet" class="btn btn-outline">Cancel</a>
                    <input type="submit" value="Update Profile" class="btn btn-primary">
                </div>
            </form>
        </div>
    </body>
</html>
