
package com.alea.poke.rest.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "abilities", "base_experience", "forms", "height", "held_items", "id", "is_default", "location_area_encounters", "name", "order",
		"species", "types", "weight" })
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PokemonDTO implements Serializable {

	private final static long serialVersionUID = 4059835084036532401L;

	@JsonProperty("base_experience")
	private Integer baseExperience;
	
	@JsonProperty("height")
	private Integer height;
	
	@JsonProperty("held_items")
	private List<Object> heldItems = Collections.emptyList();
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("is_default")
	private Boolean isDefault;
	
	@JsonProperty("location_area_encounters")
	private String locationAreaEncounters;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("order")
	private Integer order;
	
	@JsonProperty("weight")
	private Integer weight;	

    @JsonProperty("game_indices")
    private List<GameIndexDTO> gameIndices = null;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
