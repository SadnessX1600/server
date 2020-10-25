package com.among.dev.server;

import com.among.dev.authentification.model.entities.User;
import com.among.dev.authentification.utilities.database.UserStorage;
import com.among.dev.server.interfaces.LoginInterface;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.Executor;

@Path("api")
public class Server implements LoginInterface {


    @Inject
    private Executor executor;
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
    @POST
    @Path("register")
    public void register(@QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password, AsyncResponse response) {
        User user = new User(username, password, email);
        executor.execute(() -> {
            try {
                userStorage.createUser(user);
                response.resume(true);
            } catch (Exception e) {
                response.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build());
            }
        });
    }

    @Override
    @GET
    @Path("login")
    public void login(@QueryParam("username") String username, @QueryParam("password") String password, AsyncResponse response) {
        User user = new User(username, password);
        executor.execute(() -> {
            try {
                response.resume(userStorage.getUser(user).isPresent() ? true : Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build());
            } catch (Exception e) {
                response.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build());
            }
        });


    }

}
