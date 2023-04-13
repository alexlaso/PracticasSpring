package com.example.pokedex.pruebas.model.dataClasses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonTrainerJSON {
	@Schema(description="Alias of the Pokemon", example="Cloud")
	private String alias;
	@Schema(description="Id of the pokemon", example="13")
	private Integer pokemonId;
}
