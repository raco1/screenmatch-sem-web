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
) {

    @Override
    public String toString() {
        return  "\n-------------------------------------------" + "\n" +
                "Série: " + titulo + "\n" +
                "Total de temporadas: " + total_temporadas + "\n" +
                "Data de lançamento: " + data_lancamento + "\n" +
                "Rating: " + rating + "\n" +
                "Categorias: " + categorias + "\n" +
                "Sinopse: " + sinopse + "\n" +
                "Atores: " + atores + "\n" +
                "Pôster: " + poster + "\n" +
                "-------------------------------------------";
    }
}
