<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.profile.model.ProfileBean"%>
<jsp:useBean id="profile" class="com.profile.model.ProfileBean" scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Personal Profile | My Profile</title>
        <link href="https://fonts.googleapis.com/css2?family=Cormorant+Garamond:wght@500;600;700&family=Jost:wght@400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <%
            String error = (String) request.getAttribute("error");

            if (error == null && profile.getStudentID() == null)
            {
                response.sendRedirect("index.html");
                return;
            }
        %>

        <div class="hero hero-small">
            <h1>My Personal Profile</h1>
            <p class="nav-links">
                <a href="index.html">Add Profile</a>
                <a href="ViewProfilesServlet">View All Profiles</a>
            </p>
        </div>

        <div class="container">
            <div class="card">
                <% if (error != null) { %>

                <h2 class="card-title">Profile Not Saved</h2>
                <p class="alert alert-error"><%= error %></p>
                <div class="submit-row">
                    <a href="index.html" class="btn btn-primary">Back to Form</a>
                </div>

                <% } else {
                    String initial = profile.getName().trim().substring(0, 1).toUpperCase();
                %>

                <p class="alert alert-success">Your profile has been saved successfully.</p>

                <div class="profile-head">
                    <div class="avatar"><%= initial %></div>
                    <div>
                        <h2 class="profile-name"><jsp:getProperty name="profile" property="name"/></h2>
                        <p class="profile-programme"><jsp:getProperty name="profile" property="programme"/></p>
                    </div>
                </div>

                <h3 class="section-title">Personal Details</h3>
                <table class="info-table">
                    <tr>
                        <th>Full Name</th>
                        <td><jsp:getProperty name="profile" property="name"/></td>
                    </tr>
                    <tr>
                        <th>Student ID</th>
                        <td><jsp:getProperty name="profile" property="studentID"/></td>
                    </tr>
                    <tr>
                        <th>Programme</th>
                        <td><jsp:getProperty name="profile" property="programme"/></td>
                    </tr>
                    <tr>
                        <th>Email Address</th>
                        <td><jsp:getProperty name="profile" property="email"/></td>
                    </tr>
                    <tr>
                        <th>Hobbies</th>
                        <td><jsp:getProperty name="profile" property="hobbies"/></td>
                    </tr>
                    <tr>
                        <th>About Me</th>
                        <td><jsp:getProperty name="profile" property="introduction"/></td>
                    </tr>
                </table>

                <div class="submit-row">
                    <a href="index.html" class="btn btn-outline">Add Another</a>
                    <a href="ViewProfilesServlet" class="btn btn-primary">View All Profiles</a>
                </div>

                <% } %>
            </div>
        </div>
    </body>
</html>