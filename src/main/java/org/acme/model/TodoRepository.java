package org.acme.model;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.acme.model.entity.Todo;

@Stateless
public class TodoRepository {

	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	@PersistenceContext
	private EntityManager entityManager;

	public List<Todo> findAll() {
		logger.log(Level.INFO, "Finding all..");

		return this.entityManager.createNamedQuery("Todo.findAll", Todo.class).getResultList();
	}

	public List<Todo> findNotCompleted() {
		return this.entityManager.createNamedQuery("Todo.findNotCompleted", Todo.class).getResultList();
    }

	public List<Todo> findCompleted() {
		return this.entityManager.createNamedQuery("Todo.findCompleted", Todo.class).getResultList();
	}

	public Todo findById(Long id) {
		logger.log(Level.INFO, "Finding the todo with id {0}.", id);
		return this.entityManager.find(Todo.class, id);
	}

	public Todo persistTodo(Todo todo) {
		logger.log(Level.INFO, "Persisting the new todo {0}.", todo);
		this.entityManager.persist(todo);
		return todo;
	}


	public void deleteCompleted() {
		entityManager.createNamedQuery("Todo.deleteCompleted", Todo.class);
	}
	public void delete(long id) {
		logger.log(Level.INFO, "Removing a Todo {0}.", id);
		Todo todo = entityManager.find(Todo.class, id);
		this.entityManager.remove(todo);
	}

}
