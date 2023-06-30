package org.kainos.ea.api;

import org.kainos.ea.cli.ProjectEmployee;
import org.kainos.ea.cli.ProjectEmployeeRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.ProjectEmployeeValidator;
import org.kainos.ea.db.ProjectEmployeeDao;

import java.sql.SQLException;
import java.util.*;
public class ProjectEmployeeService {
    private ProjectEmployeeDao projectEmployeeDao = new ProjectEmployeeDao();
    private ProjectEmployeeValidator projectEmployeeValidator = new ProjectEmployeeValidator();

    public List<ProjectEmployee> getAllProjectEmployees() throws FailedToGetProjectEmployeeException {
        try {
            List <ProjectEmployee> projectEmployeeList = projectEmployeeDao.getAllProjectEmployees();
            return projectEmployeeList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetProjectEmployeeException();
        }
    }

    public ProjectEmployee getProjectEmployeeById(int id) throws ProjectEmployeeDoesNotExistException, FailedToGetProjectEmployeeException {
        try {
            ProjectEmployee projectEmployee = projectEmployeeDao.getProjectEmployeeById(id);
            if (projectEmployee == null) {
                throw new ProjectEmployeeDoesNotExistException();
            }
            return projectEmployee;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetProjectEmployeeException();
        }
    }

    public int createProjectEmployee(ProjectEmployeeRequest projectEmployee) throws FailedToCreateProjectEmployeeException, InvalidProjectEmployeeException {
        try {
            String validation = projectEmployeeValidator.isValidProjectEmployee(projectEmployee);

            if (validation != null) {
                throw new InvalidProjectEmployeeException(validation);
            }

            int id = projectEmployeeDao.createProjectEmployee(projectEmployee);
            if (id == -1) {
                throw new FailedToCreateProjectEmployeeException();
            }
            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateProjectEmployeeException();
        }
    }

    public void updateProjectEmployee(int id, ProjectEmployeeRequest projectEmployee) throws InvalidProjectEmployeeException,
            ProjectEmployeeDoesNotExistException, FailedToUpdateProjectEmployeeException {
        try {
            String validation = projectEmployeeValidator.isValidProjectEmployee(projectEmployee);

            if (validation != null) {
                throw new InvalidProjectEmployeeException(validation);
            }

            ProjectEmployee projectEmployeeToUpdate = projectEmployeeDao.getProjectEmployeeById(id);

            if (projectEmployeeToUpdate == null) {
                throw new ProjectEmployeeDoesNotExistException();
            }

            projectEmployeeDao.updateProjectEmployee(id, projectEmployee);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateProjectEmployeeException();
        }
    }

    public void deleteProjectEmployee(int id) throws ProjectEmployeeDoesNotExistException, FailedToDeleteProjectEmployeeException {
        try {
            ProjectEmployee projectEmployeeToDelete = projectEmployeeDao.getProjectEmployeeById(id);
            if (projectEmployeeToDelete == null) {
                throw new ProjectEmployeeDoesNotExistException();
            }
            projectEmployeeDao.deleteProjectEmployee(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToDeleteProjectEmployeeException();
        }
    }
}