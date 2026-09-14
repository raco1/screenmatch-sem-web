package br.com.alura.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
    private String titulo;
    private String numeroEp;
    private String temporada;
    private Double rating;
    private LocalDate data_lancamento;

    public Episode(String temporada, EpisodeDetail d) {
        this.titulo = d.titulo();
        this.numeroEp = d.episodio();
        this.temporada = temporada;

        try{
            this.rating = Double.valueOf(d.rating());
        } catch (NumberFormatException ex){
            this.rating = 0.0;
        }
        try {
            this.data_lancamento = LocalDate.parse(d.data_lancamento());
        } catch (DateTimeParseException ex){
            this.data_lancamento = null;
        }
    }

    public Double getRating() {
        return rating;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNumeroEp() {
        return numeroEp;
    }

    public String getTemporada() {
        return temporada;
    }

    public LocalDate getData_lancamento() {
        return data_lancamento;
    }

    @Override
    public String toString() {
        return  " titulo: '" + titulo + '\'' +
                ", episodio: '" + numeroEp + '\'' +
                ", temporada: '" + temporada + '\'' +
                ", rating: " + rating +
                ", data de lançamento: " + data_lancamento;
    }
}
