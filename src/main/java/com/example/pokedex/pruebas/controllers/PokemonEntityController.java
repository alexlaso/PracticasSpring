package com.example.pokedex.pruebas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pokedex.pruebas.model.jpa.PokemonEntity;
import com.example.pokedex.pruebas.model.response.PokemonEntityResponse;
import com.example.pokedex.pruebas.service.PokemonEntityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/v2/api")
public class PokemonEntityController {
	@Autowired
	private PokemonEntityService pes;
	
	@GetMapping("/list")
	@Operation(summary = "Gets all Pokemon available")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Ok", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PokemonEntityResponse.class)) }),
			@ApiResponse(responseCode = "500", description = "Error", content = @Content) })
	private ResponseEntity<PokemonEntityResponse> getAllPokemon() {
		return new ResponseEntity<PokemonEntityResponse>(pes.getAllPokemon(), HttpStatus.OK);
	}

	@GetMapping("/{pokemonId}")
	@Operation(summary = "Gets a specific Pokemon with the provided Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PokemonEntity.class)) }),
			@ApiResponse(responseCode = "404", description = "Error, Pokemon not found", content = @Content) })
	private ResponseEntity<PokemonEntity> getPokemonById(
			@PathVariable("pokemonId") @Parameter(description = "Id of the pokemon to retrieve", example = "16") Integer pokemonId) {
		return new ResponseEntity<PokemonEntity>(pes.getPokemonById(pokemonId), HttpStatus.OK);
	}
}
