package com.example.pokedex.pruebas.model.response;

import java.util.List;

import com.example.pokedex.pruebas.model.jpa.PokemonEntity;

import io.swagger.v3.oas.annotations.media.Schema;

public class PokemonEntityResponse {
	public PokemonEntityResponse() {
		super();
	}
	@Schema(description="List of Pokemon")
	private List<PokemonEntity> listPokemonEntity;

	public List<PokemonEntity> getListPokemonEntity() {
		return listPokemonEntity;
	}

	public void setListPokemonEntity(List<PokemonEntity> listPokemonEntity) {
		this.listPokemonEntity = listPokemonEntity;
	}

	public PokemonEntityResponse(List<PokemonEntity> listPokemonEntity) {
		super();
		this.listPokemonEntity = listPokemonEntity;
	}
	

}
