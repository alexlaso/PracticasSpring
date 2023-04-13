package com.example.pokedex.pruebas.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.pokedex.pruebas.model.dataClasses.PokeAnswerAPI;
import com.example.pokedex.pruebas.model.dataClasses.Pokemon;
import com.example.pokedex.pruebas.model.jpa.PokemonEntity;
import com.example.pokedex.pruebas.model.jpa.PokemonTrainer;
import com.example.pokedex.pruebas.model.response.PokemonTrainerResponse;

/**
 * Class designed to map the items contained on the list of Pokemon received by
 * the API response (PokeAnswerAPI) to the class Pokemon, which is later showed
 * to the client
 * 
 * @author Alejandro Laso
 *
 */
public class PokemonMapper {
	/**
	 * Method to use for mapping items from {@link PokeAnswerAPI} to {@link Pokemon}
	 * 
	 * @param listPokemon       List of the Pokemon after mapping
	 * @param listPokeAnswerAPI List of the Pokemon before mapping
	 * @return List converted from {@link PokeAnswerAPI} to {@link Pokemon}
	 */
	public static List<Pokemon> mapPokemonFromPokeAnswerAPI(List<PokeAnswerAPI> listPokeAnswerAPI) {
		List<Pokemon> listPokemon = new ArrayList<>();

		for (int j = 0; j < listPokeAnswerAPI.size(); j++) {
			Pokemon pokemon = new Pokemon();
			pokemon.setName(listPokeAnswerAPI.get(j).getName());
			String[] urlParts = listPokeAnswerAPI.get(j).getUrl().split("/");
			pokemon.setId(Integer.parseInt(urlParts[urlParts.length - 1]));
			listPokemon.add(pokemon);
		}
		return listPokemon;
	}

	public static List<PokemonEntity> mapPokemonEntityfromPokeAnswerAPI(List<PokeAnswerAPI> listPokeAnswerAPI) {
		List<PokemonEntity> listPokemonEntity = listPokeAnswerAPI.stream()
				.map(pokemonAPI -> new PokemonEntity(
						Integer.parseInt(pokemonAPI.getUrl().split("/")[pokemonAPI.getUrl().split("/").length - 1]),
						pokemonAPI.getName()))
				.collect(Collectors.toList());
		return listPokemonEntity;
	}

	public static PokemonTrainerResponse mapFromPokemonTrainerToPokemonTrainerResponse(PokemonTrainer pt) {
		PokemonTrainerResponse ptr = new PokemonTrainerResponse();
		ptr.setAlias(pt.getAlias());
		ptr.setPokemonId(pt.getPokemonId().getPokemonId());
		ptr.setTrainerId(pt.getTrainerId().getTrainerId());
		return ptr;
	}

	public static List<PokemonTrainerResponse> mapFromPokemonTrainerToPokemonTrainerResponseList(
			List<PokemonTrainer> ptList) {
		List<PokemonTrainerResponse> listResponse = ptList.stream().map(pt -> new PokemonTrainerResponse(pt.getAlias(),
				pt.getTrainerId().getTrainerId(), pt.getPokemonId().getPokemonId())).collect(Collectors.toList());
		return listResponse;
	}
}
