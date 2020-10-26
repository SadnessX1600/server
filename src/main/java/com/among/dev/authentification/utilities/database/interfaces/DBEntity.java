package com.among.dev.authentification.utilities.database.interfaces;

import java.io.Serializable;

public interface DBEntity extends Serializable {
    String getPK();

    String getPKName();
}
