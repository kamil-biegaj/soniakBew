package org.kainos.ea.client;

public class FailedToGetClientsException extends Throwable {
    @Override
    public String getMessage() {
        return "Could not get clients";
    }
}
