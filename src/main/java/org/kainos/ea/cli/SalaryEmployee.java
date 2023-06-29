package org.kainos.ea.cli;

public class SalaryEmployee extends Employee{
    private  float comRate;

    public float getComRate() {
        return comRate;
    }

    public void setComRate(float comRate) {
        this.comRate = comRate;
    }

    public SalaryEmployee(int employeeId, String name, double salary, String bankNum, String nin, float comRate) {
        super(employeeId, name, salary, bankNum, nin);
        setComRate(comRate);
    }
}
