package org.kainos.ea.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class ProjectController {
    @GET
    @Path("/Project")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProjects() {
        return "List of Projects";
    }
}
