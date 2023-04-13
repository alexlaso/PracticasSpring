package com.example.pokedex.pruebas.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.pokedex.pruebas.model.dataClasses.TrainerJSON;
import com.example.pokedex.pruebas.model.dataClasses.UserJSON;
import com.example.pokedex.pruebas.model.jpa.Trainer;
import com.example.pokedex.pruebas.model.jpa.TrainerUser;

public class TrainerMapper {
		
	public static List<Trainer> mapFromTrainerJSONToTrainerList(List<TrainerJSON> listTrainersJSON) {
		List<Trainer> listTrainers = new ArrayList<>();
		for (int j = 0; j < listTrainersJSON.size(); j++) {
			Trainer trainer = new Trainer();
			trainer.setName(listTrainersJSON.get(j).getName());
			trainer.setAge(listTrainersJSON.get(j).getAge());
			trainer.setGender(listTrainersJSON.get(j).getGender());
			listTrainers.add(trainer);
		}
		return listTrainers;
	}

	public static Trainer mapFromTrainerJSONToTrainer(TrainerJSON trainerJSON) {
		Trainer trainer = new Trainer();
		trainer.setAge(trainerJSON.getAge());
		trainer.setName(trainerJSON.getName());
		trainer.setGender(trainerJSON.getGender());
		return trainer;
	}

	public static List<TrainerJSON> mapFromTrainerToTrainerJSONList(List<Trainer> listTrainer) {
		return listTrainer.stream().map(trainer -> new TrainerJSON(trainer.getTrainerId(),
				trainer.getName(), trainer.getAge(), trainer.getGender())).collect(Collectors.toList());
	}

	public static TrainerJSON mapFromTrainerToTrainerJSON(Trainer trainer) {
		return new TrainerJSON(trainer.getTrainerId(), trainer.getName(), trainer.getAge(),
				trainer.getGender());
	}
	
	public static TrainerUser mapFromUserJSONToUser(UserJSON userJSON) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return new TrainerUser(userJSON.getUsername(),encoder.encode(userJSON.getPassword()), userJSON.getRoles());
	}

}
