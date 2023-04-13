package com.example.pokedex.pruebas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pokedex.pruebas.exception.TrainerException;
import com.example.pokedex.pruebas.model.dataClasses.UserJSON;
import com.example.pokedex.pruebas.model.jpa.TrainerUser;
import com.example.pokedex.pruebas.repository.TrainerAdminRepository;

@Service
public class TrainerAdminService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TrainerAdminRepository tar;

	public List<TrainerUser> getAllUsers() {
		return tar.findAll();
	}
	
	public TrainerUser getUserById(String username) {
		return tar.findById(username).orElseThrow(() -> new TrainerException("404"));
	}

	public void delete(String username) {
		tar.delete(tar.findById(username).orElseThrow(() -> new TrainerException("404")));
	}

	public TrainerUser registerUser(UserJSON userJson) {
		TrainerUser user = new TrainerUser();
		
		user.setUsername(userJson.getUsername());
		user.setPassword(passwordEncoder.encode(userJson.getPassword()));
		
		user.setRoles(userJson.getRoles());
		return tar.save(user);
	}

//
//	public Trainer updateTrainer(TrainerJSON trainerJSON, long trainerId) {
//		Trainer trainer = getTrainerById(trainerId);
//		if (trainer.getTrainerId() == trainerId) {
//			trainer.setAge(trainerJSON.getAge());
//			trainer.setGender(trainerJSON.getGender());
//			trainer.setName(trainerJSON.getName());
//			return tar.save(trainer);
//		} else {
//			throw new TrainerException("422");
//		}
//	}
}
