package com.example.pokedex.pruebas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pokedex.pruebas.model.jpa.PokemonEntity;

@Repository
public interface PokemonEntityRepository extends JpaRepository<PokemonEntity, Integer>{

}
