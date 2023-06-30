package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.DeliveryService;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;

@Api("Engineering Academy Dropwizard Delivery API")
@Path("/api")
public class DeliveryEmployeeController {
    private DeliveryService delservice = new DeliveryService();
    @GET
    @Path("/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDelivery()
    {
        try {
            return Response.ok(delservice.getAllDelivery()).build();
        } catch (FailedToGetDeliverysException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }
    @GET
    @Path("/delivery/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getdeliveryById(@PathParam("id") int id)
    {
        try {
            try {
                return Response.ok(delservice.getDeliveryById(id)).build();
            } catch (DeliveryDoesNotExistException e) {
                throw new RuntimeException(e);
            }
        } catch (FailedToGetDeliverysException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @POST
    @Path("/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDelivery(DeliveryEmployeeRequest del)
    {
        try {
            return Response.status(Response.Status.CREATED).entity(delservice.createDelivery(del)).build();
        }
        catch (FailedToCreateDeliveryException e) {

            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (InvalidDeliveryException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/delivery/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDelivery(@PathParam("id") int id, DeliveryEmployeeRequest delRequest)
    {
        try {
            delservice.updateDelivery(id,delRequest);
            return  Response.status(Response.Status.ACCEPTED).build();
        }
        catch (DeliveryDoesNotExistException | InvalidDeliveryException e) {
            System.err.println(e.getMessage());
            return  Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToUpdateDeliveryException e) {
            System.err.println(e.getMessage());
            return  Response.serverError().build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @DELETE
    @Path("/delivery/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDelivery(@PathParam("id") int id)
    {
        try {
            delservice.deleteDelivery(id);
            return Response.ok().build();
        } catch (DeliveryDoesNotExistException e) {
            System.err.println(e.getMessage());
            return  Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToDeleteDeliveryException e) {
            throw new RuntimeException(e);
        }
    }
}
