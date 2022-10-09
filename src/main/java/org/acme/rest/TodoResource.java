package org.acme.rest;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.model.TodoRepository;
import org.acme.model.entity.Todo;

@Path("/api")
@Produces("application/json")
@Consumes("application/json")
public class TodoResource {

	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	@Inject
	private TodoRepository todoRepository;

	@OPTIONS
	public Response opt() {
		return Response.ok().build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Todo> getAll() {
		return this.todoRepository.findAll();
	}

	@GET
	@Path("/{id}")
	public Todo getOne(@PathParam("id") Long id) {
		Todo entity = this.todoRepository.findById(id);
		return entity;
	}

	@POST
	@Transactional
	public Response create(@Valid Todo item) {
		this.todoRepository.persistTodo(item);
		return Response.status(Response.Status.CREATED).entity(item).build();

	}
	@PATCH
	@Path("/{id}")
	@Transactional
	public Response update(@Valid Todo todo, @PathParam("id") Long id) {
		Todo entity = this.todoRepository.findById(id);
		entity.id = id;
		entity.completed = todo.completed;
		entity.order = todo.order;
		entity.title = todo.title;
		entity.url = todo.url;
		return Response.ok(entity).build();
	}

	@DELETE
	@Transactional
	public Response deleteCompleted() {
		this.todoRepository.deleteCompleted();
		return Response.noContent().build();
	}

	@DELETE
	@Transactional
	@Path("/{id}")
	public Response deleteOne(@PathParam("id") Long id) {
		Todo entity = todoRepository.findById(id);
		if (entity == null) {
			throw new WebApplicationException("Todo with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
		}
		todoRepository.delete(id);
		return Response.noContent().build();
	}

}