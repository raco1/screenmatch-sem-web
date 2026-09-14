package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record SeasonDetail(@JsonAlias("Season") String temporada,
                           @JsonAlias("Episodes") List<EpisodeDetail> episodios)
{
    @Override
    public String toString() {
        return "\n-------------------------------------------" + "\n" +
                "Temporada: " + temporada + "\n" +
                "Episódios: " + episodios +
                "\n-------------------------------------------";
    }
}
