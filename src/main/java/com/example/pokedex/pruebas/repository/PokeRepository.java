package com.example.pokedex.pruebas.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.pokedex.pruebas.model.response.PokeCall;
/**
 * Class designed to act as the repository of the application
 * @author Alejandro Laso
 *
 */
@Repository
public class PokeRepository {
	@Value("${values.urlPokeAPI}")
	private String urlPokeapi;
	@Value("${values.urlPokeAPIAlter}")
	private String urlPokeAPIAlter;
	// TODO Comprobar por que no funciona el autowired
	private RestTemplate restTemplate = new RestTemplate();
	/**
	 * Method to call the API and retrieve a list of Pokemon
	 * @return An instance of {@link PokeCall}
	 */
	public PokeCall pokeCallAPI(){
		return restTemplate.getForObject(urlPokeapi,PokeCall.class);
	}
	/**
	 * Method to call the API and retrieve a list of Pokemon with variables
	 * @param uriVariables Variables used to parametrize the API call
	 * @return An instance of {@link PokeCall}
	 */
	public PokeCall pokeCallAPIVariables(Map<String, Integer> uriVariables){
		PokeCall pokeCall = restTemplate.getForObject(urlPokeAPIAlter,PokeCall.class, uriVariables);
		return pokeCall;
	}
}
