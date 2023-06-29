package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalaryRequest {
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

    public SalaryRequest(@JsonProperty("clientId") String name, @JsonProperty("Salary")  float salary, @JsonProperty("BankNum")  String bankNum, @JsonProperty("NIN")  String nin, @JsonProperty("ComRate")  float comRate) {
   setName(name);
   setSalary(salary);
   setBankNum(bankNum);
   setNin(nin);
   setComRate(comRate);
    }
}
