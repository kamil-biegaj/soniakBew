package org.kainos.ea.resources;

import org.kainos.ea.api.ClientService;
import org.kainos.ea.client.FailedToGetClientsException;

import io.swagger.annotations.Api;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Engineering Academy Dropwizard Client API")
@Path("/api")
public class ClientController {
    private ClientService clientService = new ClientService();
    @GET
    @Path("/client")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClients() {
        try {
            return Response.ok(clientService.getClientList()).build();
        } catch (FailedToGetClientsException e) {
            System.out.println(e.getMessage());

            return Response.serverError().build();
        }
    }

}
