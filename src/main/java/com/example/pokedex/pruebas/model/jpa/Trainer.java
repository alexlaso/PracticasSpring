package com.example.pokedex.pruebas.model.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name="TRAINERS")
public class Trainer {
	@Id
	@GeneratedValue
	@Column(name="TRAINER_ID")
	@Schema(description="Id of the trainer", example="10")
	private Long trainerId;
	@Column(name="NAME")
	@Schema(description="Name of the trainer", example="Ash")
	private String name;
	@Column(name="AGE")
	@Schema(description="Age of the trainer", example="24")
	private Integer age;
	@Column(name="GENDER")
	@Schema(description="Gender of the trainer", example="Male")
	private Gender gender;
	
	@OneToMany(mappedBy="trainerId", cascade = CascadeType.ALL)
	@Schema(description="List of Pokemon caught by this trainer")
	private List<PokemonTrainer> ownedPokemon;
	
	public List<PokemonTrainer> getOwnedPokemon() {
		return ownedPokemon;
	}


	public void addPokemon(PokemonTrainer pokemonTrainer) {
		this.ownedPokemon.add(pokemonTrainer);
	}
	
	public void addAllPokemon(List<PokemonTrainer> pokemonList) {
		this.ownedPokemon.addAll(pokemonList);
	}
	
	public void setOwnedPokemon(List<PokemonTrainer> ownedPokemon) {
		this.ownedPokemon = ownedPokemon;
	}

	public void setTrainerId(long trainerId) {
		this.trainerId = trainerId;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Trainer() {
		super();
	}



	public Long getTrainerId() {
		return trainerId;
	}



	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}



	public String getName() {
		return name;
	}


	public Integer getAge() {
		return age;
	}



	public void setAge(Integer age) {
		this.age = age;
	}



	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public enum Gender{
		Male,
		Female
	}


	public void setGender(String string) {
		if (string=="Male") {
			this.gender=Gender.Male;
		}
		else if(string=="Female") {
			this.gender=Gender.Female;
		}
	}

}
