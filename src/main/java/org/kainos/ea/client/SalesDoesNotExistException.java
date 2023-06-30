package org.kainos.ea.client;

public class SalesDoesNotExistException extends Throwable {
    @Override
    public String getMessage() {
        return "Sales Does not exist in the database";
    }
}
