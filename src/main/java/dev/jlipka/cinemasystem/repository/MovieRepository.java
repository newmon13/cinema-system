package dev.jlipka.cinemasystem.repository;

import dev.jlipka.cinemasystem.model.Genre;
import dev.jlipka.cinemasystem.model.GenreType;
import dev.jlipka.cinemasystem.model.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findByGenres_GenreType(String genreType);
}