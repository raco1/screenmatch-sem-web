package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeDetail(@JsonAlias("Title")String titulo,
                            @JsonAlias("Released") String data_lancamento,
                            @JsonAlias("Episode") String episodio,
                            @JsonAlias("Runtime") String duracao,
                            @JsonAlias("imdbRating") String rating)
{
    @Override
    public String toString() {
        return "\n-------------------------------------------" + "\n" +
                "Nome do episódio: " + titulo + "\n" +
                "Data de lançamento: " + data_lancamento + "\n" +
                "Episódio: " + episodio + "\n"+
                "Duração: " + duracao + "\n" +
                "Rating: " + rating + "\n" +
                "-------------------------------------------" + "\n";
    }
}