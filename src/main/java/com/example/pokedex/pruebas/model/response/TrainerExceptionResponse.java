package com.example.pokedex.pruebas.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrainerExceptionResponse {
	@Schema(description="Code of the error", example="404")
	private String code;
	@Schema(description="Message about the error", example="Trainer not found")
	private String message;
}
