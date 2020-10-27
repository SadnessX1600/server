package com.among.dev.server;

import com.among.dev.authentification.model.entities.User;
import com.among.dev.authentification.model.helpers.validators.EmailRegexpValidator;
import com.among.dev.authentification.model.helpers.validators.IEmailRegexpValidator;
import com.among.dev.authentification.utilities.database.UserStorage;
import com.among.dev.authentification.utilities.database.interfaces.DBUserInterface;
import com.among.dev.authentification.utilities.security.IMD5;
import com.among.dev.authentification.utilities.security.MD5Hasher;
import com.among.dev.server.interfaces.LoginInterface;
import org.glassfish.jersey.server.ManagedAsync;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.concurrent.Executor;

@Path("api")
public class Server implements LoginInterface {
    /* Private properties */
    private DBUserInterface userStorage;
    private IEmailRegexpValidator emailRegexpValidator;
    private IMD5 MD5Hasher;

    public Server() {
        userStorage = new UserStorage();
        emailRegexpValidator = new EmailRegexpValidator();
        MD5Hasher = new MD5Hasher();
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
        try {
            User user = new User(username, MD5Hasher.calculateMD5(password), email);
            userStorage.createUser(user);
            response.resume(true);
        } catch (Exception e) {
            System.out.println(e);
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
        User user = new User(username, MD5Hasher.calculateMD5(password));
        try {
            System.out.println("User:" + userStorage.getUser(user));
            response.resume(userStorage.getUser(user).isPresent() ? userStorage.getUser(user).get().getEmail() : Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("No such user in db")
                    .build());
        } catch (Exception e) {
            System.out.println(e);
            response.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("Error occurred")
                    .build());
        }
    }
}
