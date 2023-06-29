package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryRequest {
    private String name;
    private float salary;
    private  String bankNum;

    private  String nin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }


    public DeliveryRequest(@JsonProperty("clientId") String name, @JsonProperty("Salary")  float salary, @JsonProperty("BankNum")  String bankNum, @JsonProperty("NIN")  String nin) {
        setName(name);
        setSalary(salary);
        setBankNum(bankNum);
        setNin(nin);
    }
}
