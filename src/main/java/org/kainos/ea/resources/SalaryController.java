package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.DeliveryService;
import org.kainos.ea.api.SalaryService;
import org.kainos.ea.cli.DeliveryRequest;
import org.kainos.ea.cli.SalaryRequest;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Engineering Academy Dropwizard Sales API")
@Path("/api")
public class SalaryController {
    private SalaryService salservice = new SalaryService();
    @GET
    @Path("/sales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders()
    {
        try {
            return Response.ok(salservice.getAllSales()).build();
        } catch (FailedToGetDeliverysException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }
    @GET
    @Path("/sales/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("id") int id)
    {
        try {
            try {
                return Response.ok(salservice.getsalesById(id)).build();
            } catch (DeliveryDoesNotExistException e) {
                throw new RuntimeException(e);
            }
        } catch (FailedToGetDeliverysException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @POST
    @Path("/sales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(SalaryRequest del)
    {
        try {
            return Response.status(Response.Status.CREATED).entity(salservice.createSales(del)).build();
        }
        catch (FailedToCreateSalesException e) {

            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
         catch (InvalidSalesException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/sales/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrder(@PathParam("id") int id, SalaryRequest delRequest)
    {
        try {
            salservice.updateSales(id,delRequest);
            return  Response.status(Response.Status.ACCEPTED).build();
        }
        catch (SalesDoesNotExistException | InvalidSalesException e) {
            System.err.println(e.getMessage());
            return  Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToUpdateSalesException e) {
            System.err.println(e.getMessage());
            return  Response.serverError().build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }


    @DELETE
    @Path("/sales/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSales(@PathParam("id") int id)
    {
        try {
            salservice.deleteSales(id);
            return Response.ok().build();
        } catch (SalesDoesNotExistException e) {
            System.err.println(e.getMessage());
            return  Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToDeleteSalesException e) {
            throw new RuntimeException(e);
        }
    }
}
