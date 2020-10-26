package com.among.dev;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class ApplicationStarter extends ResourceConfig {
    public ApplicationStarter() {
        packages("com.among.dev.server");
    }
}
