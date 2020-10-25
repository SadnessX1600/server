package com.among.dev.authentification.utilities.database.interfaces;

import com.among.dev.authentification.model.entities.User;

import java.util.Optional;

public interface DBUserInterface {
    void createUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    Optional<User> getUser(User user);
}
