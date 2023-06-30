package org.kainos.ea.cli;

import javax.ws.rs.Produces;

public class Project {
    private int projectId;
    private String name;
    private boolean status;
    private int clientId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Project(int projectId, String name, boolean status, int clientId){
        setProjectId(projectId);
        setName(name);
        setStatus(status);
        setClientId(clientId);
    }

}
