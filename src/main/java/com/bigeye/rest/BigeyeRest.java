package com.bigeye.rest;

import com.bigeye.rest.core.Login;
import com.bigeye.rest.core.Person;
import com.bigeye.rest.db.LoginDAO;
import com.bigeye.rest.db.PersonDAO;
import com.bigeye.rest.resources.HealthResource;
import com.bigeye.rest.resources.PersonResource;
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
            new HibernateBundle<BigeyeRestConfiguration>(Person.class, Login.class) {
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
        final PersonDAO personDAO = new PersonDAO(hibernateBundle.getSessionFactory());
        final LoginDAO loginDAO = new LoginDAO(hibernateBundle.getSessionFactory());

        environment.addResource(new HealthResource());
        environment.addResource(new PersonResource(personDAO));
    }
}
