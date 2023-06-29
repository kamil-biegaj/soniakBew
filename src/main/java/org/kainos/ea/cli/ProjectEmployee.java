package org.kainos.ea.cli;

public class ProjectEmployee {
    private int projectEmployeeId;
    private int projectId;
    private int employeeId;
    private boolean onProject;

    public ProjectEmployee(int projectEmployeeId, int projectId, int employeeId, boolean onProject) {
        this.projectEmployeeId = projectEmployeeId;
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.onProject = onProject;
    }

    public int getProjectEmployeeId() {
        return projectEmployeeId;
    }

    public void setProjectEmployeeId(int projectEmployeeId) {
        this.projectEmployeeId = projectEmployeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isOnProject() {
        return onProject;
    }

    public void setOnProject(boolean onProject) {
        this.onProject = onProject;
    }
}
