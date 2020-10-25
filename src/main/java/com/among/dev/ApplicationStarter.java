package com.among.dev;

import com.among.dev.server.Server;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class ApplicationStarter extends Application {


    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> returnValue = new HashSet<>();
        returnValue.add(Server.class);
        return returnValue;
    }
}
