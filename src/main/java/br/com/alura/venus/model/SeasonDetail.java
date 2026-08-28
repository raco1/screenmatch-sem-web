package br.com.alura.venus.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record SeasonDetail(@JsonAlias("Season") String temporada,
                           @JsonAlias("Episodes") List<EpisodeDetail> episodios) {}
