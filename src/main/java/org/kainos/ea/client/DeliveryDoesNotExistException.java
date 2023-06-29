package org.kainos.ea.client;

public class DeliveryDoesNotExistException
    extends Throwable {
        @Override
        public  String getMessage()
        {
            return "This Delivery does not exist in database";
        }
}
