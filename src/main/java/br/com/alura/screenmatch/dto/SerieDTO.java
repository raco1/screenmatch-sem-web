package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.Categoria;

public record SerieDTO(Long id,
                       String titulo,
                       Integer totalTemporadas,
                       String data_lancamento,
                       Double rating,
                       Categoria categorias,
                       String sinopse,
                       String poster,
                       String atores) {
}
