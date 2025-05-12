package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Applicant;
import util.DBConnUtil;

public class ApplicantDAOImpl implements ApplicantDAO {
    public void insertApplicant(Applicant applicant) {
        try (Connection conn = DBConnUtil.getConnectionFromClasspath()) {
            String sql = "INSERT INTO Applicant (applicant_id,first_name,last_name,email,phone,resume)VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, applicant.getApplicantID());
            ps.setString(2, applicant.getFirstName());
            ps.setString(3, applicant.getLastName());
            ps.setString(4, applicant.getEmail());
            ps.setString(5, applicant.getPhone());
            ps.setString(6, applicant.getResume());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Applicant> getAllApplicants() {
        List<Applicant> list = new ArrayList<>();
        try (Connection conn = util.DBConnUtil.getConnectionFromClasspath()) {  // ✅ Correct new method

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Applicant");
            while (rs.next()) {
                Applicant a = new Applicant(
                    rs.getInt("Applicant_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("Resume")
                );
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}