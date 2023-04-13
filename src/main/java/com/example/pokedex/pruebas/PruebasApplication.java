package com.example.pokedex.pruebas;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.pokedex.pruebas.service.PokeService;

/**
 * Main of the application
 * 
 * @author Alejandro Laso
 *
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.pokedex.pruebas.repository")
public class PruebasApplication implements ApplicationRunner {
	@Autowired
	private PokeService pokeservice;

	/**
	 * Main method of the application
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PruebasApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Map<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("limit", 10000);
		uriVariables.put("offset", 0);
		pokeservice.getFullList(uriVariables);
	}

}
