package br.com.alura.screenmatch.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodios")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String numeroEp;
    private String temporada;
    private Double rating;
    private LocalDate data_lancamento;

    @ManyToOne
    private Serie serie;

    public Episode(){}

    public Episode(String temporada, EpisodeDetail d) {
        this.titulo = d.titulo();
        this.numeroEp = d.episodio();
        this.temporada = temporada;

        try {
            this.rating = Double.valueOf(d.rating());
        } catch (NumberFormatException ex) {
            this.rating = 0.0;
        }
        try {
            this.data_lancamento = LocalDate.parse(d.data_lancamento());
        } catch (DateTimeParseException ex) {
            this.data_lancamento = null;
        }
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNumeroEp(String numeroEp) {
        this.numeroEp = numeroEp;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setData_lancamento(LocalDate data_lancamento) {
        this.data_lancamento = data_lancamento;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return "\n-------------------------------------------" + "\n" +
                "Nome do Episódio: " + titulo + "\n" +
                "Data de lançamento: " + data_lancamento + "\n" +
                "Episódio : " + numeroEp +
                "Temporada : " + temporada + "\n" +
                "Rating: " + rating + "\n" +
                "-------------------------------------------" + "\n";
    }
}
