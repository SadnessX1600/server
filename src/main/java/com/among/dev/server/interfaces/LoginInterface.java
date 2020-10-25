package com.among.dev.server.interfaces;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;

public interface LoginInterface {
    @POST
    @Path("/register")
    void register(@QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password, AsyncResponse response);

    @GET
    @Path("/login")
    void login(@QueryParam("username") String username, @QueryParam("password") String password, AsyncResponse response);
}
