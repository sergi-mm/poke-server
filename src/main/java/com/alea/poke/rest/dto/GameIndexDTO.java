package com.alea.poke.rest.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "game_index", "version" })
@Data
public class GameIndexDTO {

	@JsonProperty("game_index")
	private Integer gameIndex;
	@JsonProperty("version")
	private VersionDTO versionDTO;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	

}