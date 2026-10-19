package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String titulo);

    List<Serie> findByAtoresContainingIgnoreCaseAndRatingGreaterThanEqual(String nomeAtor, Double rating);

    List<Serie> findTop5ByOrderByRatingDesc();

    List<Serie> findByCategorias(Categoria categoria);

    List<Serie> findByTotalTemporadasLessThanEqualAndRatingGreaterThanEqual(int totalTemporadas, double rating);

    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.rating >= :rating")
    List<Serie> seriesPorTemporadaERating(int totalTemporadas, double rating);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEp%")
    List<Episode> episodiosPorTrecho(String trechoEp);
}

