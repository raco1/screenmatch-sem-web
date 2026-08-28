package br.com.alura.venus.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeDetail(@JsonAlias("Title")String titulo,
                            @JsonAlias("Released") String data_lancamento,
                            @JsonAlias("Episode") String episodio,
                            @JsonAlias("Runtime") String duracao,
                            @JsonAlias("imdbRating") String rating) {
}