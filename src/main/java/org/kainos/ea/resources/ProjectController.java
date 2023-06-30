package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.ProjectService;
import org.kainos.ea.client.FailedToGetProjectException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Engineering Academy Dropwizard Project API")
@Path("/api")
public class ProjectController {
    private ProjectService projectService = new ProjectService();
    @GET
    @Path("/Project")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjects() {

        try {
            return Response.ok(projectService.getAllProjects()).build();
        } catch (FailedToGetProjectException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
