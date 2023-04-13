package com.example.pokedex.pruebas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pokedex.pruebas.model.dataClasses.PokemonTrainerJSON;
import com.example.pokedex.pruebas.model.response.PokemonTrainerExceptionResponse;
import com.example.pokedex.pruebas.model.response.PokemonTrainerResponse;
import com.example.pokedex.pruebas.service.PokemonTrainerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/v2/api/trainers")
public class PokemonTrainerController {
	@Autowired
	private PokemonTrainerService pts;

	@PostMapping(path = "/{trainerId}/pokemon")
	@Operation(summary = "Adds a new Pokemon to a specific trainer indicated by the Id in the url")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Ok", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PokemonTrainerResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Pokemon not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PokemonTrainerExceptionResponse.class))),
			@ApiResponse(responseCode = "422", description = "Error, pokemon with that Alias already exists", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PokemonTrainerExceptionResponse.class))) })
	private ResponseEntity<PokemonTrainerResponse> addPokemon(@RequestBody PokemonTrainerJSON pokemonTrainerJSON,
			@PathVariable("trainerId") @Parameter(description = "Id of the trainer", example = "13") Long trainerId) {
		return new ResponseEntity<PokemonTrainerResponse>(pts.savePokemonCaught(pokemonTrainerJSON, trainerId),
				HttpStatus.OK);
	}

	@GetMapping(path = "/{trainerId}/pokemon")
	@Operation(summary = "Lists all Pokemon owned by a specific trainer indicated by the Id in the url")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Ok", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PokemonTrainerResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Error, pokemon not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PokemonTrainerExceptionResponse.class))) })
	private ResponseEntity<List<PokemonTrainerResponse>> getPokemon(
			@PathVariable("trainerId") @Parameter(description = "Id of the trainer", example = "13") Long trainerId) {
		return new ResponseEntity<List<PokemonTrainerResponse>>(pts.getPokemon(trainerId), HttpStatus.OK);
	}

	@DeleteMapping("/{trainerId}/pokemon/{alias}")
	@Operation(summary = "Deletes a specific pokemon indicated by Alias")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Ok", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PokemonTrainerResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Error, pokemon not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PokemonTrainerExceptionResponse.class))) })
	private ResponseEntity<List<PokemonTrainerResponse>> deletePokemon(
			@PathVariable("trainerId") @Parameter(description = "Id of the trainer", example = "13") Long trainerId,
			@PathVariable("alias") @Parameter(description = "Alias of the Pokemon", example = "Cloud") String alias) {
		pts.delete(alias);
		return new ResponseEntity<List<PokemonTrainerResponse>>(pts.getPokemon(trainerId), HttpStatus.OK);
	}

}
