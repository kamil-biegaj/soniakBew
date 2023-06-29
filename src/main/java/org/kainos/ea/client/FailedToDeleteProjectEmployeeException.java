package org.kainos.ea.client;

public class FailedToDeleteProjectEmployeeException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to delete ProjectEmployee.";
    }
}
