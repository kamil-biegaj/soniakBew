package org.kainos.ea.db;


import org.kainos.ea.cli.ClientList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    public List<ClientList> getClientsList() throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT Client.name as `Client Name`, SalesEmployee.name as `Sales Employee Name`, Project.name as `Project name`\n" +
                "From Client\n" +
                "JOIN Project USING (clientId)\n" +
                "JOIN SalesEmployee USING (salesEmployeeID);");

        List<ClientList> clientList = new ArrayList<>();

        while (rs.next()) {
            ClientList client = new ClientList (
                    rs.getString("Client Name"),
                    rs.getString("Sales Employee Name"),
                    rs.getString("Project Name")
            );
            clientList.add(client);
        }

        return clientList;
    }

}
