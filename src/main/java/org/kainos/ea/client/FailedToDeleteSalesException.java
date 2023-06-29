package org.kainos.ea.client;

public class FailedToDeleteSalesException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to delete Sales in the database";
    }
}
