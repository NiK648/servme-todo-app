package com.servme.todoapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import com.servme.todoapp.entity.CategoryEntity;
import com.servme.todoapp.entity.TodoEntity;
import com.servme.todoapp.model.Category;
import com.servme.todoapp.model.Todo;

import io.quarkus.panache.common.Parameters;

@Singleton
public class TodoService {

	@Transactional
	public void addCategory(Category category) {
		CategoryEntity entity = new CategoryEntity();
		entity.setCategory(category.getCategory());
		CategoryEntity.persist(entity, new Object[0]);
	}

	@Transactional
	public void removeCategory(Category category) {
		CategoryEntity.delete("id = ?1", category.getId());
	}

	@Transactional
	public void addTodo(Todo todo) {
		TodoEntity entity = new TodoEntity();
		entity.setName(todo.getName());
		entity.setDescription(todo.getDescription());
		entity.setDateTime(todo.getDateTime());
		entity.setStatus(todo.getStatus());

		CategoryEntity cEntity = CategoryEntity.find("id = ?1", todo.getId()).firstResult();
		entity.setCategory(cEntity);

		TodoEntity.persist(entity, new Object[0]);
	}

	@Transactional
	public void updateTodo(Todo todo) {
		TodoEntity entity = TodoEntity.find("id = ?1", todo.getId()).firstResult();
		CategoryEntity cEntity = CategoryEntity.find("id = ?1", todo.getCategoryId()).firstResult();
		entity.setName(todo.getName());
		entity.setDescription(todo.getDescription());
		entity.setDateTime(todo.getDateTime());
		entity.setStatus(todo.getStatus());
		entity.setCategory(cEntity);
	}

	@Transactional
	public List<Todo> listTodos(Todo todo) {
		List<TodoEntity> entities = TodoEntity.listAll();
		List<Todo> result = new ArrayList<Todo>();
		for (TodoEntity entity : entities) {
			result.add(this.converToBean(entity));
		}
		return result;
	}

	private Todo converToBean(TodoEntity entity) {
		Todo bean = new Todo();
		bean.setName(entity.getName());
		bean.setDescription(entity.getDescription());
		bean.setDateTime(entity.getDateTime());
		bean.setStatus(entity.getStatus());
		bean.setCategoryName(entity.getCategory() != null ? entity.getCategory().getCategory() : "");
		return bean;
	}

}
