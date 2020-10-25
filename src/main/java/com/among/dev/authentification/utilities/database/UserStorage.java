package com.among.dev.authentification.utilities.database;

import com.among.dev.authentification.model.entities.User;
import com.among.dev.authentification.utilities.database.enums.DBCommands;
import com.among.dev.authentification.utilities.database.interfaces.DBConnectionInterface;
import com.among.dev.authentification.utilities.database.interfaces.DBUserInterface;
import org.hibernate.Session;

import java.util.Optional;

public class UserStorage implements DBUserInterface {
    private Session session;
    private DBConnectionInterface<User> dbConnector;

    public UserStorage() {
        dbConnector = new DBConnector<User>(User.class);
    }

    @Override
    public void createUser(User user) {
        dbConnector.executeCommand(DBCommands.CREATE, user);
    }

    @Override
    public void updateUser(User user) {
        dbConnector.executeCommand(DBCommands.UPDATE, user);
    }

    @Override
    public void deleteUser(User user) {
        dbConnector.executeCommand(DBCommands.DELETE, user);
    }

    @Override
    public Optional<User> getUser(User user) {
        return dbConnector.executeCommand(DBCommands.READ, user);
    }

}
