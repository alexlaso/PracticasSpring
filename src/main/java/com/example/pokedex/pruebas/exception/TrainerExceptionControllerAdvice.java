package com.example.pokedex.pruebas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.pokedex.pruebas.model.response.TrainerExceptionResponse;

@ControllerAdvice
public class TrainerExceptionControllerAdvice {

	@ExceptionHandler(TrainerException.class)
	public ResponseEntity<TrainerExceptionResponse> handleTrainerNotFoundException(TrainerException te) {
		TrainerExceptionResponse response = new TrainerExceptionResponse();
		switch (response.getMessage()) {
		case "404":
			response.setCode(response.getMessage());
			response.setMessage("Trainer not found");
			return new ResponseEntity<TrainerExceptionResponse>(response, HttpStatus.NOT_FOUND);
		case "422":
			response.setCode(response.getMessage());
			response.setMessage("Id´s do not match");
			return new ResponseEntity<TrainerExceptionResponse>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		default:
			response.setCode(response.getMessage());
			response.setMessage("Unkown error");
			return new ResponseEntity<TrainerExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
