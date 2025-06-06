package com.bigeye.rest.resources;

import com.bigeye.rest.core.Person;
import com.bigeye.rest.db.PersonDAO;
import com.sun.jersey.api.NotFoundException;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final PersonDAO personDAO;

    public PersonResource(PersonDAO peopleDAO) {
        this.personDAO = peopleDAO;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Person getPerson(@PathParam("id") int personId) {
        Person person = personDAO.read(personId);
        if (person == null) {
            throw new NotFoundException("No such user.");
        }
        return person;
    }
}
