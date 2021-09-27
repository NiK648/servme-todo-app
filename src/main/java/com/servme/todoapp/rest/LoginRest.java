package com.servme.todoapp.rest;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.servme.todoapp.enums.Role;
import com.servme.todoapp.model.LoginResponse;
import com.servme.todoapp.model.TodoUser;
import com.servme.todoapp.security.TokenUtil;
import com.servme.todoapp.service.TodoUserService;

@Path("")
public class LoginRest {

	@Inject
	TodoUserService todoUserSevice;

	@ConfigProperty(name = "com.servme.todoapp.jwt.duration")
	public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer")
	public String issuer;

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String register(TodoUser user) {
		todoUserSevice.registerUser(user);
		return "Success";
	}

	@PermitAll
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(TodoUser user) {
		TodoUser dbUser = todoUserSevice.loginUser(user.getEmail());
		if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
			try {
				Set<Role> roles = new HashSet<>();
				roles.add(Role.USER);
				return Response
						.ok(new LoginResponse(TokenUtil.generateToken(dbUser.getEmail(), roles, duration, issuer)))
						.build();
			} catch (Exception e) {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
}
