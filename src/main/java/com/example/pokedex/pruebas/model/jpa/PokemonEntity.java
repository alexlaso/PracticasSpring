package com.example.pokedex.pruebas.model.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name="POKEMON")
public class PokemonEntity {
	@Id
	@Column(name="POKEMON_ID")
	@Schema(description="Id of the Pokemon", example="13")
	private Integer pokemonId;
	@Column(name="NAME")
	@Schema(description="Name of the Pokemon", example="Giratina")
	private String name;
	@OneToMany(mappedBy="pokemonId", cascade = CascadeType.ALL)
	@Schema(description="List of Pokemon with this Id that have an associated trainer")
	private List<PokemonTrainer> caughtPokemon;
	

	public PokemonEntity(int pokemonId, String name) {
		super();
		this.pokemonId = pokemonId;
		this.name = name;
	}
	
	public void addPokemonCaught(PokemonTrainer pokemonTrainer) {
		this.caughtPokemon.add(pokemonTrainer);
	}
	public void addAllPokemonCaught(List<PokemonTrainer> pokemonList) {
		this.caughtPokemon.addAll(pokemonList);
	}
	public List<PokemonTrainer> getCaughtPokemon() {
		return caughtPokemon;
	}
	public void setCaughtPokemon(List<PokemonTrainer> caughtPokemon) {
		this.caughtPokemon = caughtPokemon;
	}
	public void setPokemonId(Integer pokemonId) {
		this.pokemonId = pokemonId;
	}
	public PokemonEntity() {
		super();
	}
	public int getPokemonId() {
		return pokemonId;
	}
	public void setPokemonId(int pokemonId) {
		this.pokemonId = pokemonId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
