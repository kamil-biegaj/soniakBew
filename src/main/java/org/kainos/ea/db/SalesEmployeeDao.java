package org.kainos.ea.db;

import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesEmployeeRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesEmployeeDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<SalesEmployee> getAllSalesEmp() throws SQLException {
        try (Connection c = databaseConnector.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT salesEmployeeid,name,salary, bankNumber, nationalInsuranceNum, commissionRate FROM SalesEmployee;");
            List<SalesEmployee> salList = new ArrayList<>();
            while (rs.next()) {
                SalesEmployee sal = new SalesEmployee(
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

    public SalesEmployeeRequest getSalesById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT name,salary, bankNumber, nationalInsuranceNum, commissionRate FROM SalesEmployee WHERE salesEmployeeid = " + id);
        while (rs.next()) {
            return new SalesEmployeeRequest(
                    rs.getString("name"),
                    rs.getFloat("salary"),
                    rs.getString("bankNumber"),
                    rs.getString("nationalInsuranceNum"),
                    rs.getFloat("commissionRate"));


        }
        return null;
    }
    public int createSales(SalesEmployeeRequest sales) throws SQLException{
        Connection c = databaseConnector.getConnection();
        String insertStatement = "INSERT INTO SalesEmployee (name, salary, bankNumber, nationalInsuranceNum, commissionRate) VALUES (?,?,?,?,?)";
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
    public void updateSales(int id, SalesEmployeeRequest sales)   throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        String updateStatement = "UPDATE SalesEmployee SET name = ?, salary = ?, bankNumber = ?, nationalInsuranceNumber = ?, commissionRate = ? WHERE salesEmployeeId = ?";
        PreparedStatement st = c.prepareStatement(updateStatement);
        st.setString(1, sales.getName());
        st.setFloat(2,sales.getSalary());
        st.setString(3,sales.getBankNum());
        st.setString(4, sales.getNin());
        st.setFloat(5, sales.getComRate());
        st.setInt(6, id);

        st.executeUpdate();
    }
    public  void deleteSales(int id) throws SQLException
    {
        Connection c = DatabaseConnector.getConnection();
        String deleteStatement = "DELETE FROM SalesEmployee WHERE salesEmployeeId = ?";
        PreparedStatement st = c.prepareStatement(deleteStatement);
        st.setInt(1,id);
        st.executeUpdate();
    }
}
