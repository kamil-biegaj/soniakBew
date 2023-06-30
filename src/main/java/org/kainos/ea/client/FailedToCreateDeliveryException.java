package org.kainos.ea.client;

public class FailedToCreateDeliveryException extends Throwable {
    @Override
    public  String getMessage()
    {
        return "Failed to update product in the database";
    }
}
