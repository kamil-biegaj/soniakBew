package org.kainos.ea.api;

import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesEmployeeRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.SalesEmployeeValidator;
import org.kainos.ea.db.SalesEmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class SalesEmployeeService {
    private SalesEmployeeDao delDao = new SalesEmployeeDao();
    private SalesEmployeeValidator delValidator = new SalesEmployeeValidator();

    public List<SalesEmployee> getAllSales() throws FailedToGetDeliverysException {
        List<SalesEmployee> delList = null;
        try {
            delList = delDao.getAllSalesEmp();
            return delList;
        } catch (SQLException e) {
            e.getMessage();
            throw new FailedToGetDeliverysException();
        }

    }

    public SalesEmployeeRequest getsalesById(int id) throws FailedToGetDeliverysException, DeliveryDoesNotExistException {
        try {
            SalesEmployeeRequest del = delDao.getSalesById(id);
            if (del == null) {
                throw new DeliveryDoesNotExistException();

            }
            return del;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetDeliverysException();
        }
    }

    public int createSales(SalesEmployeeRequest del) throws FailedToCreateSalesException, InvalidSalesException {
        try {
            int id = delDao.createSales(del);
            if (id == 1) {
                throw new FailedToCreateSalesException();

            }
            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateSalesException();
        }
    }

    public void updateSales(int id, SalesEmployeeRequest orderRequest) throws InvalidSalesException, SalesDoesNotExistException, SQLException, FailedToUpdateSalesException {
        try {
            String validation = delValidator.isValidSales(orderRequest);
            if (validation != null) {
                SalesEmployeeRequest delToUp = delDao.getSalesById(id);
                if (delToUp == null) {
                    throw new SalesDoesNotExistException();
                }
                delDao.updateSales(id, orderRequest);

            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToUpdateSalesException();
        }
    }
    public void deleteSales(int id) throws SalesDoesNotExistException, FailedToDeleteSalesException
    {
        try
        {
            SalesEmployeeRequest DeliveryToDelete = delDao.getSalesById(id);
            if(DeliveryToDelete == null)
            {
                throw new SalesDoesNotExistException();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToDeleteSalesException();
        }
    }
}
