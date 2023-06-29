package org.kainos.ea.client;

public class ProjectEmployeeDoesNotExistException extends Exception {
    @Override
    public String getMessage() {
        return "ProjectEmployee doesn't exist.";
    }
}
