package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.service.ConsultaGemini;

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
        this.sinopse = ConsultaGemini.obterTraducao(d.sinopse()).trim();
        this.poster = d.poster();
        this.atores = d.atores();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotal_temporadas() {
        return total_temporadas;
    }

    public void setTotal_temporadas(Integer total_temporadas) {
        this.total_temporadas = total_temporadas;
    }

    public String getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(String data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Categoria getCategorias() {
        return categorias;
    }

    public void setCategorias(Categoria categorias) {
        this.categorias = categorias;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    @Override
    public String toString() {
        return "\n-------------------------------------------" + "\n" +
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
