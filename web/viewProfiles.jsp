<%-- 
    Document   : viewProfiles
    Created on : Nov 24, 2026, 1:06:15 AM
    Author     : User
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.profile.model.ProfileBean"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Personal Profile | All Profiles</title>
        <link href="https://fonts.googleapis.com/css2?family=Cormorant+Garamond:wght@500;600;700&family=Jost:wght@400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <%
            List<ProfileBean> profiles = (List<ProfileBean>) request.getAttribute("profiles");

            if (profiles == null)
            {
                response.sendRedirect("ViewProfilesServlet");
                return;
            }

            String keyword = (String) request.getAttribute("keyword");
            if (keyword == null)
            {
                keyword = "";
            }

            String error = (String) request.getAttribute("error");
            String msg = request.getParameter("msg");
        %>

        <div class="hero hero-small">
            <h1>All Student Profiles</h1>
            <p class="nav-links">
                <a href="index.html">Add Profile</a>
                <a href="ViewProfilesServlet" class="active">View All Profiles</a>
            </p>
        </div>

        <div class="container container-wide">
            <div class="card">
                <h2 class="card-title">Profile List</h2>
                <p class="card-text">Total profiles: <%= profiles.size() %></p>

                <%-- Messages after edit or delete --%>
                <% if ("updated".equals(msg)) { %>
                    <p class="alert alert-success">Profile updated successfully.</p>
                <% } else if ("deleted".equals(msg)) { %>
                    <p class="alert alert-success">Profile deleted successfully.</p>
                <% } else if ("notfound".equals(msg)) { %>
                    <p class="alert alert-error">Profile not found.</p>
                <% } else if ("error".equals(msg)) { %>
                    <p class="alert alert-error">Something went wrong. Please try again.</p>
                <% } %>

                <% if (error != null) { %>
                    <p class="alert alert-error"><%= error %></p>
                <% } %>

                <form action="ViewProfilesServlet" method="get" class="search-bar">
                    <input type="text" name="keyword" value="<%= keyword.replace("\"", "&quot;") %>" placeholder="Search by Student ID or Name">
                    <input type="submit" value="Search" class="btn btn-primary">
                    <a href="ViewProfilesServlet" class="btn btn-outline">Show All</a>
                </form>

                <% if (profiles.isEmpty()) { %>

                    <p class="empty-text">No profile found.</p>

                <% } else { %>

                <div class="table-box">
                    <table class="data-table">
                        <tr>
                            <th>No.</th>
                            <th>Student ID</th>
                            <th>Name</th>
                            <th>Programme</th>
                            <th>Email</th>
                            <th>Hobbies</th>
                            <th>Introduction</th>
                            <th>Action</th>
                        </tr>
                        <%
                            for (int i = 0; i < profiles.size(); i++)
                            {
                                ProfileBean p = profiles.get(i);
                        %>
                        <tr>
                            <td><%= i + 1 %></td>
                            <td><%= p.getStudentID() %></td>
                            <td class="name-cell"><%= p.getName() %></td>
                            <td><%= p.getProgramme() %></td>
                            <td><%= p.getEmail() %></td>
                            <td><%= p.getHobbies() %></td>
                            <td class="intro-cell"><%= p.getIntroduction() %></td>
                            <td class="action-cell">
                                <a href="EditProfileServlet?id=<%= p.getStudentID() %>" class="btn-small btn-edit">Edit</a>

                                <form action="DeleteProfileServlet" method="post" class="inline-form"
                                      onsubmit="return confirm('Are you sure you want to delete this profile?');">
                                    <input type="hidden" name="id" value="<%= p.getStudentID() %>">
                                    <input type="submit" value="Delete" class="btn-small btn-delete">
                                </form>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </div>

                <% } %>
            </div>
        </div>
    </body>
</html>
