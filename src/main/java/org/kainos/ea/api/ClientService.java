package org.kainos.ea.api;

import org.kainos.ea.cli.ClientList;
import org.kainos.ea.client.FailedToGetClientsException;
import org.kainos.ea.db.ClientDao;

import java.sql.SQLException;
import java.util.List;

public class ClientService {

    private ClientDao clientDao = new ClientDao();

    public List<ClientList> getClientList() throws FailedToGetClientsException {
        try {
            List<ClientList> clientList = clientDao.getClientsList();

            return clientList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetClientsException();
        }
    }

}
