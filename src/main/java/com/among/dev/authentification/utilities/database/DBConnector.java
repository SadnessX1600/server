package com.among.dev.authentification.utilities.database;

import com.among.dev.authentification.utilities.database.enums.DBCommands;
import com.among.dev.authentification.utilities.database.interfaces.DBConnectionInterface;
import com.among.dev.authentification.utilities.database.interfaces.DBEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Optional;


public class DBConnector<T extends DBEntity> implements DBConnectionInterface {
    private Session session;
    private Class<T> dbClass;

    public DBConnector(Class<T> clazz) {
        this.dbClass = clazz;
    }

    public Optional<T> executeCommand(DBCommands command, DBEntity object) {
        session = getSessionFactory().openSession();
        session.beginTransaction();

        switch (command) {
            case CREATE -> session.save(object);
            case UPDATE -> session.update(object);
            case DELETE -> session.delete(object);
            case READ -> {
                T result = session.find(dbClass, object);
                session.getTransaction().commit();
                session.close();
                return result != null ? Optional.of(result) : Optional.empty();
            }
        }

        session.getTransaction().commit();
        session.close();

        return Optional.empty();
    }

    private SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(dbClass);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }
}
