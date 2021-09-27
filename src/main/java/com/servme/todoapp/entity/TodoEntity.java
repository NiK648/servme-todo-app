package com.servme.todoapp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.servme.todoapp.enums.Status;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class TodoEntity extends PanacheEntity {

	private String name;
	private String description;
	private Date dateTime;
	private Status status;
	@ManyToOne
	private CategoryEntity category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

}
