/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profile.dao;

import com.profile.model.ProfileBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Farzana
 */
public class ProfileDAO {
    
    private static final String DB_URL = "jdbc:derby://localhost:1527/StudentProfilesDB";
    private static final String DB_USER = "app";
    private static final String DB_PASSWORD = "app";

    //database connection
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    //insert profile
    public void insertProfile(ProfileBean profile) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Profile (studentID, name, programme, email, hobbies, introduction) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, profile.getStudentID());
            ps.setString(2, profile.getName());
            ps.setString(3, profile.getProgramme());
            ps.setString(4, profile.getEmail());
            ps.setString(5, profile.getHobbies());
            ps.setString(6, profile.getIntroduction());
            ps.executeUpdate();
        }
    }

    //Retrieve all profiles
    public List<ProfileBean> getAllProfiles() throws ClassNotFoundException, SQLException {
        List<ProfileBean> list = new ArrayList<ProfileBean>();
        String sql = "SELECT * FROM Profile ORDER BY studentID";

        try (Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(readProfile(rs));
            }
        }
        return list;
    }

    //etrieve one profile using the student ID
    public ProfileBean getProfileById(String studentID) throws ClassNotFoundException, SQLException {
        ProfileBean profile = null;
        String sql = "SELECT * FROM Profile WHERE studentID = ?";

        try (Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    profile = readProfile(rs);
                }
            }
        }
        return profile;
    }

    //Search profiles by student ID or name
    public List<ProfileBean> searchProfiles(String keyword) throws ClassNotFoundException, SQLException {
        List<ProfileBean> list = new ArrayList<ProfileBean>();
        String sql = "SELECT * FROM Profile WHERE LOWER(studentID) LIKE ? OR LOWER(name) LIKE ? "
                + "ORDER BY studentID";
        String search = "%" + keyword.toLowerCase() + "%";

        try (Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, search);
            ps.setString(2, search);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(readProfile(rs));
                }
            }
        }
        return list;
    }

    //Update a profile 
    public void updateProfile(ProfileBean profile) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Profile SET name = ?, programme = ?, email = ?, hobbies = ?, introduction = ? "
                + "WHERE studentID = ?";

        try (Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, profile.getName());
            ps.setString(2, profile.getProgramme());
            ps.setString(3, profile.getEmail());
            ps.setString(4, profile.getHobbies());
            ps.setString(5, profile.getIntroduction());
            ps.setString(6, profile.getStudentID());
            ps.executeUpdate();
        }
    }

    //Delete a profile (Option C)
    public void deleteProfile(String studentID) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Profile WHERE studentID = ?";

        try (Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentID);
            ps.executeUpdate();
        }
    }

    private ProfileBean readProfile(ResultSet rs) throws SQLException {
        ProfileBean profile = new ProfileBean();
        profile.setStudentID(rs.getString("studentID"));
        profile.setName(rs.getString("name"));
        profile.setProgramme(rs.getString("programme"));
        profile.setEmail(rs.getString("email"));
        profile.setHobbies(rs.getString("hobbies"));
        profile.setIntroduction(rs.getString("introduction"));
        return profile;
    }
}
    
