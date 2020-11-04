package com.alea.poke.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"name",
"url"
})
@Data
public class ResultDTO {
	@JsonProperty("name")
	private String name;
	@JsonProperty("url")
	private String url;
}
