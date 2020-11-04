package com.alea.poke.server;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.alea.poke.domain.Pokemon;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PokemonControllerTests {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	public static final String SERVER_URL = "http://localhost:";

	
	private String getServerUrl() {
		return SERVER_URL + port;
	}
	

	@Test
	void checkServerStatus() {
		assertThat(this.restTemplate.getForObject(getServerUrl() + "/",
				String.class)).contains("Server running!");
	}
	
	@Test
	void pokemonCountRedVersion() {
		//Pokemon red includes 150-151 pokemons
		assertThat(this.restTemplate.getForObject(getServerUrl() + "/pokemons/count",
				String.class)).isEqualTo("151").describedAs("Pokemons database must contains 151 pokemons");
	}
	
	@Test
	void pokemonTopHeaviest() {
		//Must return 5 pokemons, ordered by weight
		Pokemon[] pokemons = this.restTemplate.getForObject(getServerUrl() + "/pokemons/top-weight", Pokemon[].class);
		
		assertThat(pokemons).isNotEmpty().describedAs("List must not be empty");
		assertThat(pokemons.length).isEqualTo(5).describedAs("List must contains 5 elements");
		
		IntStream stream = IntStream.range(0, pokemons.length-1);
		assertThat(stream).allMatch(i -> pokemons[i].getWeight().compareTo(pokemons[i+1].getWeight()) >= 0,
				"List must be ordered by weight desc");
	}
	
	@Test
	void pokemonTopHighest() {
		//Must return 5 pokemons, ordered by height
		Pokemon[] pokemons = this.restTemplate.getForObject(getServerUrl() + "/pokemons/top-height", Pokemon[].class);
		
		assertThat(pokemons).isNotEmpty().describedAs("List must not be empty");
		assertThat(pokemons.length).isEqualTo(5).describedAs("List must contains 5 elements");
		
		IntStream stream = IntStream.range(0, pokemons.length-1);
		assertThat(stream).allMatch(i -> pokemons[i].getHeight().compareTo(pokemons[i+1].getHeight()) >= 0,
				"List must be ordered by heigt desc");
	}
	
	@Test
	void pokemonTopBaseExperience() {
		//Must return 5 pokemons, ordered by base experience
		Pokemon[] pokemons = this.restTemplate.getForObject(getServerUrl() + "/pokemons/top-base-experience", Pokemon[].class);
		
		assertThat(pokemons).isNotEmpty().describedAs("List must not be empty");
		assertThat(pokemons.length).isEqualTo(5).describedAs("List must contains 5 elements");
		
		IntStream stream = IntStream.range(0, pokemons.length-1);
		assertThat(stream).allMatch(i -> pokemons[i].getBaseExperience().compareTo(pokemons[i+1].getBaseExperience()) >= 0, 
				"List must be ordered by base-experience desc");
	}

}
