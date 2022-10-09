package org.acme.model.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@NamedQuery(name = "Todo.findAll", query = "SELECT t FROM Todo t ORDER BY t.title")
@NamedQuery(name = "Todo.findById", query = "SELECT t FROM Todo t WHERE t.id = :id")
@NamedQuery(name = "Todo.findNotCompleted", query = "SELECT t FROM Todo t WHERE t.completed = false")
@NamedQuery(name = "Todo.findCompleted", query = "SELECT t FROM Todo t WHERE t.completed = true")
@NamedQuery(name = "Todo.deleteCompleted", query = "DELETE FROM Todo t WHERE t.completed = true")

public class Todo{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@NotBlank
	@Column(unique = true)
	public String title;

	public boolean completed;

	@Column(name = "ordering")
	public int order;

	public String url;
}


