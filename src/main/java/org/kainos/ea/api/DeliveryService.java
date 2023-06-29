package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.DeliveryValidator;
import org.kainos.ea.db.DeliveryDao;

import java.sql.SQLException;
import java.util.List;

public class DeliveryService {

    private DeliveryDao delDao = new DeliveryDao();
    private DeliveryValidator delValidator = new DeliveryValidator();

    public List<DeliveryEmployee> getAllDelivery() throws FailedToGetDeliverysException {
        List<DeliveryEmployee> delList = null;
        try {
            delList = delDao.getAllDeliveryEmp();
            return delList;
        } catch (SQLException e) {
            e.getMessage();
            throw new FailedToGetDeliverysException();
        }

    }

    public DeliveryEmployee getDeliveryById(int id) throws FailedToGetDeliverysException, DeliveryDoesNotExistException {
        try {
            DeliveryEmployee del = delDao.getDeliveryById(id);
            if (del == null) {
                throw new DeliveryDoesNotExistException();

            }
            return del;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetDeliverysException();
        }
    }

    public int createDelivery(DeliveryRequest del) throws FailedToCreateDeliveryException, InvalidDeliveryException {
        try {
            int id = delDao.createDelivery(del);
            if (id == 1) {
                throw new FailedToCreateDeliveryException();

            }
            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateDeliveryException();
        }
    }

    public void updateDelivery(int id, DeliveryRequest orderRequest) throws InvalidDeliveryException, DeliveryDoesNotExistException, SQLException, FailedToUpdateDeliveryException {
        try {
            String validation = delValidator.isValidDelivery(orderRequest);
            if (validation != null) {
                DeliveryEmployee delToUp = delDao.getDeliveryById(id);
                if (delToUp == null) {
                    throw new DeliveryDoesNotExistException();
                }
                delDao.updateDelivery(id, orderRequest);

            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToUpdateDeliveryException();
        }
    }
    public void deleteDelivery(int id) throws DeliveryDoesNotExistException, FailedToDeleteDeliveryException
    {
        try
        {
            DeliveryEmployee DeliveryToDelete = delDao.getDeliveryById(id);
            if(DeliveryToDelete == null)
            {
                throw new DeliveryDoesNotExistException();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToDeleteDeliveryException();
        }
    }
}
