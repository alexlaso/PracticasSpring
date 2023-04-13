package com.example.pokedex.pruebas.model.response;

import java.util.List;

import com.example.pokedex.pruebas.model.dataClasses.PokeAnswerAPI;

/**
 * Class used to map the response of the API
 * @author Alejandro Laso
 *
 */
public class PokeCall {

	private int count;
	private String next, previous;
	private List<PokeAnswerAPI> results;
	/**
	 * Getter for the list of Pokemon in the response
	 * @return List of the Pokemon in the response
	 */
	public List<PokeAnswerAPI> getResults() {
			return results;
		}
	/**
	 * Setter for the list of Pokemon in the response
	 * @param results List of Pokemon in the response
	 */
	public void setResults(List<PokeAnswerAPI> results) {
		this.results = results;
	}
	/**
	 * Getter for the count of total Pokemon in the API
	 * @return Count of total Pokemon in the API
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Setter for the count of total Pokemon in the API
	 * @param count Count of total Pokemon in the API
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Default class constructor
	 */
	public PokeCall() {
		super();
	}
	
	/**
	 * Getter for the next API call
	 * @return URL of the next API call
	 */
	public String getNext() {
		return next;
	}

	/**
	 * Setter for the next API call
	 * @param next URL of the next API call
	 */
	public void setNext(String next) {
		this.next = next;
	}
	
	/**
	 * Getter for the previous API call
	 * @return URL of the previous API call
	 */
	public String getPrevious() {
		return previous;
	}
	/**
	 * Setter for the previous API call
	 * @param previous URL of the previous API call
	 */
	public void setPrevious(String previous) {
		this.previous = previous;
	}

}
