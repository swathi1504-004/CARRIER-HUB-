package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.JobListing;
import util.DBConnUtil;

public class JobListingDAOImpl implements JobListingDAO {
    public void insertJobListing(JobListing job) {
        try (Connection conn = DBConnUtil.getConnectionFromClasspath()) {
            String sql = "INSERT INTO job_listing VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, job.getJobID());
            ps.setInt(2, job.getCompanyID());
            ps.setString(3, job.getJobTitle());
            ps.setString(4, job.getJobDescription());
            ps.setString(5, job.getJobLocation());
            ps.setDouble(6, job.getSalary());
            ps.setString(7, job.getJobType());
            ps.setTimestamp(8, Timestamp.valueOf(job.getPostedDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JobListing> getAllJobListings() {
        List<JobListing> list = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnectionFromClasspath()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM job_listing");
            while (rs.next()) {
                JobListing job = new JobListing(
                    rs.getInt("Job_id"),
                    rs.getInt("Company_id"),
                    rs.getString("Job_title"),
                    rs.getString("Job_description"),
                    rs.getString("Job_location"),
                    rs.getDouble("Salary"),
                    rs.getString("Job_type"),
                    rs.getTimestamp("Posted_date").toLocalDateTime()
                );
                list.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<JobListing> getJobsBySalaryRange(double minSalary, double maxSalary) {
        List<JobListing> list = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnectionFromClasspath()) {
            String sql = "SELECT * FROM job_listing WHERE Salary BETWEEN ? AND ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, minSalary);
            ps.setDouble(2, maxSalary);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                JobListing job = new JobListing(
                    rs.getInt("Job_id"),
                    rs.getInt("Company_id"),
                    rs.getString("Job_title"),
                    rs.getString("Job_description"),
                    rs.getString("Job_location"),
                    rs.getDouble("Salary"),
                    rs.getString("Job_type"),
                    rs.getTimestamp("Posted_date").toLocalDateTime()
                );
                list.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}