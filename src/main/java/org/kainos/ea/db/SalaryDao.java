package org.kainos.ea.db;

import org.kainos.ea.cli.SalaryEmployee;
import org.kainos.ea.cli.SalaryRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<SalaryEmployee> getAllSalesEmp() throws SQLException {
        try (Connection c = databaseConnector.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT salesEmployeeid,name,salary, bankNumber, nationalInsuranceNum, commissionRate FROM SalaryEmployee;");
            List<SalaryEmployee> salList = new ArrayList<>();
            while (rs.next()) {
                SalaryEmployee sal = new SalaryEmployee(
                        rs.getInt("salesEmployeeid"),
                        rs.getString("name"),
                        rs.getFloat("salary"),
                        rs.getString("bankNumber"),
                        rs.getString("nationalInsuranceNum"),
                        rs.getFloat("commissionRate"));

                salList.add(sal);
            }
            return salList;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public SalaryEmployee getSalesById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT name,salary, bankNumber, nationalInsuranceNum, commissionRate FROM SalesEmployee where salesEmployeeid = " + id);
        while (rs.next()) {
            return new SalaryEmployee(
                    rs.getInt("salesEmployeeId"),
                    rs.getString("name"),
                    rs.getFloat("salary"),
                    rs.getString("bankNumber"),
                    rs.getString("nationalInsuranceNum"),
                    rs.getFloat("commisionRate"));


        }
        return null;
    }
    public int createSales(SalaryRequest sales) throws SQLException{
        Connection c = databaseConnector.getConnection();
        String insertStatement = "INSERT INTO SalesEmployee (name,salary, bankNumber, nationalInsuranceNum, commissionRate) VALUES (?,?,?)";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, sales.getName());
        st.setFloat(2,sales.getSalary());
        st.setString(3,sales.getBankNum());
        st.setString(4, sales.getNin());
        st.setFloat(5, sales.getComRate());
        st.executeUpdate();


        ResultSet rs = st.getGeneratedKeys();
        if(rs.next())
        {
            return rs.getInt(1);
        }
        return  -1;
    }
    public void updateSales(int id, SalaryRequest sales)   throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        String updateStatement = "UPDATE SalesEmployee SET name = ?, salary = ?, bankNumber = ?  commissionRate = ? Where salesEmployeeId = ?";
        PreparedStatement st = c.prepareStatement(updateStatement);
        st.setString(1, sales.getName());
        st.setFloat(2,sales.getSalary());
        st.setString(3,sales.getBankNum());
        st.setFloat(5, sales.getComRate());
        st.setInt(6, id);

        st.executeUpdate();
    }
    public  void DeleteSales(int id) throws SQLException
    {
        Connection c = DatabaseConnector.getConnection();
        String deleteStatement = "DELETE FROM SalesEmployee Where salesEmployeeId = ?";
        PreparedStatement st = c.prepareStatement(deleteStatement);
        st.setInt(1,id);
        st.executeUpdate();
    }
}
