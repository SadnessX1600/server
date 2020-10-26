package com.among.dev.server;

import com.among.dev.authentification.model.entities.User;
import com.among.dev.authentification.utilities.database.UserStorage;
import com.among.dev.server.interfaces.LoginInterface;
import org.glassfish.jersey.server.ManagedAsync;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.Executor;

@Path("api")
public class Server implements LoginInterface {
    /* Private properties */
    private UserStorage userStorage;

    public Server() {
        userStorage = new UserStorage();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @Override
    @ManagedAsync
    @POST
    @Path("register")
    public void register(@QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password, @Suspended AsyncResponse response) {
        User user = new User(username, password, email);
        try {
            userStorage.createUser(user);
            response.resume(true);
        } catch (Exception e) {
            response.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("Operation timed out")
                    .build());
        }
    }

    @Override
    @ManagedAsync
    @GET
    @Path("login")
    public void login(@QueryParam("username") String username, @QueryParam("password") String password, @Suspended AsyncResponse response) {
        User user = new User(username, password);
        try {
            response.resume(userStorage.getUser(user).isPresent() ? true : Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("No such user in db")
                    .build());
        } catch (Exception e) {
            response.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("Error occurred")
                    .build());
        }
    }
}
