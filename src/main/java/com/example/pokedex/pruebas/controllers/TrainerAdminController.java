package com.example.pokedex.pruebas.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pokedex.pruebas.mappers.TrainerMapper;
import com.example.pokedex.pruebas.model.dataClasses.TrainerJSON;
import com.example.pokedex.pruebas.model.dataClasses.UserJSON;
import com.example.pokedex.pruebas.model.jpa.TrainerUser;
import com.example.pokedex.pruebas.model.response.TrainerExceptionResponse;
import com.example.pokedex.pruebas.service.TrainerAdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/v3/api/trainers")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class TrainerAdminController {
	@Autowired
	private TrainerAdminService tas;

	@GetMapping
	@Operation(summary = "Get a list of all Users")
	@ApiResponse(responseCode = "200", description = "Ok", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerUser.class)) })
	private ResponseEntity<List<TrainerUser>> getAllTrainers() {
		return new ResponseEntity<List<TrainerUser>>(tas.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/trainer")
	@Operation(summary = "Get a specific trainer indicated by Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) }),
			@ApiResponse(responseCode = "404", description = "Error, trainer not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerExceptionResponse.class)) }) })
	private ResponseEntity<TrainerUser> getTrainer(
			@PathParam("username") @Parameter(description = "Username of the user", example = "Ash_23") String username) {
		return new ResponseEntity<TrainerUser>(tas.getUserById(username), HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	@Operation(summary = "Deletes a specific user indicated by username")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) }),
			@ApiResponse(responseCode = "404", description = "Error, trainer not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerExceptionResponse.class)) }) })
	private void deleteUser(
			@PathParam("username") @Parameter(description = "Username of the user", example = "Ash_23") String username) {
		tas.delete(username);
	}
//
//	@PutMapping(path = "/{trainerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	@Operation(summary = "Updates a specific trainer indicated by Id")
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = "200", description = "Ok", content = {
//					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) }),
//			@ApiResponse(responseCode = "404", description = "Error, trainer not found", content = {
//					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerExceptionResponse.class)) }) })
//	private ResponseEntity<Trainer> modifyTrainer(@RequestBody TrainerJSON trainerJSON,
//			@PathVariable("trainerId") @Parameter(description = "Id of the trainer", example = "13") long trainerId) {
//		return new ResponseEntity<Trainer>(tas.updateTrainer(trainerJSON, trainerId), HttpStatus.OK);
//	}
//	
//	@GetMapping(path = "/age/{age}")
//	@Operation(summary = "Get a list of trainers with the provided age")
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = "200", description = "Ok", content = {
//					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) }),
//			@ApiResponse(responseCode = "404", description = "Error, trainer not found", content = {
//					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerExceptionResponse.class)) }) })
//	private ResponseEntity<List<TrainerJSON>> getTrainersByAge(@PathVariable("age")@Parameter(description="Age of the trainer", example="24") Integer age) {
//		return new ResponseEntity<List<TrainerJSON>>(tas.trainersByAge(age), HttpStatus.OK);
//	}
//
//	@GetMapping(path = "/name/{name}")
//	@Operation(summary = "Get a list of trainers with the provided name")
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = "200", description = "Ok", content = {
//					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) }),
//			@ApiResponse(responseCode = "404", description = "Error, trainer not found", content = {
//					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerExceptionResponse.class)) }) })
//	private ResponseEntity<List<TrainerJSON>> getTrainersByName(@PathVariable("name")@Parameter(description="Name of the trainer", example="Ash") String name) {
//		return new ResponseEntity<List<TrainerJSON>>(tas.trainersByName(name), HttpStatus.OK);
//	}
//
//	@GetMapping(path = "/gender/{gender}")
//	@Operation(summary = "Get a list of trainers with the provided gender")
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = "200", description = "Ok", content = {
//					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) }),
//			@ApiResponse(responseCode = "404", description = "Error, trainer not found", content = {
//					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerExceptionResponse.class)) }) })
//	private ResponseEntity<List<TrainerJSON>> getTrainersByGender(@PathVariable("gender")@Parameter(description="Gender of the trainer", example="Male") String gender) {
//		return new ResponseEntity<List<TrainerJSON>>(tas.trainersByGender(gender), HttpStatus.OK);
//	}
//	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Saves a new user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerJSON.class)) }),
			@ApiResponse(responseCode = "422", description = "Error, trainer already exists", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TrainerExceptionResponse.class)) }) })
	private ResponseEntity<TrainerUser> saveUser(@RequestBody UserJSON userJSON) {
		tas.registerUser(userJSON);
		return new ResponseEntity<TrainerUser>(TrainerMapper.mapFromUserJSONToUser(userJSON), HttpStatus.OK);
	}

}
