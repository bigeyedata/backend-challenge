package com.bigeye.rest.db;

import com.bigeye.rest.core.Person;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class PersonDAO extends AbstractDAO<Person> {
    public PersonDAO(SessionFactory factory) {
        super(factory);
    }

    public Person read(int id) {
        return get(id);
    }

    public Person create(Person person) {
        return persist(person);
    }
}
