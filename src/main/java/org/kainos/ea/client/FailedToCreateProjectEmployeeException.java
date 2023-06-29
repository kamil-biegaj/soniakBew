package org.kainos.ea.client;

public class FailedToCreateProjectEmployeeException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to create ProjectEmployee.";
    }
}
