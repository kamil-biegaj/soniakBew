package org.kainos.ea.db;

import org.kainos.ea.cli.Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao{
    public List<Project> getProjectsList() throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT *\n" +
                "From Project\n");

        List<Project> projectList = new ArrayList<>();

        while (rs.next()) {
            Project client = new Project (
                    rs.getInt("Project"),
                    rs.getString("Name"),
                    rs.getBoolean("Status"),
                    rs.getInt("Client")

            );
            projectList.add(client);
        }

        return projectList;
    }

}

