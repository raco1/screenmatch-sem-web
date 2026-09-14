package br.com.alura.screenmatch.model;

import java.util.OptionalDouble;

public class Serie {
    private String titulo;
    private Integer total_temporadas;
    private String data_lancamento;
    private Double rating;
    private Categoria categorias;
    private String sinopse;
    private String poster;
    private String atores;

    public Serie(Dados d) {
        this.titulo = d.titulo();
        this.total_temporadas = d.total_temporadas();
        this.data_lancamento = d.data_lancamento();
        this.rating = OptionalDouble.of(Double.valueOf(d.rating())).orElse(0);
        this.categorias = Categoria.fromString(d.categorias().split(",")[0].trim());
        this.sinopse = d.sinopse();
        this.poster = d.poster();
        this.atores = d.atores();
    }
}
