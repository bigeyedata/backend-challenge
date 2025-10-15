package com.bigeye.rest.db;

import com.bigeye.rest.core.Employee;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class EmployeeDAO extends AbstractDAO<Employee> {
    public EmployeeDAO(SessionFactory factory) {
        super(factory);
    }

    public Employee read(int id) {
        return get(id);
    }

    public Employee create(Employee employee) {
        return persist(employee);
    }
}
