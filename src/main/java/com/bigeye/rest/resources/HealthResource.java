package com.bigeye.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/health")
@Produces(MediaType.APPLICATION_JSON)
public class HealthResource {

    public HealthResource() { }

    @GET
    public String healthCheck() {
        return "ok\n";
    }

}
