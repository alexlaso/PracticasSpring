package com.example.pokedex.pruebas.service;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokedex.pruebas.mappers.PokemonMapper;
import com.example.pokedex.pruebas.model.jpa.PokemonEntity;
import com.example.pokedex.pruebas.model.response.PokemonEntityResponse;
import com.example.pokedex.pruebas.repository.PokeRepository;
import com.example.pokedex.pruebas.repository.PokemonEntityRepository;

@Service
public class PokemonEntityService {
	@Autowired
	private PokemonEntityRepository per;
	@Autowired
	private PokeRepository pokeRepo;

	public PokemonEntityResponse getAllPokemon() {
		return new PokemonEntityResponse(per.findAll());
	}

	public PokemonEntity getPokemonById(int pokemonId) {
		return per.findById(pokemonId).orElseThrow(() -> new NoSuchElementException());
	}

	public void delete(Integer pokemonId) {
		per.delete(per.findById(pokemonId).orElseThrow(() -> new NoSuchElementException()));
	}

	public PokemonEntity save(PokemonEntity pokemonEntity) {
		return per.save(pokemonEntity);
	}

	public PokemonEntityResponse getFullList(Map<String, Integer> uriVariables) {
		return new PokemonEntityResponse(PokemonMapper
				.mapPokemonEntityfromPokeAnswerAPI(pokeRepo.pokeCallAPIVariables(uriVariables).getResults()));
	}

}
