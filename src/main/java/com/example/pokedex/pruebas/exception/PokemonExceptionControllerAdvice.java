package com.example.pokedex.pruebas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.pokedex.pruebas.model.response.PokemonTrainerExceptionResponse;

@ControllerAdvice
public class PokemonExceptionControllerAdvice {

	@ExceptionHandler(PokemonTrainerException.class)
	public ResponseEntity<PokemonTrainerExceptionResponse> handleTrainerNotFoundException(PokemonTrainerException pte) {
		PokemonTrainerExceptionResponse response = new PokemonTrainerExceptionResponse();
		switch (pte.getMessage()) {
		case "404":
			response.setCode(pte.getMessage());
			response.setMessage("Pokemon not found");
			return new ResponseEntity<PokemonTrainerExceptionResponse>(response, HttpStatus.NOT_FOUND);
		case "422":
			response.setCode(pte.getMessage());
			response.setMessage("Error, pokemon with that Alias already exists");
			return new ResponseEntity<PokemonTrainerExceptionResponse>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		case "422.2":

			response.setCode("422");
			response.setMessage("Error, this trainer already has that Pokemon");
			return new ResponseEntity<PokemonTrainerExceptionResponse>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		default:
			response.setCode(pte.getMessage());
			response.setMessage("Unkown error");
			return new ResponseEntity<PokemonTrainerExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
