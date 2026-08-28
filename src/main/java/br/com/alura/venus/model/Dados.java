package br.com.alura.venus.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(@JsonAlias("Title")String titulo,
                    @JsonAlias("totalSeasons") Integer total_temporadas,
                    @JsonAlias("Released") String data_lancamento,
                    @JsonAlias("imdbRating") String rating) {}
