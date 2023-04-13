package com.example.pokedex.pruebas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.pokedex.pruebas.mappers.PokemonMapper;
import com.example.pokedex.pruebas.model.dataClasses.PokeAnswerAPI;
import com.example.pokedex.pruebas.model.dataClasses.Pokemon;
import com.example.pokedex.pruebas.model.jpa.PokemonEntity;
import com.example.pokedex.pruebas.model.response.PokeCall;
import com.example.pokedex.pruebas.model.response.PokeResponse;
import com.example.pokedex.pruebas.model.response.PokemonEntityResponse;
import com.example.pokedex.pruebas.repository.PokeRepository;
import com.example.pokedex.pruebas.repository.PokemonEntityRepository;

/**
 * Class designed to act as service for the application
 * 
 * @author Alejandro Laso
 *
 */
@Service
public class PokeService {
	@Value("${values.urlPokeAPI}")
	private String urlPokeAPI;

	@Autowired
	private PokeRepository pokerepo;

	@Autowired
	private PokemonEntityRepository per;

	private final static RestTemplate restTemplate = new RestTemplate();

	/**
	 * Method to get a list of Pokemon with the default values of the API
	 * 
	 * @return An instance of {@link PokeResponse} with a list of {@link Pokemon}
	 */
	public PokeResponse getList() {
		PokeCall pokeCall = restTemplate.getForObject(urlPokeAPI + "pokemon", PokeCall.class);
		PokeResponse pokeResponse = new PokeResponse();

		List<Pokemon> pokeList = new ArrayList<>();
		for (int i = 0; i < pokeCall.getResults().size(); i++) {
			Pokemon pokemon = new Pokemon();
			pokemon.setName(pokeCall.getResults().get(i).getName());
			String[] urlParts = pokeCall.getResults().get(i).getUrl().split("/");
			pokemon.setId(Integer.parseInt(urlParts[urlParts.length - 1]));
			pokeList.add(pokemon);
		}

		pokeResponse.setCount(pokeCall.getCount());
		pokeResponse.setNext(pokeCall.getNext());
		pokeResponse.setPrevious(pokeCall.getPrevious());

		pokeResponse.setListPokemon(pokeList);
		return pokeResponse;
	}

	/**
	 * Method to get a list of Pokemon with different limit and offset, introduced
	 * by the client
	 * 
	 * @param uriVariables Variables to parametrize the call (limit and offset)
	 * @return An instance of {@link PokeResponse} with a list of {@link Pokemon}
	 */
	public PokeResponse getLimitedList(Map<String, Integer> uriVariables) {
		PokeCall pokeCall = new PokeCall();
		PokeResponse pokeResponse = new PokeResponse();

		List<PokeAnswerAPI> listPokeAnswerAPI = new ArrayList<>();

		pokeCall = pokerepo.pokeCallAPIVariables(uriVariables);
		listPokeAnswerAPI.addAll(pokeCall.getResults());

		pokeResponse.setCount(pokeCall.getCount());
		pokeResponse.setNext(pokeCall.getNext());
		pokeResponse.setPrevious(pokeCall.getPrevious());
		pokeResponse.setListPokemon(PokemonMapper.mapPokemonFromPokeAnswerAPI(listPokeAnswerAPI));

		return pokeResponse;
	}

	/**
	 * Method to make calls with a default limit of 20, until getting a total amount
	 * selected by the user
	 * 
	 * @param uriVariables Variables to parametrize the calls
	 * @param totalToGet   Total amount of Pokemon to retrieve
	 * @return An instance of {@link PokeResponse} with a list of {@link Pokemon}
	 */
	public PokeResponse getOptionalList(Map<String, Integer> uriVariables, Integer totalToGet) {
		PokeResponse pokeResponse = new PokeResponse();
		PokeCall pokeCall = new PokeCall();

		List<PokeAnswerAPI> listPokeAnswerAPI = new ArrayList<>();

		while (listPokeAnswerAPI.size() <= totalToGet) {
			pokeCall = pokerepo.pokeCallAPIVariables(uriVariables);
			listPokeAnswerAPI.addAll(pokeCall.getResults());
			pokeResponse.setCount(pokeCall.getCount());
			uriVariables.replace("offset", uriVariables.get("offset") + 20);
		}

		pokeResponse.setListPokemon(PokemonMapper.mapPokemonFromPokeAnswerAPI(listPokeAnswerAPI).stream()
				.limit(totalToGet).collect(Collectors.toList()));
		return pokeResponse;
	}

	public PokemonEntityResponse getFullList(Map<String, Integer> uriVariables) {
		List<PokemonEntity> listPokemonEntity = PokemonMapper
				.mapPokemonEntityfromPokeAnswerAPI(pokerepo.pokeCallAPIVariables(uriVariables).getResults());

		for (PokemonEntity pe : listPokemonEntity) {
			per.save(pe);
		}

		return new PokemonEntityResponse(listPokemonEntity);
	}
}