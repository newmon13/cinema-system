package dev.jlipka.cinemasystem.service;

import dev.jlipka.cinemasystem.BaseTestClass;
import dev.jlipka.cinemasystem.config.ApplicationConfig;
import dev.jlipka.cinemasystem.model.Genre;
import dev.jlipka.cinemasystem.model.GenreType;
import dev.jlipka.cinemasystem.model.Movie;
import dev.jlipka.cinemasystem.repository.GenreRepository;
import dev.jlipka.cinemasystem.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Import(ApplicationConfig.class)
@SpringBootTest
@Transactional
class MovieServiceTest extends BaseTestClass {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieService movieService;

    @Test
    public void shouldSaveAndFindMovieWithGenresInDatabase() {
        //given
        Movie movie = prepareMovie("Test Title");
        Set<GenreType> movieGenres = new HashSet<>(Set.of(GenreType.Adventure, GenreType.Crime));
        //when
        Movie savedMovie = movieService.addMovie(movie, movieGenres);
        //then
        Optional<Movie> foundMovie = movieRepository.findById(savedMovie.getId());
        assertThat(foundMovie).isPresent();
        Set<GenreType> savedMovieGenres = foundMovie.get()
                .getGenres()
                .stream()
                .map(genre ->  GenreType.valueOf(genre.getGenreType()))
                .collect(Collectors.toSet());
        assertThat(savedMovieGenres).containsAll(movieGenres);
    }

    @Test
    public void shouldSaveAndFindMoviesByGenreTypeInDatabase() {
        //given
        List<Movie> movies = prepareMovies(3);
        //when
        movieService.addMovie(movies.get(0), Set.of(GenreType.Action, GenreType.Drama));
        movieService.addMovie(movies.get(1), Set.of(GenreType.Biography, GenreType.Documentary));
        movieService.addMovie(movies.get(2), Set.of(GenreType.Drama, GenreType.Animation));
        List<Movie> moviesByGenre = movieService.getMoviesByGenre(GenreType.Drama);
        //then
        assertThat(moviesByGenre).hasSize(2);
        moviesByGenre.stream().map(Movie::getGenres).forEach(genres -> {
            Set<String> collect = genres.stream()
                    .map(Genre::getGenreType)
                    .collect(Collectors.toSet());
            assertThat(collect).contains(GenreType.Drama.name());
        });
    }

    @Test
    public void shouldSaveAndFindMovieWithNewGenre() {
        //given
        Movie movie = prepareMovie("Test Title");
        String newGenre = "New Test Genre";
        Set<GenreType> movieGenres = new HashSet<>(Set.of(GenreType.Biography));
        movie = movieService.prepareMovieWithNewGenre(movie, newGenre);
        //when
        Movie savedMovie = movieService.addMovie(movie, movieGenres);
        //then
        Optional<Movie> foundMovie = movieRepository.findById(savedMovie.getId());
        assertThat(foundMovie).isPresent();
        assertThat(foundMovie.get().getGenres().stream().anyMatch(genre -> genre.getGenreType().equals(newGenre))).isTrue();
    }
}