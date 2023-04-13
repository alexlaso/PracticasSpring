package com.example.pokedex.pruebas.model.dataClasses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class designed to be mapped for the final response of each call to the API
 * which contains any "Pokemon" object
 * 
 * @author Alejandro Laso
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Class Pokemon")
public class Pokemon {
	@Schema(description = "Name of the Pokemon", example = "Pikachu")
	private String name;
	@Schema(description = "Id of the Pokemon", example = "16")
	private int id;
}
