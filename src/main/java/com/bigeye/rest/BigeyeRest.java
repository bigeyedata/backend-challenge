package com.bigeye.rest;

import com.bigeye.rest.core.Employee;
import com.bigeye.rest.db.EmployeeDAO;
import com.bigeye.rest.resources.EmployeeResource;
import com.bigeye.rest.resources.HealthResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.migrations.MigrationsBundle;

public class BigeyeRest extends Service<BigeyeRestConfiguration> {
    public static void main(String[] args) throws Exception {
        new BigeyeRest().run(args);
    }

    private final HibernateBundle<BigeyeRestConfiguration> hibernateBundle =
            new HibernateBundle<BigeyeRestConfiguration>(Employee.class) {
                @Override
                public DatabaseConfiguration getDatabaseConfiguration(
                    BigeyeRestConfiguration configuration) {
                    return configuration.getDatabaseConfiguration();
                }
            };

    @Override
    public void initialize(Bootstrap<BigeyeRestConfiguration> bootstrap) {
        bootstrap.setName("hello-world");
        bootstrap.addBundle(new MigrationsBundle<BigeyeRestConfiguration>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(
                BigeyeRestConfiguration configuration) {
                return configuration.getDatabaseConfiguration();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(BigeyeRestConfiguration configuration,
                    Environment environment) {
        final EmployeeDAO employeeDAO = new EmployeeDAO(hibernateBundle.getSessionFactory());

        environment.addResource(new HealthResource());
        environment.addResource(new EmployeeResource(employeeDAO));
    }
}
