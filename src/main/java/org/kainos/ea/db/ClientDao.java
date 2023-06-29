package org.kainos.ea.db;

import org.kainos.ea.cli.Client;
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

        ResultSet rs = st.executeQuery(dfvsdfv);

        List<ClientList> clientList = new ArrayList<>();

        while (rs.next()) {
            ClientList client = new ClientList(
                    rs.getString("Name"),
                    rs.getString("Employee Name")
                    rs.getString("Project Name")
            );
            clientList.add(client);
        }

        return clientList;
    }

}
