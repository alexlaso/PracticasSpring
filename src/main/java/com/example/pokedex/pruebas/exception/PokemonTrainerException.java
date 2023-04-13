package com.example.pokedex.pruebas.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PokemonTrainerException extends RuntimeException {
	private static final long serialVersionUID = -1456498969751672101L;

	public PokemonTrainerException(String code) {
		super(code);
	}

}
