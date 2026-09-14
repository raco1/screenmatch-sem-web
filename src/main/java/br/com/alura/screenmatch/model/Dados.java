package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(@JsonAlias("Title")String titulo,
                    @JsonAlias("totalSeasons") Integer total_temporadas,
                    @JsonAlias("Released") String data_lancamento,
                    @JsonAlias("imdbRating") String rating,
                    @JsonAlias("Genre") String categorias,
                    @JsonAlias("Plot") String sinopse,
                    @JsonAlias("Poster") String poster,
                    @JsonAlias("Actors") String atores
) {}
