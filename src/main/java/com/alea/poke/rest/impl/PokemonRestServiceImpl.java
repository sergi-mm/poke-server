package com.alea.poke.rest.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alea.poke.rest.PokemonRestService;
import com.alea.poke.rest.dto.PokemonDTO;
import com.alea.poke.rest.dto.QueryResponseDTO;
import com.alea.poke.rest.dto.ResultDTO;

@Service
public class PokemonRestServiceImpl implements PokemonRestService{	
	
	private RestTemplate restTemplate = new RestTemplate();

	public static final String POKEMONS_URL = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=5000";	

	private Logger logger = LoggerFactory.getLogger(PokemonRestServiceImpl.class);
	
	public List<PokemonDTO> retreivePokemonsFromSource(Optional<String> version) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent", "Application");
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		//Request
		ResponseEntity<QueryResponseDTO> response = restTemplate.exchange(POKEMONS_URL, HttpMethod.GET, entity, QueryResponseDTO.class);
		if(!response.hasBody()) {
			throw new RuntimeException("Nothing retreived");
		}
		
		List<PokemonDTO> list = new ArrayList<>();
		int count = 0;
		for(ResultDTO result : response.getBody().getResults()) {
			//logger.debug("Retreiving pokemon " + result.getName());
			
			ResponseEntity<PokemonDTO> pokemonRes = restTemplate.exchange(result.getUrl(), HttpMethod.GET, entity, PokemonDTO.class);

			count++;
			if(count % 100 == 0) {
				logger.info("Fetched " + count+ " results");
			}
			
			if(pokemonRes.hasBody() && version.isPresent() 
					&& pokemonRes.getBody().getGameIndices().stream().anyMatch(v -> v.getVersionDTO().getName().equals(version.get()))) {
				list.add(pokemonRes.getBody());
			}
			
		}
		
		return list;
	}

}
