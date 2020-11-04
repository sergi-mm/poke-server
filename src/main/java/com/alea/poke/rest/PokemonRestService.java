package com.alea.poke.rest;

import java.util.List;
import java.util.Optional;

import com.alea.poke.rest.dto.PokemonDTO;

public interface PokemonRestService {
	
	public List<PokemonDTO> retreivePokemonsFromSource(Optional<String> version);

}
