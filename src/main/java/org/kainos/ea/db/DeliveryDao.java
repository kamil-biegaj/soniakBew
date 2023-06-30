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
            ResultSet rs = st.executeQuery("SELECT DeliveryEmployeeId,name,salary, bankNumber, nationalInsuranceNum FROM DeliveryEmployee;");
            List<DeliveryEmployee> delList = new ArrayList<>();
            while (rs.next()) {
                DeliveryEmployee del = new DeliveryEmployee(
                        rs.getInt("deliveryEmployeeId"),
                        rs.getString("name"),
                        rs.getFloat("salary"),
                        rs.getString("bankNumber"),
                        rs.getString("nationalInsuranceNum"));

                delList.add(del);
            }
            return delList;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public DeliveryRequest getDeliveryById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT name, salary, bankNumber, nationalInsuranceNum FROM DeliveryEmployee where deliveryEmployeeId = " + id);
        while (rs.next()) {
            return new DeliveryRequest(
                    rs.getString("name"),
                    rs.getFloat("salary"),
                    rs.getString("bankNumber"),
                    rs.getString("nationalInsuranceNum"));


        }
        return null;
    }
    public int createDelivery(DeliveryRequest delivery) throws SQLException{
        Connection c = databaseConnector.getConnection();
        String insertStatement = "INSERT INTO DeliveryEmployee (name, salary, bankNumber, nationalInsuranceNum) VALUES (?,?,?,?)";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, delivery.getName());
        st.setFloat(2,delivery.getSalary());
        st.setString(3,delivery.getBankNum());
        st.setString(4, delivery.getNin());
        st.executeUpdate();


        ResultSet rs = st.getGeneratedKeys();
        if(rs.next())
        {
            return rs.getInt(1);
        }
        return  -1;
    }
    public void updateDelivery(int id, DeliveryRequest delivery) throws SQLException {
        Connection c = databaseConnector.getConnection();
        String updateStatement = "UPDATE DeliveryEmployee SET name = ?, salary = ?, bankNumber = ?, nationalInsuranceNum = ? WHERE deliveryEmployeeId = ?";
        PreparedStatement st = c.prepareStatement(updateStatement);
        st.setString(1, delivery.getName());
        st.setFloat(2, delivery.getSalary());
        st.setString(3, delivery.getBankNum());
        st.setString(4, delivery.getNin());
        st.setInt(5, id);

        st.executeUpdate();
    }
    public void deleteDelivery(int id) throws SQLException
    {
        Connection c = databaseConnector.getConnection();
        String deleteStatement = "DELETE FROM DeliveryEmployee Where DeliveryEmployeeId = ?";
        PreparedStatement st = c.prepareStatement(deleteStatement);
        st.setInt(1,id);
        st.executeUpdate();
    }
}