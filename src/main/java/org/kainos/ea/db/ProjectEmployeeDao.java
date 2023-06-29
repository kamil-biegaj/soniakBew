package org.kainos.ea.db;

import org.kainos.ea.cli.ProjectEmployee;
import org.kainos.ea.cli.ProjectEmployeeRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectEmployeeDao {

    private dataBaseConnector dataBaseConnector = new dataBaseConnector();

    public List<ProjectEmployee> getAllProjectEmployees() throws SQLException {
        Connection c = dataBaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT projectEmployeeId, projectID, employeeId, onProject " +
                "FROM Project_Employee;");

        List<ProjectEmployee> projectEmployeeList = new ArrayList<>();

        while (rs.next()) {
            ProjectEmployee projectEmployee = new ProjectEmployee (
                    rs.getInt("projectEmployeeId"),
                    rs.getInt("projectId"),
                    rs.getInt("employeeId"),
                    rs.getBoolean("onProject")
            );
            projectEmployeeList.add(projectEmployee);
        }

        return projectEmployeeList;
    }

    public ProjectEmployee getProjectEmployeeById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT projectEmployeeId, projectID, employeeId, onProject " +
                "FROM Project_Employee WHERE projectEmployeeId=" + id);

        while (rs.next()) {
            return new ProjectEmployee(
                    rs.getInt("projectEmployeeId"),
                    rs.getInt("projectId"),
                    rs.getInt("employeeId"),
                    rs.getBoolean("onProject")
            );
        }

        return null;
    }

    public int createProjectEmployee(ProjectEmployeeRequest projectEmployee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Project_Employee (projectId, employeeId, onProject) VALUES (?,?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1, projectEmployee.getProjectId());
        st.setInt(2, projectEmployee.getEmployeeId());
        st.setBoolean(3, projectEmployee.getOnProject());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public void updateProjectEmployee(int id, ProjectEmployeeRequest projectEmployee) throws SQLException {
        Connection c = databaseConnector.getConnection();
        String updateStatement = "UPDATE Project_Employee SET projectId = ?, employeeId = ?, onProject = ?" +
                " WHERE projectEmployeeId = ?";
        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setInt(1, projectEmployee.getProjectId());
        st.setInt(2, projectEmployee.getEmployeeId());
        st.setBoolean(3, projectEmployee.getOnProject());
        st.setInt(4, id);

        st.executeUpdate();

    }

    public void deleteProjectEmployee(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        String deleteStatement = "DELETE FROM Project_Employee WHERE projectEmployeeId = ?";
        PreparedStatement st = c.prepareStatement(deleteStatement);
        st.setInt(1, id);
        st.executeUpdate();
    }

}
