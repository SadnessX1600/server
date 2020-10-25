package com.among.dev.authentification.utilities.database.interfaces;

import com.among.dev.authentification.utilities.database.enums.DBCommands;

import java.util.Optional;

public interface DBConnectionInterface<T extends DBEntity> {

    Optional<T> executeCommand(DBCommands command, T object);
}
