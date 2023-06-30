package org.kainos.ea.api;

import org.kainos.ea.cli.Project;
import org.kainos.ea.db.ProjectDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProjectService {
  private ProjectDao projectDao = new ProjectDao();

    public List<Project> getProjectList(){
        try {
            List<Project> projectLists;
            projectLists = projectDao.getProjectsList();

            return projectLists;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
}
