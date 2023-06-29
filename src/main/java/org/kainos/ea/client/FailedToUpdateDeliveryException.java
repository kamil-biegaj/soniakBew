package org.kainos.ea.client;

public class FailedToUpdateDeliveryException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to update Delivery in the database";
    }
}