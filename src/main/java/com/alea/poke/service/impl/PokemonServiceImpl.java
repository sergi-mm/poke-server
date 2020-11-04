package com.alea.poke.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alea.poke.domain.Pokemon;
import com.alea.poke.repository.PokemonRepository;
import com.alea.poke.rest.PokemonRestService;
import com.alea.poke.rest.dto.PokemonDTO;
import com.alea.poke.service.PokemonService;

@Service
public class PokemonServiceImpl implements PokemonService {

	@Autowired
	private PokemonRestService restService;
	
	@Autowired
	private PokemonRepository repository;
	
	
	private Logger logger = LoggerFactory.getLogger(PokemonServiceImpl.class);
	

	@Scheduled(initialDelay = 5000L, fixedDelay = 24 * 3600L * 1000L)
	public void autoRefreshData() {
		if(repository.count() > 0) {
			logger.info("Data already retreived, skipping auto import");
		}else {
			logger.info("Import all data");
			this.importData();
		}
	}
	
		
	@Override
	public int importData() {
		logger.info("Clearing database");
		repository.deleteAll();
		
		List<PokemonDTO> list = restService.retreivePokemonsFromSource(Optional.of("red"));
		
		list.stream().map(dto -> {
			Pokemon pokemon = new Pokemon();
			pokemon.setId(dto.getId());
			pokemon.setBaseExperience(dto.getBaseExperience());
			pokemon.setHeight(dto.getHeight());
			pokemon.setIsDefault(dto.getIsDefault());
			pokemon.setLocationAreaEncounters(dto.getLocationAreaEncounters());
			pokemon.setName(dto.getName());
			pokemon.setPosition(dto.getOrder());
			pokemon.setWeight(dto.getWeight());
			pokemon.setVersions(dto.getGameIndices().stream().map(gi -> gi.getVersionDTO().getName()).collect(Collectors.toSet()));
			return pokemon;
			
		}).forEach(pok -> repository.save(pok));
		
		logger.info("Saved pokemons : " +list.size() );
		
		return list.size();
	}
	
	@Override
	public Long countPokemons() {
		return repository.count();
	}
	
	@Override
	public List<Pokemon> getPokemons(){
		return repository.findAll();
	}
	
	@Override
	public List<Pokemon> getTopHighestPokemons(int maxResults){
		return repository.findByHeight(PageRequest.of(0, maxResults));
	}
	
	@Override
	public List<Pokemon> getTopHeaviestPokemons(int maxResults){
		return repository.findByWeight(PageRequest.of(0, maxResults));
	}
	
	@Override
	public List<Pokemon> getTopExperiencedPokemons(int maxResults){
		return repository.findByBaseExperience(PageRequest.of(0, maxResults));
	}
	
}
