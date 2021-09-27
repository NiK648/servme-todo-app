
package com.servme.todoapp.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.servme.todoapp.model.Category;
import com.servme.todoapp.model.Todo;
import com.servme.todoapp.service.TodoService;

@Path("/todo")
public class TodoRest {

	@Inject
	TodoService todoService;

	@POST
	@Path("/category/add")
	@RolesAllowed("USER")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCategory(Category category) {
		todoService.addCategory(category);
		return "Success";
	}

	@POST
	@Path("/category/remove")
	@RolesAllowed("USER")
	@Produces(MediaType.TEXT_PLAIN)
	public String removeCategory(Category category) {
		todoService.removeCategory(category);
		return "Success";
	}

	@POST
	@Path("/todo/add")
	@RolesAllowed("USER")
	@Produces(MediaType.TEXT_PLAIN)
	public String addTodo(Todo todo) {
		todoService.addTodo(todo);
		return "Success";
	}

	@POST
	@Path("/todo/update")
	@RolesAllowed("USER")
	@Produces(MediaType.TEXT_PLAIN)
	public String modifyTodo(Todo todo) {
		todoService.updateTodo(todo);
		return "Success";
	}
	
	@POST
	@Path("/todo/list")
	@RolesAllowed("USER")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Todo> listTodos(Todo todo) {
		return todoService.listTodos(todo);
	}
}
