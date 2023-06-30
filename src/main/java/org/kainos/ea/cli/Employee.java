package org.kainos.ea.cli;


public abstract class Employee   {
    private int employeeId;
    private String name;
    private double salary;
    private  String bankNum;

    private  String nin;

    public Employee(int employeeId, String name, double salary, String bankNum, String nin) {
        setEmployeeId(employeeId);
        setName(name);
        setSalary(salary);
        setBankNum(bankNum);
        setNin(nin);

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

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
}
