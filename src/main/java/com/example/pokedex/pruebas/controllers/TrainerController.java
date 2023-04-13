package com.example.pokedex.pruebas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pokedex.pruebas.mappers.TrainerMapper;
import com.example.pokedex.pruebas.model.dataClasses.TrainerJSON;
import com.example.pokedex.pruebas.model.jpa.Trainer;
import com.example.pokedex.pruebas.model.response.TrainerExceptionResponse;
import com.example.pokedex.pruebas.service.TrainerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/v2/api/trainers")
public class TrainerController {
	@Autowired
	private TrainerService trainerService;
	
	@GetMapping
	@Operation(summary = "Get a list of all Trainers")
	@ApiResponse(responseCode = "200", description = "Ok", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) })
	private ResponseEntity<List<TrainerJSON>> getAllTrainers() {
		return new ResponseEntity<List<TrainerJSON>>(trainerService.getAllTrainersJSON(), HttpStatus.OK);
	}

	@GetMapping("/{trainerId}")
	@Operation(summary = "Get a specific trainer indicated by Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) }),
			@ApiResponse(responseCode = "404", description = "Error, trainer not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerExceptionResponse.class)) }) })
	private ResponseEntity<TrainerJSON> getTrainer(
			@PathVariable("trainerId") @Parameter(description = "Id of the trainer", example = "13") Long trainerId) {
		return new ResponseEntity<TrainerJSON>(trainerService.getTrainerJSONById(trainerId), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Saves a new trainer")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) }),
			@ApiResponse(responseCode = "422", description = "Error, trainer already exists", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerExceptionResponse.class)) }) })
	private ResponseEntity<Trainer> saveTrainer(@RequestBody TrainerJSON trainerJSON) {
		trainerService.save(trainerJSON);
		return new ResponseEntity<Trainer>(TrainerMapper.mapFromTrainerJSONToTrainer(trainerJSON), HttpStatus.OK);
	}

	@PostMapping(path = "/start", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Saves a list of trainers")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) }),
			@ApiResponse(responseCode = "422", description = "Error, trainer already exists", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerExceptionResponse.class)) }) })
	private ResponseEntity<List<TrainerJSON>> saveAllTrainers(@RequestBody List<TrainerJSON> trainersJSON) {
		for (TrainerJSON t : trainersJSON) {
			trainerService.save(t);
		}
		return new ResponseEntity<List<TrainerJSON>>(trainersJSON, HttpStatus.OK);
	}

	@PutMapping(path = "/{trainerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Updates a specific trainer indicated by Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) }),
			@ApiResponse(responseCode = "404", description = "Error, trainer not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerExceptionResponse.class)) }) })
	private ResponseEntity<Trainer> modifyTrainer(@RequestBody TrainerJSON trainerJSON,
			@PathVariable("trainerId") @Parameter(description = "Id of the trainer", example = "13") long trainerId) {
		return new ResponseEntity<Trainer>(trainerService.updateTrainer(trainerJSON, trainerId), HttpStatus.OK);
	}

	@GetMapping(path = "/age/{age}")
	@Operation(summary = "Get a list of trainers with the provided age")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) }),
			@ApiResponse(responseCode = "404", description = "Error, trainer not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerExceptionResponse.class)) }) })
	private ResponseEntity<List<TrainerJSON>> getTrainersByAge(@PathVariable("age")@Parameter(description="Age of the trainer", example="24") Integer age) {
		return new ResponseEntity<List<TrainerJSON>>(trainerService.trainersByAge(age), HttpStatus.OK);
	}

	@GetMapping(path = "/name/{name}")
	@Operation(summary = "Get a list of trainers with the provided name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) }),
			@ApiResponse(responseCode = "404", description = "Error, trainer not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerExceptionResponse.class)) }) })
	private ResponseEntity<List<TrainerJSON>> getTrainersByName(@PathVariable("name")@Parameter(description="Name of the trainer", example="Ash") String name) {
		return new ResponseEntity<List<TrainerJSON>>(trainerService.trainersByName(name), HttpStatus.OK);
	}

	@GetMapping(path = "/gender/{gender}")
	@Operation(summary = "Get a list of trainers with the provided gender")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) }),
			@ApiResponse(responseCode = "404", description = "Error, trainer not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerExceptionResponse.class)) }) })
	private ResponseEntity<List<TrainerJSON>> getTrainersByGender(@PathVariable("gender")@Parameter(description="Gender of the trainer", example="Male") String gender) {
		return new ResponseEntity<List<TrainerJSON>>(trainerService.trainersByGender(gender), HttpStatus.OK);
	}
}
