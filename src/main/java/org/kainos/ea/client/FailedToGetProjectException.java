package org.kainos.ea.client;

public class FailedToGetProjectException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to retrive project(s).";
    }
}
