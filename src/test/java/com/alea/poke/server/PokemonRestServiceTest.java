package com.alea.poke.server;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alea.poke.rest.PokemonRestService;
import com.alea.poke.rest.dto.PokemonDTO;

@SpringBootTest
public class PokemonRestServiceTest {
	
	@Autowired
	private PokemonRestService restService;
	
	@Test
	public void getAllRedVersionPokemons() {
		final String version = "red";
		List<PokemonDTO> pokemonList = this.restService.retreivePokemonsFromSource(Optional.of(version));
		
		assertThat(pokemonList).isNotEmpty();
		assertThat(pokemonList.size()).isEqualTo(151);
		assertThat(pokemonList).allMatch(p -> p.getGameIndices().stream().anyMatch(gi -> gi.getVersionDTO().getName().equals(version)), 
				"All pokemons must be present in " + version + " version");
	}
	
	@Test
	public void getNonExistingVersionPokemons() {
		final String version = "chocolate";
		List<PokemonDTO> pokemonList = this.restService.retreivePokemonsFromSource(Optional.of(version));
		
		assertThat(pokemonList).isEmpty();
	}

}
