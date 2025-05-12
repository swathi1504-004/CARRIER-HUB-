package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.JobApplication;
import util.DBConnUtil;

public class ApplicationDAOImpl implements ApplicationDAO {
    public void insertApplication(JobApplication application) {
        try (Connection conn = DBConnUtil.getConnectionFromClasspath()) {
            String sql = "INSERT INTO Job_application (application_id,job_id,applicant_id,application_date,cover_letter)VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, application.getApplicationID());
            ps.setInt(2, application.getJobID());
            ps.setInt(3, application.getApplicantID());
            ps.setTimestamp(4, Timestamp.valueOf(application.getApplicationDate()));
            ps.setString(5, application.getCoverLetter());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JobApplication> getApplicationsByJobId(int jobId) {
        List<JobApplication> list = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnectionFromClasspath()) {
            String sql = "SELECT * FROM job_application WHERE Job_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                JobApplication app = new JobApplication(
                    rs.getInt("Application_id"),
                    rs.getInt("Job_id"),
                    rs.getInt("Applicant_id"),
                    rs.getTimestamp("Application_date").toLocalDateTime(),
                    rs.getString("Cover_letter")
                );
                list.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
