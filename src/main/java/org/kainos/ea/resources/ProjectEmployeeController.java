package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.ProjectEmployeeService;
import org.kainos.ea.cli.ProjectEmployeeRequest;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Engineering Academy Dropwizard ProjectEmployee API")
@Path("/api")
public class ProjectEmployeeController {
    private ProjectEmployeeService projectEmployeeService = new ProjectEmployeeService();

    @GET
    @Path("/project_employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectEmployees() {

        try {
            return Response.ok(projectEmployeeService.getAllProjectEmployees()).build();
        } catch (FailedToGetProjectEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/project_employees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectEmployeesById(@PathParam("id") int id) {
        try {
            return Response.ok(projectEmployeeService.getProjectEmployeeById(id)).build();
        } catch (FailedToGetProjectEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (ProjectEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/project_employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProjectEmployee(ProjectEmployeeRequest projectEmployee) {
        try {
            return Response.status(Response.Status.CREATED).entity(projectEmployeeService.createProjectEmployee(projectEmployee)).build();
        } catch (FailedToCreateProjectEmployeeException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (InvalidProjectEmployeeException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/project_employees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProjectEmployee(@PathParam("id") int id, ProjectEmployeeRequest projectEmployee) {
        try {
            projectEmployeeService.updateProjectEmployee(id, projectEmployee);
            return Response.ok().build();
        } catch (InvalidProjectEmployeeException | ProjectEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToUpdateProjectEmployeeException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/project_employees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProjectEmployee(@PathParam("id") int id) {
        try {
            projectEmployeeService.deleteProjectEmployee(id);
            return Response.ok().build();
        } catch (ProjectEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToDeleteProjectEmployeeException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

}
