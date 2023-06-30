package org.kainos.ea.api;

import org.kainos.ea.cli.Project;
import org.kainos.ea.cli.ProjectEmployee;
import org.kainos.ea.client.FailedToGetProjectEmployeeException;
import org.kainos.ea.client.FailedToGetProjectException;
import org.kainos.ea.db.ProjectDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProjectService {
  private ProjectDao projectDao = new ProjectDao();

    public List<Project> getAllProjects() throws FailedToGetProjectException {
        try {
            List <Project> projectList = projectDao.getAllProjects();
            return projectList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetProjectException();
        }
    }
}
