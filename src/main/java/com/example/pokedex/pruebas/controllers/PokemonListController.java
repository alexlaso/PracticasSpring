package com.example.pokedex.pruebas.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pokedex.pruebas.model.dataClasses.Pokemon;
import com.example.pokedex.pruebas.model.response.PokeResponse;
import com.example.pokedex.pruebas.model.response.PokemonEntityResponse;
import com.example.pokedex.pruebas.service.PokeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * This class acts as the controller for this application.
 * 
 * @author Alejandro Laso
 *
 */
@RestController
@RequestMapping(path = "/api")
public class PokemonListController {
	/**
	 * Value used by the program to set the limit on each API call
	 */
	@Value("${values.limit}")
	private Integer limit;

	@Autowired
	private PokeService pokeservice;

	/**
	 * Method used to get a standard list of Pokemon, with default values provided
	 * by the original API
	 * 
	 * @return PokeResponse containing the list of {@link Pokemon}
	 */
	@GetMapping(path = "/list")
	@Operation(summary = "Get a list of Pokemon with the default values of the API")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PokeResponse.class)) }),
			@ApiResponse(responseCode = "500", description = "Error", content = @Content) })
	public PokeResponse getList() {
		return pokeservice.getList();
	}

	/**
	 * Method to retrieve a list of Pokemon with the indicated amount of Pokemon you
	 * want and with an offset of your choice
	 * 
	 * @param limit  Number of Pokemon you want to retrieve in the search
	 * @param offset Starting number of the Pokemon to retrieve from the API
	 * @return PokeResponse containing the list of {@link Pokemon}
	 */
	@GetMapping(path = "/limitlist")
	@Operation(summary = "Get a list of Pokemon with different limit and/or offset")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PokeResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Limit and/or offset must be positive", content = @Content) })
	public ResponseEntity<PokeResponse> getLimitedList(
			@RequestParam @Parameter(description = "Limit of the pokemon to retrieve on the call", example="13") Integer limit,
			@RequestParam @Parameter(description = "Initial number of the Pokemon to retrieve on the call", example = "17") Integer offset) {
		if (limit >= 1 && offset >= 0) {
			Map<String, Integer> uriVariables = new HashMap<>();
			uriVariables.put("limit", limit);
			uriVariables.put("offset", offset);
			return new ResponseEntity<PokeResponse>(pokeservice.getLimitedList(uriVariables), HttpStatus.OK);
		} else {
			return new ResponseEntity<PokeResponse>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Method to retrieve a certain amount of Pokemon, by making the necessary calls
	 * to the API with a default limit of 20 each time
	 * 
	 * @param totalToGet Number of total Pokemon you want to retrieve from the API
	 * @param offset     Starting number of the Pokemon to retrieve from the API
	 * @return PokeResponse containing the list of {@link Pokemon}
	 */
	@GetMapping(path = "/optionallist")
	@Operation(summary = "Get a list of Pokemon with a limit and/or offset but with different calls to the API with 20 Pokemon on each call")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PokeResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Limit and/or offset must be positive", content = @Content) })
	public ResponseEntity<PokeResponse> getOptionalList(
			@RequestParam @Parameter(description = "Total amount of Pokemon to retrieve in the call", example = "53") Integer totalToGet,
			@RequestParam(defaultValue = "0") @Parameter(description = "Initial number of the Pokemon to retrieve in the call", required = false, example = "20") Integer offset) {
		if (totalToGet >= 1 && offset >= 0) {
			Map<String, Integer> uriVariables = new HashMap<>();
			uriVariables.put("limit", limit);
			uriVariables.put("offset", offset);
			return new ResponseEntity<PokeResponse>(pokeservice.getOptionalList(uriVariables, totalToGet),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<PokeResponse>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/start")
	public ResponseEntity<PokemonEntityResponse> getFullList() {
		Map<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("limit", 10000);
		uriVariables.put("offset", 0);
		return new ResponseEntity<PokemonEntityResponse>(pokeservice.getFullList(uriVariables), HttpStatus.OK);
	}
}
