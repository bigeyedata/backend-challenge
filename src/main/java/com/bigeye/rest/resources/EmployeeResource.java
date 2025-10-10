package com.bigeye.rest.resources;

import com.bigeye.rest.core.Employee;
import com.bigeye.rest.db.EmployeeDAO;
import com.sun.jersey.api.NotFoundException;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private final EmployeeDAO employeeDAO;

    public EmployeeResource(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Employee getPerson(@PathParam("id") int employeeId) {
        Employee employee = employeeDAO.read(employeeId);
        if (employee == null) {
            throw new NotFoundException("No such user.");
        }
        return employee;
    }
}
