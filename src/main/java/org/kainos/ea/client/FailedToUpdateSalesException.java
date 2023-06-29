package org.kainos.ea.client;

public class FailedToUpdateSalesException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to update Sales in the database";
    }
}
