package dev.jlipka.cinemasystem.service;

import dev.jlipka.cinemasystem.model.Genre;
import dev.jlipka.cinemasystem.model.GenreType;
import dev.jlipka.cinemasystem.model.Movie;
import dev.jlipka.cinemasystem.repository.GenreRepository;
import dev.jlipka.cinemasystem.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    public List<Movie> getMoviesByGenre(GenreType genreType) {
        return movieRepository.findByGenres_GenreType(genreType.name());
    }

    public Movie addMovie(Movie movie, Set<GenreType> movieGenres) {
        Set<Genre> foundGenres = getGenres(movieGenres);
        movie.getGenres().addAll(foundGenres);
        movieRepository.save(movie);
        return movie;
    }

    public Movie prepareMovieWithNewGenre(Movie movie, String newGenre) {
        Genre newSavedGenre = addGenre(newGenre);
        movie.getGenres().add(newSavedGenre);
        return movie;
    }

    private Genre addGenre(String newGenre) {
        Genre newSavedGenre = genreRepository.save(Genre.builder()
                .genreType(newGenre)
                .build());
        return genreRepository.save(newSavedGenre);
    }

    private Set<Genre> getGenres(Set<GenreType> genreTypes) {
        return genreTypes.stream()
                .map(genreType -> genreRepository.findByGenreType(genreType.name()))
                .collect(Collectors.toSet());
    }
}
