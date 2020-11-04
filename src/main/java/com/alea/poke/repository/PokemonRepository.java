package com.alea.poke.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alea.poke.domain.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{

	@Query("select p from Pokemon p order by p.weight desc")
	public List<Pokemon> findByWeight(Pageable pageable);
	
	@Query("select p from Pokemon p order by p.height desc")
	public List<Pokemon> findByHeight(Pageable pageable);
	
	@Query("select p from Pokemon p order by p.baseExperience desc")
	public List<Pokemon> findByBaseExperience(Pageable pageable);
}
