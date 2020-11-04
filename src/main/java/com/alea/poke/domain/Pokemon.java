package com.alea.poke.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "pokemon")
public class Pokemon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8144290359132272148L;

	@Id
	private Integer id;
	
	private Integer baseExperience;
	
	private Integer height;	
	
	private Boolean isDefault;
	
	private String locationAreaEncounters;
	
	private String name;
	
	private Integer position;
	
	private Integer weight;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> versions;
}
