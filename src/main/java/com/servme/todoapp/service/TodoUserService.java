package com.servme.todoapp.service;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import com.servme.todoapp.entity.TodoUserEntity;
import com.servme.todoapp.model.TodoUser;

@Singleton
public class TodoUserService {

	@Transactional
	public void registerUser(TodoUser todoUser) {
		TodoUserEntity entity = this.convertToEntity(todoUser);
		TodoUserEntity.persist(entity, new Object[0]);
	}

	@Transactional
	public TodoUser loginUser(String email) {
		TodoUserEntity dbUser = TodoUserEntity.find("email = ?1", email).firstResult();
		if (dbUser != null) {
			return this.convertToModel(dbUser);
		}
		return null;
	}

	private TodoUserEntity convertToEntity(TodoUser todoUser) {
		TodoUserEntity entity = new TodoUserEntity();
		entity.setFirstName(todoUser.getFirstName());
		entity.setLastName(todoUser.getLastName());
		entity.setGender(todoUser.getGender());
		entity.setMobile(todoUser.getMobile());
		entity.setBirthDate(todoUser.getBirthDate());
		entity.setEmail(todoUser.getEmail());
		entity.setPassword(todoUser.getPassword());
		return entity;
	}

	private TodoUser convertToModel(TodoUserEntity entity) {
		TodoUser todoUser = new TodoUser();
		todoUser.setFirstName(entity.getFirstName());
		todoUser.setLastName(entity.getLastName());
		todoUser.setGender(entity.getGender());
		todoUser.setMobile(entity.getMobile());
		todoUser.setBirthDate(entity.getBirthDate());
		todoUser.setEmail(entity.getEmail());
		todoUser.setPassword(entity.getPassword());
		return todoUser;
	}
}
