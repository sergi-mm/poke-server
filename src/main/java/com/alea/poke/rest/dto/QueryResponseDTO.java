package com.alea.poke.rest.dto;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "count", "next", "previous", "results" })
@Data
public class QueryResponseDTO {

	@JsonProperty("count")
	private Integer count;
	@JsonProperty("next")
	private String next;
	@JsonProperty("previous")
	private Object previous;
	@JsonProperty("results")
	private List<ResultDTO> results = Collections.emptyList();
	
	
	

}