package com.example.pokedex.pruebas.model.dataClasses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserJSON {
	private String username;
	private String password;
	private List<String> roles;
}
