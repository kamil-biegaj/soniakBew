package org.kainos.ea.client;

public class FailedToGetDeliverysException extends Throwable {
    @Override
    public  String getMessage()
    {
        return "Failed to get Delivery in the database";
    }
}
