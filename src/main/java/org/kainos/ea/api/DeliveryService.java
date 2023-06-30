package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.DeliveryEmployeeValidator;
import org.kainos.ea.db.DeliveryEmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class DeliveryService {

    private DeliveryEmployeeDao delDao = new DeliveryEmployeeDao();
    private DeliveryEmployeeValidator delValidator = new DeliveryEmployeeValidator();

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

    public DeliveryEmployeeRequest getDeliveryById(int id) throws FailedToGetDeliverysException, DeliveryDoesNotExistException {
        try {
            DeliveryEmployeeRequest del = delDao.getDeliveryById(id);
            if (del == null) {
                throw new DeliveryDoesNotExistException();

            }
            return del;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetDeliverysException();
        }
    }

    public int createDelivery(DeliveryEmployeeRequest delivery) throws FailedToCreateDeliveryException, InvalidDeliveryException {
        try {
            int id = delDao.createDelivery(delivery);
            if (id == 1) {
                throw new FailedToCreateDeliveryException();

            }
            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateDeliveryException();
        }
    }

    public void updateDelivery(int id, DeliveryEmployeeRequest delivery) throws InvalidDeliveryException, DeliveryDoesNotExistException, SQLException, FailedToUpdateDeliveryException {
        try {
            String validation = delValidator.isValidDelivery(delivery);

            if (validation != null) {
                throw new InvalidDeliveryException(validation);
            }

            DeliveryEmployeeRequest deliveryToUpdate = delDao.getDeliveryById(id);

            if (deliveryToUpdate == null) {
                throw new DeliveryDoesNotExistException();
            }

            delDao.updateDelivery(id, delivery);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToUpdateDeliveryException();
        }
    }
    public void deleteDelivery(int id) throws DeliveryDoesNotExistException, FailedToDeleteDeliveryException
    {
        try
        {
            DeliveryEmployeeRequest DeliveryToDelete = delDao.getDeliveryById(id);
            if(DeliveryToDelete == null)
            {
                throw new DeliveryDoesNotExistException();
            }
            delDao.deleteDelivery(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToDeleteDeliveryException();
        }
    }
}
