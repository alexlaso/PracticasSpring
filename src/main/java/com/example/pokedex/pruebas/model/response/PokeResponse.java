package com.example.pokedex.pruebas.model.response;

import java.util.List;

import com.example.pokedex.pruebas.model.dataClasses.Pokemon;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class to show to the client after being mapped from {@link PokeCall}
 * @author AlejandroLaso
 *
 */
@Schema(description="Class to show as response of call")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokeResponse {
	@Schema(description="Total count of Pokemon in te API", example="1281")
	private int count;
	@Schema(description="URL to the next API call", example="https://pokeapi.co/api/v2/pokemon/?offset=40&limit=20")
	private String next;
	@Schema(description="URL to the previous API call", example="https://pokeapi.co/api/v2/pokemon/?offset=0&limit=20")
	private String previous;
	private List<Pokemon> listPokemon;
	
	@Override
	public String toString() {
		return "PokeResponse {count=" + count + ", next=" + next + ", previous=" + previous + ", listPokemon="
				+ listPokemon + "}";
	}

}
