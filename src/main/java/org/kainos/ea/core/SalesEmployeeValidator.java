package org.kainos.ea.core;

import org.kainos.ea.cli.SalesEmployeeRequest;

public class SalesEmployeeValidator {
    public  String isValidSales(SalesEmployeeRequest del) {
        if (del.getName().length() > 50) {
            return "Name greater than 50 characters";
        }
        if (del.getBankNum().length() > 50) {
            return "BankNum greater than 50 characters";
        }
        if (del.getNin().length() > 13) {
            return "National Insurance Number cant exceed 13 characters";
        }
        return null;
    }
}
