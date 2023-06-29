package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectEmployeeRequest {
    private int projectId;
    private int employeeId;
    private boolean onProject;

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

    public boolean getOnProject() {
        return onProject;
    }

    public void setOnProject(boolean onProject) {
        this.onProject = onProject;
    }

    @JsonCreator
    public ProjectEmployeeRequest(
            @JsonProperty("projectId") int projectId,
            @JsonProperty("employeeId") int employeeId,
            @JsonProperty("onProject") boolean onProject) {
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.onProject = onProject;
    }
}