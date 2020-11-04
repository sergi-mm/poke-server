package com.alea.poke.service;

import java.util.List;

import com.alea.poke.domain.Pokemon;

public interface PokemonService {
	
	public void autoRefreshData();
	
	/**
	 * Manually refresh data
	 * @return
	 */
	public int importData();

	Long countPokemons();

	List<Pokemon> getPokemons();

	List<Pokemon> getTopHighestPokemons(int maxResults);

	List<Pokemon> getTopHeaviestPokemons(int maxResults);

	List<Pokemon> getTopExperiencedPokemons(int maxResults);
	
}
