package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryRequest;
import org.kainos.ea.cli.SalaryEmployee;
import org.kainos.ea.cli.SalaryRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<DeliveryEmployee> getAllDeliveryEmp() throws SQLException {
        try (Connection c = databaseConnector.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT DeliveryEmpId,Name,Salary, BankNo, NIN, comRate FROM SalaryEmployee;");
            List<DeliveryEmployee> delList = new ArrayList<>();
            while (rs.next()) {
                DeliveryEmployee del = new DeliveryEmployee(
                        rs.getInt("SalesEmpId"),
                        rs.getString("Name"),
                        rs.getFloat("Salary"),
                        rs.getString("BanNo"),
                        rs.getString("NIN"));

                delList.add(del);
            }
            return delList;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public DeliveryEmployee getDeliveryById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT Name,Salary, BankNo, NIN,  FROM DeliveryEmployee where DeliveryEmpId = " + id);
        while (rs.next()) {
            return new DeliveryEmployee(
                    rs.getInt("deliveryEmpId"),
                    rs.getString("Name"),
                    rs.getFloat("Salary"),
                    rs.getString("bankNo"),
                    rs.getString("NIN"));


        }
        return null;
    }
    public int createDelivery(DeliveryRequest sales) throws SQLException{
        Connection c = databaseConnector.getConnection();
        String insertStatement = "INSERT INTO DeliveryEmployee (name,salary, bankNumber, nationalInsuranceNum) VALUES (?,?,?)";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, sales.getName());
        st.setFloat(2,sales.getSalary());
        st.setString(3,sales.getBankNum());
        st.setString(4, sales.getNin());
        st.executeUpdate();


        ResultSet rs = st.getGeneratedKeys();
        if(rs.next())
        {
            return rs.getInt(1);
        }
        return  -1;
    }
    public void updateDelivery(int id, DeliveryRequest sales)   throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        String updateStatement = "UPDATE DeliveryEmployee SET name = ?, salary = ?, bankNumber = ? nationalInsuranceNum = ? Where deliveryEmployeeId = ?";
        PreparedStatement st = c.prepareStatement(updateStatement);
        st.setString(1, sales.getName());
        st.setFloat(2,sales.getSalary());
        st.setString(3,sales.getBankNum());
        st.setString(4, sales.getNin());
        st.setInt(5, id);

        st.executeUpdate();
    }
    public  void DeleteDelivery(int id) throws SQLException
    {
        Connection c = DatabaseConnector.getConnection();
        String deleteStatement = "DELETE FROM DeliveryEmployee Where DeliveryEmployeeId = ?";
        PreparedStatement st = c.prepareStatement(deleteStatement);
        st.setInt(1,id);
        st.executeUpdate();
    }
}