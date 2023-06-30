package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryRequest;
import org.kainos.ea.cli.SalaryEmployee;
import org.kainos.ea.cli.SalaryRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.DeliveryValidator;
import org.kainos.ea.core.SalesValidator;
import org.kainos.ea.db.DeliveryDao;
import org.kainos.ea.db.SalaryDao;

import java.sql.SQLException;
import java.util.List;

public class SalaryService {
    private SalaryDao delDao = new SalaryDao();
    private SalesValidator delValidator = new SalesValidator();

    public List<SalaryEmployee> getAllSales() throws FailedToGetDeliverysException {
        List<SalaryEmployee> delList = null;
        try {
            delList = delDao.getAllSalesEmp();
            return delList;
        } catch (SQLException e) {
            e.getMessage();
            throw new FailedToGetDeliverysException();
        }

    }

    public SalaryEmployee getsalesById(int id) throws FailedToGetDeliverysException, DeliveryDoesNotExistException {
        try {
            SalaryEmployee del = delDao.getSalesById(id);
            if (del == null) {
                throw new DeliveryDoesNotExistException();

            }
            return del;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetDeliverysException();
        }
    }

    public int createSales(SalaryRequest del) throws FailedToCreateSalesException, InvalidSalesException {
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

    public void updateSales(int id, SalaryRequest orderRequest) throws InvalidSalesException, SalesDoesNotExistException, SQLException, FailedToUpdateSalesException {
        try {
            String validation = delValidator.isValidSales(orderRequest);
            if (validation != null) {
                SalaryEmployee delToUp = delDao.getSalesById(id);
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
            SalaryEmployee DeliveryToDelete = delDao.getSalesById(id);
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
