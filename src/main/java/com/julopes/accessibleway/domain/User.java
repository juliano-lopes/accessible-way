package com.julopes.accessibleway.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull(message = "Name should be not null")
	@NotEmpty(message = "Name should be not empty")
	private String name;
	@NotNull(message = "E-mail should be not null")
	@NotEmpty(message = "E-mail should be not empty")
	private String email;
	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
	List<Telephone> telephones;

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setTelephones(List<Telephone> telephones) {
		this.telephones = telephones;
	}

}
