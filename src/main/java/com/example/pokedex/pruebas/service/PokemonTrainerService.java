package com.example.pokedex.pruebas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokedex.pruebas.exception.PokemonTrainerException;
import com.example.pokedex.pruebas.mappers.PokemonMapper;
import com.example.pokedex.pruebas.model.dataClasses.PokemonTrainerJSON;
import com.example.pokedex.pruebas.model.jpa.PokemonTrainer;
import com.example.pokedex.pruebas.model.response.PokemonTrainerResponse;
import com.example.pokedex.pruebas.repository.PokemonTrainerRepository;

@Service
public class PokemonTrainerService {
	@Autowired
	private PokemonTrainerRepository ptr;
	@Autowired
	private TrainerService trainerService;
	@Autowired
	private PokemonEntityService pes;

	public PokemonTrainerResponse savePokemonCaught(PokemonTrainerJSON ptJSON, Long trainerId) {
		List<PokemonTrainer> listPokemonTrainer = ptr.findAll().stream()
				.filter(pt -> pt.getAlias().equals(ptJSON.getAlias())).collect(Collectors.toList());
		List<PokemonTrainer> listPokemonTrainerPokemonId = ptr.findAll().stream()
				.filter(pt -> pt.getTrainerId().getTrainerId().equals(trainerId)).collect(Collectors.toList());
		if (!listPokemonTrainer.isEmpty()) {
			throw new PokemonTrainerException("404");
		}
		if (!listPokemonTrainerPokemonId.isEmpty()) {
			throw new PokemonTrainerException("422");
		} else {
			PokemonTrainer pt = new PokemonTrainer();
			pt.setAlias(ptJSON.getAlias());
			pt.setTrainerId(trainerService.getTrainerById(trainerId));
			pt.setPokemonId(pes.getPokemonById(ptJSON.getPokemonId()));
			ptr.saveAndFlush(pt);
			return PokemonMapper.mapFromPokemonTrainerToPokemonTrainerResponse(pt);
		}
	}

	public List<PokemonTrainerResponse> getPokemon(Long trainerId) {
		List<PokemonTrainer> listPokemonTrainer = ptr.findAll().stream()
				.filter(pt -> pt.getTrainerId().getTrainerId().equals(trainerId)).collect(Collectors.toList());
		List<PokemonTrainerResponse> listResponse = new ArrayList<>();
		for (PokemonTrainer pt : listPokemonTrainer) {
			PokemonTrainerResponse ptResponse = PokemonMapper.mapFromPokemonTrainerToPokemonTrainerResponse(pt);
			listResponse.add(ptResponse);
		}
		return listResponse;
	}

	public void delete(String alias) {
		ptr.deleteById(alias);
	}

}
