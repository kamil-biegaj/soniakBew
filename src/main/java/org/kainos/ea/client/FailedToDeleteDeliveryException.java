package org.kainos.ea.client;

public class FailedToDeleteDeliveryException extends Throwable {
    @Override
    public  String getMessage()
    {
        return "Failed to delete product in the database";
    }
}
