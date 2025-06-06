package com.bigeye.rest.db;

import com.bigeye.rest.core.Login;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class LoginDAO extends AbstractDAO<Login> {

    public LoginDAO(SessionFactory factory) {
        super(factory);
    }

    public Login read(int id) {
        return get(id);
    }

    public Login create(Login person) {
        return persist(person);
    }
}
