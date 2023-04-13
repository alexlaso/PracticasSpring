package com.example.pokedex.pruebas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pokedex.pruebas.model.jpa.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long>{

}
