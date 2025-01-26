package dev.jlipka.cinemasystem.repository;

import dev.jlipka.cinemasystem.model.Genre;
import dev.jlipka.cinemasystem.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findByGenreType(String genreType);
}
