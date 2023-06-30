package org.kainos.ea.api;

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
