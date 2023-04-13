package com.example.pokedex.pruebas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokedex.pruebas.exception.TrainerException;
import com.example.pokedex.pruebas.mappers.TrainerMapper;
import com.example.pokedex.pruebas.model.dataClasses.TrainerJSON;
import com.example.pokedex.pruebas.model.jpa.Trainer;
import com.example.pokedex.pruebas.repository.TrainerRepository;

@Service
public class TrainerService {
	@Autowired
	private TrainerRepository trainerRepository;

	public List<Trainer> getAllTrainers() {
		return trainerRepository.findAll();
	}

	public List<TrainerJSON> getAllTrainersJSON() {
		return TrainerMapper.mapFromTrainerToTrainerJSONList(trainerRepository.findAll());
	}

	public TrainerJSON getTrainerJSONById(long trainerId) {
		return TrainerMapper.mapFromTrainerToTrainerJSON(
				trainerRepository.findById(trainerId).orElseThrow(() -> new TrainerException("404")));
	}

	public Trainer getTrainerById(long trainerId) {
		return trainerRepository.findById(trainerId).orElseThrow(() -> new TrainerException("404"));
	}

	public void delete(Long trainerId) {
		trainerRepository.delete(trainerRepository.findById(trainerId).orElseThrow(() -> new TrainerException("404")));
	}

	public Trainer save(TrainerJSON trainerJson) {
		return trainerRepository.save(TrainerMapper.mapFromTrainerJSONToTrainer(trainerJson));
	}

	public List<TrainerJSON> trainersByAge(Integer age) {
		return TrainerMapper.mapFromTrainerToTrainerJSONList(trainerRepository.findAll().stream()
				.filter(trainer -> trainer.getAge().equals(age)).collect(Collectors.toList()));
	}

	public List<TrainerJSON> trainersByName(String name) {
		return TrainerMapper.mapFromTrainerToTrainerJSONList(trainerRepository.findAll().stream()
				.filter(trainer -> trainer.getName().equals(name)).collect(Collectors.toList()));
	}

	public List<TrainerJSON> trainersByGender(String gender) {
		return TrainerMapper.mapFromTrainerToTrainerJSONList(trainerRepository.findAll().stream()
				.filter(trainer -> trainer.getGender().toString().equals(gender)).collect(Collectors.toList()));
	}

	public Trainer updateTrainer(TrainerJSON trainerJSON, long trainerId) {
		Trainer trainer = getTrainerById(trainerId);
		if (trainer.getTrainerId() == trainerId) {
			trainer.setAge(trainerJSON.getAge());
			trainer.setGender(trainerJSON.getGender());
			trainer.setName(trainerJSON.getName());
			return trainerRepository.save(trainer);
		} else {
			throw new TrainerException("422");
		}
	}
}
