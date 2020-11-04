package com.alea.poke.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alea.poke.domain.Pokemon;
import com.alea.poke.service.PokemonService;

@RestController
@RequestMapping("/")
public class PokemonController {
	
	@Autowired
	private PokemonService service;
	
	@GetMapping
	public String serverStatus() {
		return "Server running!";
	}
	
	@GetMapping("/pokemons/count")
	public Long countPokemons() {
		return service.countPokemons();
	}
	
	@GetMapping("/pokemons/")
	public List<Pokemon> allPokemons() {
		return service.getPokemons();
	}
	
	@GetMapping("/pokemons/top-weight")
	public List<Pokemon> topHeaviestPokemons(@RequestParam(name="max-results", required = false) Integer maxResults) {
		return service.getTopHeaviestPokemons(maxResults == null ? 5 : maxResults);
	}
	
	@GetMapping("/pokemons/top-height")
	public List<Pokemon> topHighestPokemons(@RequestParam(name="max-results", required = false) Integer maxResults) {
		return service.getTopHighestPokemons(maxResults == null ? 5 : maxResults);
	}
	
	@GetMapping("/pokemons/top-base-experience")
	public List<Pokemon> topExperiencedPokemons(@RequestParam(name="max-results", required = false) Integer maxResults) {
		return service.getTopExperiencedPokemons(maxResults == null ? 5 : maxResults);
	}
	
	@PostMapping("/import-data")
	public String importDataPokemons() {
		int result = service.importData();
		return "Imported " +result+ " pokemons";
	}
}
