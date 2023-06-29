package org.kainos.ea.client;

public class InvalidSalesException extends Throwable {
    public InvalidSalesException(String validation) {
        super(validation);
    }
}
