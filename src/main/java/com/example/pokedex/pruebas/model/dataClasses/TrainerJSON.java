package com.example.pokedex.pruebas.model.dataClasses;

import com.example.pokedex.pruebas.model.jpa.Trainer.Gender;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainerJSON {
	@Schema(description = "Name of the trainer", example = "Ash")
	private String name;
	@Schema(description = "Age of the trainer", example = "24")
	private Integer age;
	@Schema(description = "Gender of the trainer", example = "Male")
	@Setter(AccessLevel.NONE)
	private Gender gender;
	@Schema(description = "Id of the trainer", example = "1")
	private Long Id;

	public TrainerJSON(Long Id, String name, Integer age, Gender gender) {
		super();
		this.Id = Id;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public void setGender(String string) {
		if (string.equalsIgnoreCase("male")) {
			this.gender = Gender.Male;
		} else if (string.equalsIgnoreCase("female")) {
			this.gender = Gender.Female;
		} else {
			this.gender = Gender.Male;
		}
	}

}
