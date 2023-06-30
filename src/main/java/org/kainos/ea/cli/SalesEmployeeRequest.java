package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesEmployeeRequest {
    private String name;
    private float salary;
    private  String bankNum;
    private  String nin;
    private  float comRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
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

    public float getComRate() {
        return comRate;
    }

    public void setComRate(float comRate) {
        this.comRate = comRate;
    }

    public SalesEmployeeRequest(@JsonProperty("clientId") String name, @JsonProperty("salary")  float salary, @JsonProperty("bankNumber")  String bankNum, @JsonProperty("nationalInsuranceNum")  String nin, @JsonProperty("commissionRate")  float comRate) {
   setName(name);
   setSalary(salary);
   setBankNum(bankNum);
   setNin(nin);
   setComRate(comRate);
    }
}
