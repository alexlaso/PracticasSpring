package com.example.pokedex.pruebas.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonTrainerResponse {
	@Schema(description="Alias of the Pokemon", example="Cloud")
	private String alias;
	@Schema(description="Id of the trainer", example="10")
	private Long trainerId;
	@Schema(description="Id of the Pokemon", example="13")
	private Integer pokemonId;
}
