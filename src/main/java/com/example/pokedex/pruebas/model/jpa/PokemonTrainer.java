package com.example.pokedex.pruebas.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name="POKEMON_TRAINER")
public class PokemonTrainer {
	@Id
	@Column(name="ALIAS")
	@Schema(description="Alias of the Pokemon", example="Cloud")
	private String alias;
	@ManyToOne
	@JoinColumn(name="POKEMON_ID")
	@Schema(description="Id of the Pokemon")
	private PokemonEntity pokemonId;
	@ManyToOne
	@JoinColumn(name="TRAINER_ID")
	@Schema(description="Id of the trainer")
	private Trainer trainerId;
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public PokemonEntity getPokemonId() {
		return pokemonId;
	}
	public void setPokemonId(PokemonEntity pokemonId) {
		this.pokemonId = pokemonId;
	}
	public Trainer getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(Trainer trainerId) {
		this.trainerId = trainerId;
	}
	public PokemonTrainer() {
		super();
	}
	
	
}