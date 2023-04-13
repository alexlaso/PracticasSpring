package com.example.pokedex.pruebas.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonTrainerExceptionResponse {
	@Schema(description="Code of the error", example="404")
	private String code;
	@Schema(description="Message about the error", example="This trainer does not have any Pokemon with that Id")
	private String message;
	
}
