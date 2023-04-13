package com.example.pokedex.pruebas.model.dataClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class used to map the initial list of the Pokemon retrieved from the call to the API
 * @author Alejandro Laso
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokeAnswerAPI {
	private String name;
	private String url;
}
