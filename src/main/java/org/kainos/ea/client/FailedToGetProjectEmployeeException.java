package org.kainos.ea.client;

public class FailedToGetProjectEmployeeException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get ProjectEmployee(s).";
    }
}
