package com.example.pokedex.pruebas.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class TrainerException extends RuntimeException {
	private static final long serialVersionUID = -5011974459715781466L;

	public TrainerException(String code) {
		super(code);
	}

}
