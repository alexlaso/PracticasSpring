package com.example.pokedex.pruebas.model.jpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainerUser {
	@Id
	private String username;
	@Column(name = "password")
	private String password;
	@ElementCollection
	private List<String> roles;

}